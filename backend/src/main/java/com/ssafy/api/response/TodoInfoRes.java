package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ApiModel("TodoResponse")
public class TodoInfoRes {
    LocalDate date;
    LocalTime time;
    String title;
    String departmentName;
}
