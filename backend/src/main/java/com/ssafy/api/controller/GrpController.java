package com.ssafy.api.controller;

import com.ssafy.api.request.GrpRegisterPostReq;
import com.ssafy.api.request.GrpUpdatePutReq;
import com.ssafy.api.response.GrpRes;
import com.ssafy.api.service.GrpService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Grp;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "그룹 API", tags = {"Group"})
@RestController
@RequestMapping("/api/v1/group")
public class GrpController {
    @Autowired
    GrpService grpService;

    //전체 그룹 조회
    @GetMapping
    @ApiOperation(value = "그룹 전체 목록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 409, message = "조회 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> getAllTeams(){
        List<Grp> grps = grpService.getAll();

        if(grps != null) return ResponseEntity.status(200).body(GrpRes.of(grps));
        return ResponseEntity.status(409).body(BaseResponseBody.of(409, "Search Failed"));
    }

    //관리자 그룹 조회
    @GetMapping(value = "/{adminId}")
    @ApiOperation(value = "관리자별 그룹 목록", notes = "<strong>관리자 아이디</strong>를 통해 존재하는 그룹을 확인한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 409, message = "조회 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> getTeamsByAdminId( @PathVariable String adminId){
        List<Grp> grps = grpService.getGroupByAdminId(adminId);

        if(grps != null) return ResponseEntity.status(200).body(GrpRes.of(grps));
        return ResponseEntity.status(409).body(BaseResponseBody.of(409, "Search Failed"));
    }


    @PostMapping()
    @ApiOperation(value = "그룹 생성", notes = "<strong>그룹명과 그룹소개, 관리자 id</strong>를 통해 그룹을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> register(
            @RequestBody @ApiParam(value="그룹 생성 정보", required = true) GrpRegisterPostReq registerInfo) {

        //임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
        Grp grp = grpService.createGroup(registerInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    // 그룹 수정
    @PutMapping("/update")
    @ApiOperation(value = "그룹 정보 수정", notes = "그룹 정보를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "수정 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> updateUserInfo( @RequestBody GrpUpdatePutReq teamUpdateInfo) {

        if(grpService.updateGroup(teamUpdateInfo)){
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
        }
        return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Update failed"));
    }

    //그룹 삭제
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "그룹 삭제", notes = "<strong>그룹 아이디</strong>를 통해 회원 정보를 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> deleteUser(@PathVariable Long id) {
       
        if(grpService.deleteGroup(id)) return ResponseEntity.status(204).body(BaseResponseBody.of(204, "Delete Success"));
        return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Delete Fail"));
    }
}
