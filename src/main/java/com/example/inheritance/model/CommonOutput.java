package com.example.inheritance.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonOutput<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorOutput error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

}
