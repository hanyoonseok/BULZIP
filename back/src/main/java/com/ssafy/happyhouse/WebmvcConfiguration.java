package com.ssafy.happyhouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
//@EnableAspectJAutoProxy // AOP 사용
@MapperScan(basePackages = {"com.ssafy.**.mapper"})
public class WebmvcConfiguration {

}
