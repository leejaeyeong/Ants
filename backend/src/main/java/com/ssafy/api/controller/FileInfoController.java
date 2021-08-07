package com.ssafy.api.controller;


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
import java.util.Map;

@Api(value = "파일 API", tags = {"FileInfo"})
@RestController
@RequestMapping("/api/v1/files")
public class FileInfoController {
    @Autowired
    FileInfoService fileInfoService;

    @GetMapping("/download/{id}")
    @ApiOperation(value = "파일 다운", notes = "파일 다운로드")
    @ApiResponses({
            @ApiResponse(code = 200, message = "파일 다운 성공")
    })
    public ResponseEntity<Resource> downloadFile (@PathVariable Long id) throws IOException {
        Map<String, Object> fileMap = fileInfoService.downloadFile(id);
        Resource resource = (Resource) fileMap.get("resource");
        HttpHeaders headers = (HttpHeaders) fileMap.get("headers");
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @PostMapping("/upload")
    @ApiOperation(value = "파일 업로드", notes = "파일 업로드")
    @ApiResponses({
            @ApiResponse(code = 200, message = "파일 업로드 성공")
    })
    public ResponseEntity<FileInfo> uploadFile (@RequestParam MultipartFile file, @RequestParam String userId) throws IOException {
        return ResponseEntity.ok().body(fileInfoService.uploadFile(file, userId));
    }

//    @GetMapping()
//    @ApiOperation(value = "전체 파일 목록", notes = "전체 파일 목록을 반환한다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "전체 파일 반환 성공")
//    })
//    public ResponseEntity<> findAllFile() {
//
//    }

}
