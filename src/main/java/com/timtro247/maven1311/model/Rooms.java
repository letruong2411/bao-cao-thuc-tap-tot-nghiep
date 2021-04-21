package com.timtro247.maven1311.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rooms extends BaseModel {

    private Integer cost;
    private Integer maxPersonNumber;
    private Integer acreage;
    private String description;
    private String address;
    private String roomName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

    @ManyToOne
    private TypeRooms typeRoom;

    @OneToMany(mappedBy = "room",fetch = FetchType.EAGER)
    private Set<Posts> posts;

    @ManyToOne
    private Areas area;

    @ManyToOne
    private Prices price;

    @OneToMany(mappedBy = "room",fetch = FetchType.EAGER)
    private Set<Images> images;
}
