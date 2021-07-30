package com.ssafy.api.controller;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.BoardRes;
import com.ssafy.api.service.BoardService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.BoardType;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "게시판 API", tags = {"Board"})
@RestController
@RequestMapping("/api/v1/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/type")
    @ApiOperation(value = "게시판 카테고리 목록", notes = "게시판 카테고리 목록을 반환한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "보드 타입 반환 성공")
    })
    public ResponseEntity<List<BoardType>> getBoardTypeList() {
        return ResponseEntity.ok().body(boardService.getBoardTypeList());
    }

    @GetMapping("/type/{id}")
    @ApiOperation(value = "특정 카테고리 게시글 목록", notes = "해당 카테고리의 게시판 목록을 반환한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "보드 반환 성공")
    })
    public ResponseEntity<List<BoardRes>> getBoardListByTypeId(@PathVariable Long id) {
        return ResponseEntity.ok().body(boardService.getBoardListByTypeId(id));
    }

    @GetMapping()
    @ApiOperation(value = "전체 게시글 목록", notes = "전체 게시글목록을 반환한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "보드 반환 성공")
    })
    public ResponseEntity<List<BoardRes>> getBoardList() {
        return ResponseEntity.ok().body(boardService.getBoardList());
    }

    @GetMapping("/marker")
    @ApiOperation(value = "사용자가 마크한 게시글 목록", notes = "사용자가 마크한 게시글 목록을 반환한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "보드 반환 성공")
    })
    public ResponseEntity<List<BoardRes>> getBoardListByMarker(@RequestParam String userId) {
        return ResponseEntity.ok(boardService.getBoardListByMarker(userId));
    }



}
