package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 23133
 * @version 1.0
 * @description:
 * @date 2025/1/5 10:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {
//    名称
    private String name;
//    年龄
    private Integer age;
//    性别
    private String sex;
}
