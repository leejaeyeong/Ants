package com.ssafy.api.response;

import com.ssafy.db.entity.Board;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel("BoardResponse")
public class BoardRes {
    @ApiModelProperty(name = "Board Information")
    Long id;
    String title;
    String content;
    LocalDateTime registrationTime;
    Long typeId;
    String writer;
    String profileLocation;
    int view;
    boolean isMarker;
    List<BoardCommentRes> comments;

    public static BoardRes of(Board board, boolean isMarker, List<BoardCommentRes> comments) {
        BoardRes boardRes = new BoardRes(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getRegistrationTime(),
                board.getBoardType().getId(),
                board.getWriter().getUserId(),
                board.getView(),
                board.getWriter().getProfileLocation(),
                isMarker
        );
        boardRes.setComments(comments);
        return boardRes;
    }
    public BoardRes(Long id, String title, String content, LocalDateTime registrationTime,
                    Long typeId, String writer, int view, String profileLocation, boolean isMarker) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.registrationTime = registrationTime;
        this.typeId = typeId;
        this.writer = writer;
        this.view = view;
        this.comments = new ArrayList<>();
        this.profileLocation = profileLocation;
        this.isMarker = isMarker;
    }
}
