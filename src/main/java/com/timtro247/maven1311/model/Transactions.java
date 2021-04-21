package com.timtro247.maven1311.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transactions extends BaseModel {

    private Integer cost;

    @ManyToOne
    private Users user;

    @OneToOne
    private Posts post;
}
