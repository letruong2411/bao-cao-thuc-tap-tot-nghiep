package com.timtro247.maven1311.model.security;

import com.timtro247.maven1311.model.BaseModel;
import com.timtro247.maven1311.model.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Roles extends BaseModel {

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRoles> userRoles = new HashSet<>();

    public Roles(String name) {
        this.name = name;
    }
}
