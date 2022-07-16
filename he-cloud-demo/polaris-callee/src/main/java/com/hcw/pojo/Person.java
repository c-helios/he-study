package com.hcw.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hcw
 * @date 2022-07-09
 */
@Data
@Component
@ConfigurationProperties(prefix = "teacher")
public class Person {

    private String name;

    private int age;
}
