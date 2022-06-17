package com.demo.cloud.ops.gateway.util;

import com.alibaba.fastjson.JSONObject;
import com.demo.cloud.common.core.constant.CommonCode;
import com.demo.cloud.common.core.util.ApiResponse;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * web工具类
 **/
public class WebUtils {

    public static Mono<Void> getAuthFailResult(ServerHttpResponse response, Integer code) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        CommonCode commonCode = CommonCode.getValue(code);
        ApiResponse<?> failed = ApiResponse.failed(commonCode);
        DataBuffer buffer = response.bufferFactory().wrap(JSONObject.toJSONString(failed).getBytes());
        return response.writeWith(Flux.just(buffer));
    }
}
