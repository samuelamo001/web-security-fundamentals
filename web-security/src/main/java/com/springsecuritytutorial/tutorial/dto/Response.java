package com.springsecuritytutorial.tutorial.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private final String status;
    private final String token;

}
