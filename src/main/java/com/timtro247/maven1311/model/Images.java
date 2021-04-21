package com.timtro247.maven1311.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Images extends BaseModel {

    private String fileName;

    @ManyToOne
    private Rooms room;
}
