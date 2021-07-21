package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Team;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("TeamResponse")
public class TeamRes extends BaseResponseBody {
    @ApiModelProperty(name = "Group List")
    List<Team> list;
    public static TeamRes of(List<Team> team) {
        TeamRes res = new TeamRes();
        res.list = team;
        return res;
    }
}
