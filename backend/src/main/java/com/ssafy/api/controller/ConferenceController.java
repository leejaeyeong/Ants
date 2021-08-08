package com.ssafy.api.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.ssafy.Room;
import com.ssafy.RoomManager;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.UserRes;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.schema.Entry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@Api(value = "회의 API", tags = {"Conferences"})
@RestController
@RequestMapping("/api/v1/rtc")
public class ConferenceController {

    @Autowired
    private RoomManager roomManager;

    @GetMapping("/")
    @ApiOperation(value = "회의룸 대기실", notes = "회의룸 방 목록을 불러온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<String[]>> getConferenceRoom() {
        List<String[]> list = new ArrayList<>();
        System.out.println("갯수 : " + roomManager.getRoomList().size());
        for (Map.Entry<String, Room> s : roomManager.getRoomList().entrySet()) {
            if(s.getValue().getParticipants().size() == 0) {
                roomManager.removeRoom(s.getValue());
                continue;
            }
            String[] arr = new String[3];
            arr[0] = s.getKey();
            arr[1] = s.getValue().getManager();
            arr[2] = s.getValue().getDepartment().getDepartmentName();

            list.add(arr);
        }
        return ResponseEntity.status(200).body(list);
    }
}
