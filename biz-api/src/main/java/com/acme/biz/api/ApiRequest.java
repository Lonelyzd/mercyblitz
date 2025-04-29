package com.acme.biz.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * API 请求对象
 * <T> 模型对象类型
 *
 * @author : IceBlue
 * @date : 2025/4/12 22:18
 **/
@Data
@ToString(callSuper =true)
@EqualsAndHashCode(callSuper = true)
public class ApiRequest<T> extends HttpApi<T> {


}
