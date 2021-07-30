package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Grp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ApiModel("BoardResponse")
public class BoardRes {
    @ApiModelProperty(name = "Board List")
    Long id;
    String title;
    String content;
    LocalDateTime registrationTime;
    Long typeId;
    String writer;
    int view;

    public BoardRes(Long id, String title, String content, LocalDateTime registrationTime, Long typeId, String writer, int view) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.registrationTime = registrationTime;
        this.typeId = typeId;
        this.writer = writer;
        this.view = view;
    }
}
