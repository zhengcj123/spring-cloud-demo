/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.demo.cloud.common.core.util;

import com.demo.cloud.common.core.constant.CommonCode;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -2486222969242464024L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private T data;

    public static <T> ApiResponse<T> success() {
        return restR(null, CommonCode.SUCCESS.getCode(), CommonCode.SUCCESS.getMsg());
    }

    public static <T> ApiResponse<T> success(T data) {
        return restR(data, CommonCode.SUCCESS.getCode(), CommonCode.SUCCESS.getMsg());
    }

    public static <T> ApiResponse<T> success(T data, String msg) {
        return restR(data, CommonCode.SUCCESS.getCode(), msg);
    }

    public static <T> ApiResponse<T> failed() {
        return restR(null, CommonCode.FAILURE.getCode(), CommonCode.FAILURE.getMsg());
    }

    public static <T> ApiResponse<T> failed(String msg) {
        return restR(null, CommonCode.FAILURE.getCode(), msg);
    }

    public static <T> ApiResponse<T> failed(T data) {
        return restR(data, CommonCode.FAILURE.getCode(), CommonCode.FAILURE.getMsg());
    }

    public static <T> ApiResponse<T> failed(T data, String msg) {
        return restR(data, CommonCode.FAILURE.getCode(), msg);
    }

    public static <T> ApiResponse<T> failed(CommonCode commonCode) {
        ApiResponse<T> apiApiResponse = new ApiResponse<>();
        apiApiResponse.setCode(commonCode.getCode());
        apiApiResponse.setMsg(commonCode.getMsg());
        return apiApiResponse;
    }

    public static <T> ApiResponse<T> failed(CommonCode commonCode, String msg) {
        ApiResponse<T> apiApiResponse = new ApiResponse<>();
        apiApiResponse.setCode(commonCode.getCode());
        apiApiResponse.setMsg(msg);
        return apiApiResponse;
    }

    public static <T> ApiResponse<T> failed(T data, CommonCode commonCode) {
        ApiResponse<T> apiApiResponse = new ApiResponse<>();
        apiApiResponse.setCode(commonCode.getCode());
        apiApiResponse.setMsg(commonCode.getMsg());
        apiApiResponse.setData(data);
        return apiApiResponse;
    }
    private static <T> ApiResponse<T> restR(T data, int code, String msg) {
        ApiResponse<T> apiApiResponse = new ApiResponse<>();
        apiApiResponse.setCode(code);
        apiApiResponse.setData(data);
        apiApiResponse.setMsg(msg);
        return apiApiResponse;
    }

}
