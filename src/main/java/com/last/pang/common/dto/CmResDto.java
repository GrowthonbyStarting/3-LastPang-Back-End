package com.last.pang.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class CmResDto<T> {
    private int code;
    private String message;
    private T data;
}
