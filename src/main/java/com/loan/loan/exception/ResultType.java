package com.loan.loan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultType {

    SUCCESS("0000", "success"),

    SYSTEM_ERROR("9000", "system error"),
    // file = 4000
    NOT_EXIST("4001", "file not exist");

    private final String code;
    private final String desc;
}