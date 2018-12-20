package com.tickychat.server.base.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Angus
 * @date 2018/12/15
 */
@Configuration
@MapperScan(basePackages = "com.tickychat.server.mapper")
public class MapperScanConfig {
}
