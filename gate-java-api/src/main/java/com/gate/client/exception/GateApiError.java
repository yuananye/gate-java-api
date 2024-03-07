package com.gate.client.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GateApiError {
    LINK_FAILE("508","交易所连接失败"),
    ADDRESS_FAIL("509","访问不了该地址");

    private  String errorCode;

    private String message;

}
