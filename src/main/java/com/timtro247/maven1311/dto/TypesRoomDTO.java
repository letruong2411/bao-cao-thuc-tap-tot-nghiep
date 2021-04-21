package com.timtro247.maven1311.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.timtro247.maven1311.model.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TypesRoomDTO extends BaseDTO {

    @JsonProperty("type_name")
    private String typeName;
}
