package com.example.demo.entity;
import lombok.Data;

/**
 * 验证码类
 */
@Data
public class VerifyCode {

    private String code;

    private byte[] imgBytes;

    private long expireTime;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

}
