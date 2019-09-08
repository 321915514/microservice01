package com.day01.consumerfeign.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MyConfig {
}
