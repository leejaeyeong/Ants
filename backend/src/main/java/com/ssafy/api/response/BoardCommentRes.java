package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("BoardCommentResponse")
public class BoardCommentRes {
    @ApiModelProperty(name = "Comment List")
    String comment;
    LocalDateTime registrationTime;
    String writer;

    public BoardCommentRes(String comment, LocalDateTime registrationTime, String writer) {
        this.comment = comment;
        this.registrationTime = registrationTime;
        this.writer = writer;
    }
}
