package com.gloibgroup.urbanswap.dtos.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;
    private int code;
    private T data;
}
