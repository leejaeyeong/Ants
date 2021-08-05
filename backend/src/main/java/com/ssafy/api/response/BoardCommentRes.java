package com.ssafy.api.response;

import com.ssafy.db.entity.BoardComment;
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
    String profileLocation;

    public BoardCommentRes(String comment, LocalDateTime registrationTime, String writer, String profileLocation) {
        this.comment = comment;
        this.registrationTime = registrationTime;
        this.writer = writer;
        this.profileLocation = profileLocation;
    }

    public static BoardCommentRes of (BoardComment boardComment) {
        BoardCommentRes res = new BoardCommentRes(
                boardComment.getComment(),
                boardComment.getRegistrationTime(),
                boardComment.getWriter().getUserId(),
                boardComment.getWriter().getProfileLocation()
        );
        return res;

    }
}
