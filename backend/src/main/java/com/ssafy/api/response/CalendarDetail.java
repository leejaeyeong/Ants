package com.ssafy.api.response;

import com.ssafy.db.entity.Board;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@ApiModel("CalendarDetailResponse")
public class CalendarDetail {
    @ApiModelProperty(name = "Board Log Information")
    LocalTime time;
}
