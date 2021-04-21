package com.timtro247.maven1311.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TypeRooms extends BaseModel {

    private String typeName;

    @OneToMany(mappedBy = "typeRoom")
    private List<Rooms> rooms;
}
