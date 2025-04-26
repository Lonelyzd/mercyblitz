package com.acme.biz.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * API 请求对象
 * <T> 模型对象类型
 *
 * @author : IceBlue
 * @date : 2025/4/12 22:18
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class HttpApiRequest<T> extends HttpApi<T> {


}
