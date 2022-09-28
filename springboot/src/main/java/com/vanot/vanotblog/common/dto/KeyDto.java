package com.vanot.vanotblog.common.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class KeyDto {
    @NotBlank
    private String key;
}
