package com.devmarvin.noticias.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidationErrors {
    private Map<String, String> errors;
    private Date timestamp;
}
