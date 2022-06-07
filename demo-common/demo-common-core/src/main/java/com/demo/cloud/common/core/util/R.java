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
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private T data;

    public static <T> R<T> ok() {
        return restR(null, CommonCode.SUCCESS.getCode(), CommonCode.SUCCESS.getMsg());
    }

    public static <T> R<T> ok(T data) {
        return restR(data, CommonCode.SUCCESS.getCode(), CommonCode.SUCCESS.getMsg());
    }

    public static <T> R<T> ok(T data, String msg) {
        return restR(data, CommonCode.SUCCESS.getCode(), msg);
    }

    public static <T> R<T> failed() {
        return restR(null, CommonCode.FAILURE.getCode(), CommonCode.FAILURE.getMsg());
    }

    public static <T> R<T> failed(String msg) {
        return restR(null, CommonCode.FAILURE.getCode(), msg);
    }

    public static <T> R<T> failed(T data) {
        return restR(data, CommonCode.FAILURE.getCode(), CommonCode.FAILURE.getMsg());
    }

    public static <T> R<T> failed(T data, String msg) {
        return restR(data, CommonCode.FAILURE.getCode(), msg);
    }

    public static <T> R<T> failed(CommonCode commonCode) {
        R<T> apiR = new R<>();
        apiR.setCode(commonCode.getCode());
        apiR.setMsg(commonCode.getMsg());
        return apiR;
    }

    public static <T> R<T> failed(CommonCode commonCode, String msg) {
        R<T> apiR = new R<>();
        apiR.setCode(commonCode.getCode());
        apiR.setMsg(msg);
        return apiR;
    }

    public static <T> R<T> failed(T data, CommonCode commonCode) {
        R<T> apiR = new R<>();
        apiR.setCode(commonCode.getCode());
        apiR.setMsg(commonCode.getMsg());
        apiR.setData(data);
        return apiR;
    }
    private static <T> R<T> restR(T data, int code, String msg) {
        R<T> apiR = new R<>();
        apiR.setCode(code);
        apiR.setData(data);
        apiR.setMsg(msg);
        return apiR;
    }

}
