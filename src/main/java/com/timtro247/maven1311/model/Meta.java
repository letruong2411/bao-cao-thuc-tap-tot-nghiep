package com.timtro247.maven1311.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meta<T> {

    private Pagination pagination;
    private String message;
    private Map<String, String> errors;

    public Meta(String message) {
        this.message = message;
    }

}
