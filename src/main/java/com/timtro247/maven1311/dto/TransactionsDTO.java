package com.timtro247.maven1311.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.timtro247.maven1311.model.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
public class TransactionsDTO extends BaseDTO {

    @JsonProperty("cost")
    private Integer cost;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("post_id")
    private Long postId;
}
