package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Grp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("GroupResponse")
public class GrpRes extends BaseResponseBody {
    @ApiModelProperty(name = "Group List")
    List<Grp> list;
    public static GrpRes of(List<Grp> grp) {
        GrpRes res = new GrpRes();
        res.list = grp;
        return res;
    }
}
