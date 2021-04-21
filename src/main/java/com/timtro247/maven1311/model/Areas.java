package com.timtro247.maven1311.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Areas extends BaseModel {

    private String areaName;
    private Integer status;

    @OneToMany(mappedBy = "area")
    private Set<Rooms> rooms;
}
