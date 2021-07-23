package com.vanot.vanotblog.common.lang;

public enum ResultCode {
    SUCCESS(200),ERROR(1000);

    int code;

    ResultCode(int code){
        this.code = code;
    }
}
