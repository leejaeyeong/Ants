package com.ssafy.api.controller;

import com.ssafy.Room;
import com.ssafy.RoomManager;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Api(value = "회의 API", tags = {"Conferences"})
@RestController
@RequestMapping("/api/v1/rtc")
public class ConferenceController {

    @Autowired
    private RoomManager roomManager;

    @GetMapping()
    @ApiOperation(value = "회의룸 대기실", notes = "회의룸 방 목록을 불러온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String[] getConferenceRoom() {
        ConcurrentMap<String, Room> rooms = roomManager.getRoomList();
        List<String> list = new ArrayList<>();
        Iterator<String> iterator = rooms.keySet().iterator();
        while(iterator.hasNext()){
            list.add(iterator.next());
        }
        for(int i = 0; i < list.size(); i++){
            System.out.println("room" + i + ": " + list.get(i));
        }
        return list.toArray(new String[0]);
    }
}
