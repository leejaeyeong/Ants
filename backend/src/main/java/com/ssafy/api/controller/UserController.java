package com.ssafy.api.controller;

import com.ssafy.api.request.*;
import com.ssafy.api.response.AttendanceListRes;
import com.ssafy.api.response.AttendanceRes;
import com.ssafy.api.response.TotalHourRes;
import com.ssafy.db.entity.Attendance;
import com.ssafy.db.entity.BaseEntity;
import com.ssafy.db.entity.Department;
import com.ssafy.db.repository.DepartmentRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping()
	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> register(
//			@RequestBody @ApiParam(value="회원가입 정보", required = true) UserRegisterPostReq registerInfo,
			@RequestParam MultipartFile profile,
			@RequestParam String userId,
			@RequestParam String name,
			@RequestParam String password,
			@RequestParam String email,
			@RequestParam Long department) throws Exception {
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.

		User user = userService.createUser(UserRegisterPostReq.of(userId, password, name, email, department, profile));

		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/me")
	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserRes> getUserInfo(@ApiIgnore Authentication authentication) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
		System.out.println("user: " + user);


		return ResponseEntity.status(200).body(UserRes.of(user));
	}

	@GetMapping()
	@ApiOperation(value = "가입한 모든 회원 정보 조회", notes = "가입한 모든 회원 정보를 조회한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<UserRes>> getUserAll() {
		return ResponseEntity.status(200).body(userService.findAll());
	}

	// 유저 정보
	@GetMapping(value = "/{userId}")
	@ApiOperation(value = "유저 정보", notes = "<strong>아이디</strong>를 통해 존재하는 회원을 확인한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "존재하지 않는 유저"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 409, message = "이미 존재하는 유저"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> checkDuplicate(@PathVariable String userId){
		User user = userService.getUserByUserId(userId);
		if(user == null) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		return ResponseEntity.status(409).body(BaseResponseBody.of(409, "Duplicate"));
	}

	// 유저 정보 수정
	@PutMapping("/{userId}")
	@ApiOperation(value = "유저 정보 수정", notes = "유저 정보를 수정한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})

	public ResponseEntity<? extends BaseResponseBody> updateUserInfo(@PathVariable String userId, @RequestParam String email, @RequestParam MultipartFile profile) throws IOException {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		System.out.println(profile + " 프로필 정보를");
		User user = userService.getUserByUserId(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		userService.updateUser(user, email, profile);
		return ResponseEntity.ok().build();
	}

	// 유저 정보 삭제
	@DeleteMapping(value = "/{userId}")
	@ApiOperation(value = "유저 정보 삭제 (탈퇴)", notes = "<strong>아이디</strong>를 통해 회원 정보를 삭제한다.")
	@ApiResponses({
			@ApiResponse(code = 204, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> deleteUser(@PathVariable String userId, @ApiIgnore Authentication authentication) {
		if(userService.deleteUser(userId)) {
			return ResponseEntity.status(204).body(BaseResponseBody.of(204, "Delete Success"));
		}
		return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Delete Fail"));
	}

	// 유저 그룹 소속여부 확인
	@GetMapping(value = "/checkTeam/{userId}")
	@ApiOperation(value = "유저 그룹 소속여부 확인", notes = "<strong>아이디</strong>를 통해 회원이 현재 그룹에 가입되어 있는지 확인한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "그룹이 존재하지 않는 유저"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 409, message = "이미 그룹이 존재하는 유저"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> checkTeam(@PathVariable String userId){
		User user = null;
		boolean checkTeam = userService.getTeamByUserId(userId);
		if(checkTeam) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		return ResponseEntity.status(409).body(BaseResponseBody.of(409, "isExisted"));
	}

	// 유저 그룹 매핑
	@PutMapping("/teamMapping")
	@ApiOperation(value = "유저 그룹 매핑", notes = "유저에 그룹을 할당한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "해당 그룹 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})

	public ResponseEntity<? extends BaseResponseBody> updateUserTeamInfo(@ApiIgnore Authentication authentication, @RequestBody UserTeamMappingPutReq userTeamMappingPutReq) {
		if (userService.updateUserTeamInfo(userTeamMappingPutReq)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		}
		return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Fail"));
	}

	// 유저 출근
	@PostMapping(value = "{userId}/check-in")
	@ApiOperation(value = "유저 출근", notes = "<strong>아이디</strong>를 가진 회원의 출근 처리.")
	@ApiResponses({
			@ApiResponse(code = 204, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<LocalTime> checkInUser(@PathVariable String userId) {
		User user = userService.getUserByUserId(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		LocalTime checkInTime = userService.checkInUser(user);
		if (checkInTime == null) {
			return ResponseEntity.status(409).body(null);
		}
		return ResponseEntity.ok().body(checkInTime);
	}

	// 유저 퇴근
	@PutMapping(value = "{userId}/check-out")
	@ApiOperation(value = "유저 퇴근", notes = "<strong>아이디</strong>를 가진 회원의 퇴근 처리.")
	@ApiResponses({
			@ApiResponse(code = 204, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<LocalTime> checkOutUser(@PathVariable String userId) {
		User user = userService.getUserByUserId(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		LocalTime checkOutTime = userService.checkOutUser(user);
		if (checkOutTime == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(checkOutTime);
	}

	// 유저 1개월 단위 근태 조회
	@GetMapping(value = "{userId}/attendance/{year}/{month}")
	@ApiOperation(value = "유저 근태 조회(1개월 단위)", notes = "<strong>아이디</strong>를 가진 회원의 근태현황.")
	@ApiResponses({
			@ApiResponse(code = 204, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<AttendanceRes>> getAttendanceByMonth(@PathVariable String userId, @PathVariable Integer year, @PathVariable Integer month) {
		User user = userService.getUserByUserId(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		Map<String, Object> dateMap = new HashMap<>();
		dateMap.put("user", user);
		dateMap.put("year", year);
		dateMap.put("month", month);
		return ResponseEntity.ok().body(AttendanceListRes.of(userService.findAllByDateBetween(dateMap)));
	}

	// 유저 1주일 근로 시간 조회
	@GetMapping(value = "{userId}/attendance/week")
	@ApiOperation(value = "유저 근태 시간 조회(이번주)", notes = "<strong>아이디</strong>를 가진 회원의 1주일 근로 시간")
	@ApiResponses({
			@ApiResponse(code = 200, message = "이번 주 근로 시간을 받음"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<TotalHourRes> getAttendanceByWeek(@PathVariable String userId, @RequestParam String startDate, @RequestParam String endDate) {
		User user = userService.getUserByUserId(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(TotalHourRes.of(userService.findAllByDateBetween(user, startDate, endDate)));
	}
	// 유저 당일 근태 조회
	@GetMapping(value = "{userId}/attendance")
	@ApiOperation(value = "유저 근태 조회(당일)", notes = "<strong>아이디</strong>를 가진 회원의 근태현황.")
	@ApiResponses({
			@ApiResponse(code = 204, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<AttendanceRes> getAttendanceToday(@PathVariable String userId) {
		User user = userService.getUserByUserId(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(AttendanceRes.of(userService.getAttendanceToday(user)));
	}

	// 프로필 업로드 테스트
	@PostMapping(value = "{userId}/profile")
	@ApiOperation(value = "프로필 업로드 테스트", notes = "프로필 업로드를 테스트한다.")
	@ApiResponses({
			@ApiResponse(code = 204, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<String> registerProfile(@PathVariable String userId, @RequestParam MultipartFile profile) throws IOException {
		String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
		System.out.println("rootPath: " + rootPath);

		String basePath = rootPath + "/" + "profile";
		System.out.println("basePath: " + basePath);

		File file = new File(basePath);
		if (!file.exists()) {
			file.mkdir();
		}

		String filePath = basePath + "/" + profile.getOriginalFilename();
		System.out.println("filePath: " + filePath);

		File dest = new File(filePath);
		profile.transferTo(dest);
		return ResponseEntity.ok().body("success");
	}
	// 유저 권한 수정
	@PutMapping("/{userId}/auth")
	@ApiOperation(value = "유저 권한 수정", notes = "유저이 권한을 수정한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공")
	})

	public ResponseEntity<? extends BaseResponseBody> updateUserAuth(@PathVariable String userId) {
		userService.updateUserAuth(userId);
		return ResponseEntity.ok().build();
	}
}
