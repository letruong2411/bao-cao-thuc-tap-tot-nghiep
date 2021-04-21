package com.timtro247.maven1311.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.timtro247.maven1311.model.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WalletHistorysDTO extends BaseDTO {

    @JsonProperty("current_balance")
    private Integer currentBalance;

    @JsonProperty("deposit")
    private Integer deposit;

    @JsonProperty("active")
    private Integer active;

    @JsonProperty("user_id")
    private Long userId;
}
