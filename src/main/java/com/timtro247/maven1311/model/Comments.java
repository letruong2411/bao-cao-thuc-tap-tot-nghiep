package com.timtro247.maven1311.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comments extends BaseModel {

    private String content;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Posts post;
}
