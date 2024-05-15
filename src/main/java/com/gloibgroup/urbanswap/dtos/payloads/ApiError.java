package com.gloibgroup.urbanswap.dtos.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private String status;
    private int code;
    private String message;
}
