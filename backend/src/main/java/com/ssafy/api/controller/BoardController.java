package com.ssafy.api.controller;

import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.api.request.CommentRegisterPostReq;
import com.ssafy.api.response.BoardCommentRes;
import com.ssafy.api.response.BoardRes;
import com.ssafy.api.service.BoardService;
import com.ssafy.db.entity.BoardType;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping("/{id}")
    @ApiOperation(value = "게시글 id로 게시글 상세 조회", notes = "게시글에 대한 상세정보를 반환한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "상세정보 반환 성공")
    })
    public ResponseEntity<BoardRes> getBoardDetail(@PathVariable Long id, @RequestParam String userId) {
        return ResponseEntity.ok(boardService.getBoardDetail(id, userId));
    }

    @GetMapping("/userId")
    @ApiOperation(value = "글 작성자로 게시글 조회", notes = "글 작성자가 userId인 글 목록을 반환한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "댓글 반환 성공")
    })
    public ResponseEntity<List<BoardRes>> getBoardByWriterId(@RequestParam String userId) {
        return ResponseEntity.ok(boardService.getBoardByWriterId(userId));
    }

    @GetMapping("/title")
    @ApiOperation(value = "제목으로 게시글 검색", notes = "글 제목이 title인 게시글을 반환한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "댓글 반환 성공")
    })
    public ResponseEntity<List<BoardRes>> getBoardByTitle(@RequestParam String title) {
        return ResponseEntity.ok(boardService.getBoardByTitle(title));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "게시글 조회수 증가", notes = "게시글 상세 정보를 볼 때 조회수가 증가한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회수 증가")
    })
    public ResponseEntity increaseViewCnt(@PathVariable Long id) {
        boardService.increaseViewCnt(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    @ApiOperation(value = "게시글 등록", notes = "게시글을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "게시글 등록 성공")
    })
    public ResponseEntity<BoardRes> registerBoard(
            @RequestParam Long type,
            @RequestParam String writer,
            @RequestParam String title,
            @RequestParam String content
            ) throws IOException {
        return ResponseEntity.ok(boardService.registerBoard(BoardRegisterPostReq.of(type, writer, title, content, null)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "게시글 삭제", notes = "게시글 번호로 게시글 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "게시글 삭제 성공")
    })
    public ResponseEntity<BoardRes> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comment")
    @ApiOperation(value = "게시글 댓글 생성", notes = "게시글에 대한 댓글 생성")
    @ApiResponses({
            @ApiResponse(code = 200, message = "게시글 댓글 생성 성공")
    })
    public ResponseEntity<BoardCommentRes> registerComment(@PathVariable Long id, @RequestBody CommentRegisterPostReq commentRegisterPostReq) {
        return ResponseEntity.ok().body(boardService.registerComment(id, commentRegisterPostReq));
    }

    @PutMapping("/{id}/marker")
    @ApiOperation(value = "게시글 마크 토글", notes = "게시글에 대한 마크를 토글한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "토글 결과 반환")
    })
    public ResponseEntity<String> toggleMarker(@PathVariable Long id, @RequestParam String userId) {
        return ResponseEntity.ok().body(boardService.boardMarkerToggle(id, userId) ? "마커 등록 성공" : "마커 삭제 성공");
    }

}
