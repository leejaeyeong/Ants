package com.ssafy.api.controller;


import com.ssafy.api.response.CalendarDetail;
import com.ssafy.api.response.FileInfoRes;
import com.ssafy.api.service.CalendarService;
import com.ssafy.api.service.FileInfoService;
import com.ssafy.db.entity.FileInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Api(value = "달력 상세 API", tags = {"CalendarInfo"})
@RestController
@RequestMapping("/api/v1/calendar")
public class CalendarInfoController {
    @Autowired
    CalendarService calendarService;

    @GetMapping()
    @ApiOperation(value = "캘린더 상세 정보", notes = "캘린더 상세정보 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "정보 응답 성공")
    })
    public ResponseEntity<List<CalendarDetail>> getCalendarLogList (@RequestParam String date, @RequestParam Long departmentId) throws IOException {
        return ResponseEntity.ok().body(calendarService.getCalendarLogList(date, departmentId));
    }
}
