package com.devmarvin.noticias.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessages {
    private Integer status;
    private String message;
    private Date timestamp;
}
