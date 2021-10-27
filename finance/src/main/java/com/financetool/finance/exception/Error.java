package com.financetool.finance.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class Error {
    private final Date timestamp;
    private final Integer status;
    private final String error;
    private final String path;
}
