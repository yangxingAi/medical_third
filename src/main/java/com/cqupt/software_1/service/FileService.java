package com.cqupt.software_1.service;

import com.cqupt.software_1.common.UploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {


    public UploadResult fileUpload(MultipartFile file, String newName, String disease) throws IOException;
}
