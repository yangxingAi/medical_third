package com.cqupt.software_1.controller;

import com.cqupt.software_1.common.ExecState;
import com.cqupt.software_1.common.R;
import com.cqupt.software_1.config.RemoteConfig;
import com.cqupt.software_1.service.FileService;
import com.cqupt.software_1.service.TableService;
import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileController {


    @Autowired
    private FileService fileService;

    @Autowired
    private ResourceLoader resourceLoader;


    @Autowired
    private ServletContext servletContext;


    @Autowired
    private TableService tableService;


    @Autowired
    private RemoteConfig remoteConfig;

    @PostMapping("/upload")
    public R uploadFile(@RequestParam("file") MultipartFile file) throws IOException, JSchException, SftpException {

//        // 获取项目的根路径
//        String rootPath = resourceLoader.getResource("").getFile().getAbsolutePath();
//        // 定义文件保存的相对路径
//        String relativePath = "uploads/";
//        // 拼接完整的保存路径
//        String filePath = rootPath + File.separator + relativePath;
//
//        System.out.println(filePath);
//
//        // 处理文件上传逻辑
//        if (!file.isEmpty()) {
//            try {
//                // 获取文件名
//                String fileName = file.getOriginalFilename();
//                // 创建保存文件的目录
//                File directory = new File(filePath);
//                if (!directory.exists()) {
//                    directory.mkdirs();
//                }
//                // 创建保存文件的完整路径
//                String fileFullPath = filePath + fileName;
//
//                // 执行文件保存操作
//                file.transferTo(new File(fileFullPath));
//
//
//                // 导入数据表的内容到数据库
//
//
//
//
//
//
//
//
//
//
//
//                return new R<>(200, "成功" , ExecState.SUCCESS);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return new R(500 , " 失败" , ExecState.FAILED);
//            }
//        } else {
//            return new R(500 , " 未选择文件或者文件为空" , ExecState.FAILED);
//        }


        // 连接远程服务器
        JSch jsch = new JSch();
        Session session = jsch.getSession(remoteConfig.getRemoteUsername(), remoteConfig.getRemoteHost(), remoteConfig.getRemotePort());
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(remoteConfig.getRemotePassword());
        session.connect();

        // 创建SFTP通道
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();

        // 指定远程服务器的目标路径
        String remotePath = remoteConfig.getRemoteFilePath() + file.getOriginalFilename();


        // 将文件上传到远程服务器
        channel.put(file.getInputStream(), remotePath);

        // 关闭SFTP通道和会话
        channel.disconnect();
        session.disconnect();


        // 将其导入到mysql
        String tableName = file.getOriginalFilename();
        tableService.saveCSV2MySQL(remotePath,tableName.substring(0, tableName.indexOf(".")));

        // 更新table_info 表
        tableService.insertTableInfo(tableName);
        return new R<>(200 , "成功" , ExecState.SUCCESS);
    }


}
