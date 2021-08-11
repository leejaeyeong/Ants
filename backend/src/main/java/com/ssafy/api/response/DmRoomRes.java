package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("DmRoomResponse")
public class DmRoomRes {
    @ApiModelProperty(name="User")
    String userId;
    @ApiModelProperty(name="User")
    String roomId;
    @ApiModelProperty(name="User")
    String username;
    @ApiModelProperty(name="User")
    String departmentName;
    @ApiModelProperty(name="User")
    String profileLocation;
}
