package com.timtro247.maven1311.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.timtro247.maven1311.model.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomsDTO extends BaseDTO {

    @JsonProperty("cost")
    private Integer cost;

    @JsonProperty("max_person_number")
    private Integer maxPersonNumber;

    @JsonProperty("acreage")
    private Integer acreage;

    @JsonProperty("description")
    private String description;

    @JsonProperty("address")
    private String address;

    @JsonProperty("level")
    private Integer level;

    @JsonProperty("type_room_id")
    private Long typeRoomId;

    @JsonProperty("area_id")
    private Long areaId;
}
