package com.timtro247.maven1311.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersDTO extends BaseDTO {

    private String username;
    private String fullName;
    private String identityCard;
    private String phoneNumber;
    private String address;
    private Integer gender;
    private Integer balance;
    private Long roleId;
    private String password;
    private String confirmPassword;
    private Integer active;
}
