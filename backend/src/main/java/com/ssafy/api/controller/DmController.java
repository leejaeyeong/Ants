package com.ssafy.api.controller;

import com.ssafy.api.response.DmRoomRes;
import com.ssafy.api.service.DmRoomService;
import com.ssafy.db.entity.Dm;
import com.ssafy.db.entity.DmRoom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(value = "DM send API", tags = {"Dm"})
@RestController
@RequestMapping("/api/v1/dm")
public class DmController {

    @Autowired
    private DmRoomService dmRoomService;

    @GetMapping("/")
    @ApiOperation(value = "그룹원 목록", notes = "자신 제외 그룹원들의 목록을 받아온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<DmRoomRes>> getDmRoomList(@RequestParam String userId) throws Exception {
        //임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
        List<DmRoomRes> list = dmRoomService.getDmRoomList(userId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/history")
    @ApiOperation(value = "그룹원 목록", notes = "자신 제외 그룹원들의 목록을 받아온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<Dm>> getDmHistory(@RequestParam String roomId) throws Exception {
        //임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
        List<Dm> list = dmRoomService.getDmHistory(roomId);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendDm(
            @RequestBody Dm dm
    ){
        dmRoomService.putDm(dm.getRoomId(), dm.getUser(), dm.getMessage());
        return ResponseEntity.ok("저장 완료");
    }
}

