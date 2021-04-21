package com.timtro247.maven1311.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.timtro247.maven1311.model.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostsDTO extends BaseDTO {

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

//    @JsonFormat(pattern = "mm/dd/yyyy")
    @JsonProperty("start_time")
    private LocalDate startTime;

    @JsonProperty("save_post_day_number")
    private Integer savePostDayNumber;

    @JsonProperty("post_type")
    private Integer postType;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("room_id")
    private Long roomId;
}
