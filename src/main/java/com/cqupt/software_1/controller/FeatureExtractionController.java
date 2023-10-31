package com.cqupt.software_1.controller;


import com.cqupt.software_1.common.RunPyEntity;
import com.cqupt.software_1.common.RunPyEntity2;
import com.cqupt.software_1.common.RunPyR;
import com.cqupt.software_1.common.RunPyR2;
import com.cqupt.software_1.entity.UserLog;
import com.cqupt.software_1.service.UserLogService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/featureExtraction")
public class FeatureExtractionController {


    @Autowired
    private UserLogService userLogService;


    /**
     *
     * 这是一张表的特征抽取
     * @param runPyEntity
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */


    @PostMapping("/runPy")
    public RunPyR runAi(@RequestBody RunPyEntity runPyEntity , HttpServletRequest request) throws URISyntaxException, IOException {

        // 算法服务地址
        String baseUri = "http://localhost:5000/";
        // 运行哪个算法就是哪个路径
        URI uri = new URI(baseUri+runPyEntity.getAiName());
        System.out.println(runPyEntity);
        // 创建Http 请求post
        HttpPost httpPost = new HttpPost(uri);
        HttpClient httpClient = HttpClients.createDefault();
        // 将参数列表转换为json
        List<String> runParams = runPyEntity.getRunParams();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(runPyEntity);
        httpPost.setEntity(new StringEntity(json));
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpResponse response = httpClient.execute(httpPost);
        String responseBody = EntityUtils.toString(response.getEntity());
        JsonNode jsonNode = objectMapper.readValue(responseBody,JsonNode.class);
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        // 操作日志
        UserLog userLog = new UserLog();
        userLog.setUid(userId);
        userLog.setOpTime(new Date());
        userLog.setUsername((String) request.getSession().getAttribute("username"));
        userLog.setOpType("运行算法名为: " + runPyEntity.getAiName());
        userLogService.save(userLog);

        return new RunPyR<>(200,"成功",jsonNode);
    }


    /**
     *
     * 这是两张表的特征抽取  两张表跑一个算法
     *
     * @param
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    @PostMapping("/runPy2")
    public RunPyR runPy2(@RequestBody RunPyEntity2 runPyEntity2,  HttpServletRequest request) throws URISyntaxException, IOException {

        // 算法服务地址
        String baseUri = "http://localhost:5000/";
        // 运行哪个算法就是哪个路径
        URI uri = new URI(baseUri+runPyEntity2.getAiName());
        // 创建Http 请求post
        HttpPost httpPost = new HttpPost(uri);
        HttpClient httpClient = HttpClients.createDefault();
        // 将参数列表转换为json
        List<String> runParams = runPyEntity2.getRunParams();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(runPyEntity2);
        httpPost.setEntity(new StringEntity(json));
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpResponse response = httpClient.execute(httpPost);
        String responseBody = EntityUtils.toString(response.getEntity());
        JsonNode jsonNode = objectMapper.readValue(responseBody,JsonNode.class);

        System.out.println(jsonNode);


        // 操作日志
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        UserLog userLog = new UserLog();
        userLog.setUid(userId);
        userLog.setOpTime(new Date());
        userLog.setUsername((String) request.getSession().getAttribute("username"));
        userLog.setOpType("多疾病特征抽取运行算法名为: " + runPyEntity2.getAiName());
        userLogService.save(userLog);

        return new RunPyR<>(200,"成功",jsonNode);
    }


}
