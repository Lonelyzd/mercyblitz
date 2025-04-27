package com.acme.biz.api;

import com.acme.biz.api.enums.StatusCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : IceBlue
 * @date : 2025/4/13 16:04
 **/

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiResponse<T> extends HttpApi<T> {

    private int code;

    private String message;

    public static <T> ApiResponse<T> of(T body, StatusCode statusCode) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setBody(body);
        response.setCode(statusCode.getCode());
        response.setMessage(statusCode.getLocalizedMessage());
        return response;
    }

    public static <T> ApiResponse<T> ok(T body) {
        return of(body, StatusCode.OK);
    }

    public static <T> ApiResponse<T> failed(T body) {
        return of(body, StatusCode.FAILED);
    }

    public static <T> ApiResponse<T> failed(T body, String errorMessage) {
        final ApiResponse<T> response = of(body, StatusCode.FAILED);
        response.setMessage(errorMessage);
        return response;
    }

    public static class Builder<T> {
        private int code;

        private String message;

        public Builder<T> code(int code) {
            this.code = code;
            return this;
        }
    }

}
