package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserTeamMappingRequest")
public class UserTeamMappingPutReq {
    @ApiModelProperty(name="유저 ID", example="ssafy_web")
    String userId;
    @ApiModelProperty(name="그룹 ID", example="1")
    Long teamId;
}
