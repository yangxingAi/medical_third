package com.cqupt.software_1.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "remote")
@Data
public class RemoteConfig {

    private String remoteHost;
    private int remotePort;
    private String remoteUsername;
    private String remotePassword;
    private String remoteFilePath;
}
