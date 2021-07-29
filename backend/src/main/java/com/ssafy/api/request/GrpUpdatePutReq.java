package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("GroupUpdatePutRequest")
public class GrpUpdatePutReq {
    @ApiModelProperty(name="그룹 id", example="1")
    Long id;
    @ApiModelProperty(name="그룹 소개", example="삼성 청년 소프트웨어 아카데미")
    String description;
    @ApiModelProperty(name="그룹 관리자", example="ssafy_admin")
    String adminId;
    @ApiModelProperty(name="그룹 이름", example="SSAFY")
    String name;
}
