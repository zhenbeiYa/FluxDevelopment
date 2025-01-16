package org.example.error;

import org.example.enmu.BusinessEnum;

import java.io.Serial;

/**
 * @author 23133
 * @version 1.0
 * @description: 自定义异常类, 用于流式错误处理
 * @date 2025/1/15 10:33
 */
public class BusinessException extends Exception {

    static final long serialVersionUID = 1;

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
