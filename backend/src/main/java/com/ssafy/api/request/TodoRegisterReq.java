package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@ApiModel("TodoRegisterRequest")
public class TodoRegisterReq {
    @ApiModelProperty(name="할일 시간", example="")
    LocalTime time;
    @ApiModelProperty(name="할일 제목", example="")
    String title;
    @ApiModelProperty(name="할일 적용할 부서", example="")
    Long departmentId;
    @ApiModelProperty(name="유저 id", example="")
    String userId;
}
