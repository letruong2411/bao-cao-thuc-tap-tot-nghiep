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
public class Prices extends BaseModel {

    private String priceName;
    private Integer minPrice;
    private  Integer maxPrice;
    private Integer status;

    @OneToMany(mappedBy = "price")
    private Set<Rooms> rooms;
}
