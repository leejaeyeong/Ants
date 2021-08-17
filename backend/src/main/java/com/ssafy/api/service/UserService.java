package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.request.UserTeamMappingPutReq;
import com.ssafy.api.request.UserUpdatePutReq;
import com.ssafy.api.response.UserRes;
import com.ssafy.db.entity.Attendance;
import com.ssafy.db.entity.Department;
import com.ssafy.db.entity.User;
import com.ssafy.db.entity.outLink;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	User createUser(UserRegisterPostReq userRegisterInfo) throws Exception;
	User getUserByUserId(String userId);
	List<UserRes> findAll();
	boolean getTeamByUserId(String userId);
	boolean updateUserTeamInfo(UserTeamMappingPutReq userTeamMappingPutReq);
	boolean updateUser(User user, String email, MultipartFile profile) throws IOException;
	boolean deleteUser(String userId);
	LocalTime checkInUser(User user);
	LocalTime checkOutUser(User user);
	List<Attendance> findAllByDateBetween(Map<String, Object> dateMap);
	Integer findAllByDateBetween(User user, String startDate, String endDate);
	Department getDepartmentById(long departmentId);
	Attendance getAttendanceToday(User user);
	boolean updateUserAuth(String userId);
	void delete(Long id);
}
