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
public class WalletHistorys extends BaseModel {

    private Integer currentBalance;
    private Integer deposit;
    private Integer active;

    @ManyToOne
    private Users user;
}
