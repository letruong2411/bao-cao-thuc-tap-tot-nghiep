package com.timtro247.maven1311.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class AccountsDTO extends BaseDTO {

    @JsonProperty("username")
    @NotBlank(message = "username is required")
    private String username;

    @JsonProperty("password")
    @NotBlank(message = "password is required")
    private String password;

    @JsonProperty("active")
    private Integer active;
}
