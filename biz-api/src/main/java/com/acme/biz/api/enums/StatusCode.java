package com.acme.biz.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 状态码
 * @author : IceBlue
 * @date : 2025/4/13 16:35
 * @see org.springframework.http.HttpStatus
 **/
@Getter
@AllArgsConstructor
public enum StatusCode {
    OK(0,"OK"),
    FAILED(-1,"FAILED"),
    CONTINUE(1,"{status-code.continue}")
    ;

    private final int code;

    private final String message;//可能需要支持国际化

    public String getLocalizedMessage(){
        // FIXME: 2025/4/14 增加国际化支持
        //如果 message 是占位符，翻译成当前 message text
        //否则直接返回 message
        return message;
    }

}
