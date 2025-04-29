package com.acme.biz.api;

import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * @author : IceBlue
 * @date : 2025/4/13 16:08
 **/
@Data
public class HttpApi<T> implements Serializable {

//    @Deprecated
//    private Map<String, String> headers;
//
//    @Deprecated
//    private MultiValueMap<String, String> mateData;

    @Valid
    private T body;

 /*   public Map<String, String> getHeaders() {
        if (headers == null) return Collections.emptyMap();
        return headers;
    }*/
}
