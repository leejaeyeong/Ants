package com.ssafy.api.response;

import com.ssafy.db.entity.Board;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
    LocalTime time;
    Long typeId;
    String writer;
    String profileLocation;
    int view;
    boolean isMarker;
    String imageLocation;
    List<BoardCommentRes> comments;
    String userName;

    public static BoardRes of(Board board, boolean isMarker, List<BoardCommentRes> comments) {
        BoardRes boardRes = new BoardRes(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getRegistrationTime(),
                board.getTime(),
                board.getBoardType().getId(),
                board.getWriter().getUserId(),
                board.getView(),
                board.getWriter().getProfileLocation(),
                isMarker,
                board.getImageLocation(),
                board.getWriter().getName()
        );
        boardRes.setComments(comments);
        return boardRes;
    }
    public BoardRes(Long id, String title, String content, LocalDateTime registrationTime, LocalTime time,
                    Long typeId, String writer, int view, String profileLocation, boolean isMarker, String imageLocation, String userName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.registrationTime = registrationTime;
        this.time = time;
        this.typeId = typeId;
        this.writer = writer;
        this.view = view;
        this.comments = new ArrayList<>();
        if (typeId == 2) {
            this.profileLocation = "https://us.123rf.com/450wm/thesomeday123/thesomeday1231712/thesomeday123171200009/91087331-%EB%82%A8%EC%84%B1%EC%9D%84%EC%9C%84%ED%95%9C-%EA%B8%B0%EB%B3%B8-%EC%95%84%EB%B0%94%ED%83%80-%ED%94%84%EB%A1%9C%ED%95%84-%EC%95%84%EC%9D%B4%EC%BD%98-%ED%9A%8C%EC%83%89-%EC%82%AC%EC%A7%84-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C-%EC%9E%90-%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8-%EB%B2%A1%ED%84%B0.jpg?ver=6";
            this.userName = "익명";
        } else {
            this.profileLocation = profileLocation;
            this.userName = userName;
        }
        this.isMarker = isMarker;
        this.imageLocation = imageLocation;
    }
}
