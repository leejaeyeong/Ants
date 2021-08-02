package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@ApiModel("TotalHourResponse")
public class TotalHourRes {
    @ApiModelProperty(name="이번주 근무 시간", example="{ totalHour : 52 }")
    Integer totalHour;

    public static TotalHourRes of (Integer hour) {
        TotalHourRes trs = new TotalHourRes();
        trs.setTotalHour(hour);
        return trs;
    }
}
