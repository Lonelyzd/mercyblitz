package com.acme.biz.api;

import lombok.Data;
import org.springframework.util.MultiValueMap;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

/**
 * @author : IceBlue
 * @date : 2025/4/13 16:08
 **/
@Data
public class HttpApi<T> {

    @Deprecated
    private Map<String, String> headers;

    @Deprecated
    private MultiValueMap<String, String> mateData;

    @Valid
    private T body;

    public Map<String, String> getHeaders() {
        if (headers == null) return Collections.emptyMap();
        return headers;
    }
}
