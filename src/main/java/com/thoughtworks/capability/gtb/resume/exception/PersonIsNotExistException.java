package com.thoughtworks.capability.gtb.resume.exception;

// GTB: - 通常自定义异常体系会从 RuntimeException 开始继承
public class PersonIsNotExistException extends Throwable {
    public PersonIsNotExistException(String message) {
        super(message);
    }
}
