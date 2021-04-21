package com.timtro247.maven1311.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("status")
    private Integer status;
}
