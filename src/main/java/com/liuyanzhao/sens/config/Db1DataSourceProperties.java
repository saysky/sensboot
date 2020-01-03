package com.liuyanzhao.sens.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author 言曌
 * @date 2020-01-03 17:03
 */
@Component
@Data
@ConfigurationProperties(prefix = "db1")
public class Db1DataSourceProperties implements Serializable {

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    private Integer initialSize;

    private Integer minIdle;

    private Integer maxActive;
}
