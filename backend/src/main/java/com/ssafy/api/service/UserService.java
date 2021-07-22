package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.request.UserTeamMappingPutReq;
import com.ssafy.api.request.UserUpdatePutReq;
import com.ssafy.db.entity.Attendance;
import com.ssafy.db.entity.User;

import java.util.List;
import java.util.Map;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	User createUser(UserRegisterPostReq userRegisterInfo);
	User getUserByUserId(String userId);
	boolean getTeamByUserId(String userId);
	boolean updateUser(String userId, UserUpdatePutReq userUpdateInfo);
	boolean updateUserTeamInfo(UserTeamMappingPutReq userTeamMappingPutReq);
	boolean deleteUser(String userId);
	boolean checkInUser(String userId);
	boolean checkOutUser(String userId);
	List<Attendance> findAllByDateBetween(Map<String, Object> dateMap);
}
