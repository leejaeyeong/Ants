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
@ApiModel("BoardLogResponse")
public class BoardLog extends CalendarDetail {
    @ApiModelProperty(name = "Board Log Information")
    Long id;
    String title;
    LocalTime time;
    String writer;
    String profileLocation;
    int view;
    int commentCount;
    String type = "board-write";

   public static BoardLog of (Board board, int commentCount) {
       BoardLog boardLog = new BoardLog();
       boardLog.setCommentCount(commentCount);
       boardLog.setId(board.getId());
       boardLog.setTitle(board.getTitle());
       boardLog.setTime(board.getTime());
       boardLog.setWriter(board.getWriter().getUserId());
       boardLog.setProfileLocation(board.getWriter().getProfileLocation());
       boardLog.setView(board.getView());
       return boardLog;
   }
}
