package com.ssafy.api.service;

import com.ssafy.api.request.UserTeamMappingPutReq;
import com.ssafy.api.response.UserRes;
import com.ssafy.common.util.FileUtil;
import com.ssafy.db.entity.Attendance;
import com.ssafy.db.entity.Department;
import com.ssafy.db.entity.Grp;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRepositorySupport userRepositorySupport;

	@Autowired
	AttendanceRepository attendanceRepository;

	@Autowired
	AttendanceRepositorySupport attendanceRepositorySupport;

	@Autowired
	DepartmentRepositorySupport departmentRepositorySupport;

	@Autowired
	GrpRepositorySupport grpRepositorySupport;

	@Autowired
	PasswordEncoder passwordEncoder;

	FileUtil fileUtil = FileUtil.getInstance();
	
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) throws Exception{
		User user = new User();
		user.setUserId(userRegisterInfo.getUserId());
		user.setName(userRegisterInfo.getName());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		user.setDepartment(getDepartmentById(userRegisterInfo.getDepartment()));
		user.setEmail(userRegisterInfo.getEmail());

//		String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
//		System.out.println("rootPath: " + rootPath);
//
//		String basePath = rootPath + "/" + "profile";
//		System.out.println("basePath: " + basePath);
//
//		File file = new File(basePath);
//		if (!file.exists()) {
//			file.mkdir();
//		}
//		String filePath = basePath + "/" + userRegisterInfo.getProfile().getOriginalFilename();
//		System.out.println("filePath: " + filePath);
//		user.setProfileLocation(filePath);
//
//		File dest = new File(filePath);
//		userRegisterInfo.getProfile().transferTo(dest);


		String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
		System.out.println("rootPath: " + rootPath);

		//String path = rootPath + "/media";
		File t = new File("..");
		String path = t.getCanonicalPath();
		fileUtil.createFilePath(path += "/media");
		fileUtil.createFilePath(path += "/profile");
		File file = fileUtil.createFilePath(path + "/" + userRegisterInfo.getUserId());


		String filePath = file.getAbsoluteFile() + "/" + userRegisterInfo.getProfile().getOriginalFilename();

		System.out.println("filePath: " + filePath);
		System.out.println("AbbbbfilePath: " +file.getAbsoluteFile());

		File ts = new File("..");
		System.out.println("test용 path : " + ts.getAbsoluteFile());
		System.out.println("test용 path : " + ts.getCanonicalPath());

		user.setProfileLocation("/media/profile/" + userRegisterInfo.getUserId() + "/" + userRegisterInfo.getProfile().getOriginalFilename());
		File dest = new File(filePath);
		userRegisterInfo.getProfile().transferTo(dest);

		return userRepository.save(user);
	}

	@Override
	public User getUserByUserId(String userId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = null;
		if(userRepositorySupport.findUserByUserId(userId).isPresent())
			user = userRepositorySupport.findUserByUserId(userId).get();
		return user;
	}

	@Override
	public List<UserRes> findAll() {
		List<User> users = userRepository.findAll();
		List<UserRes> userResList = new ArrayList<>();
		for (User user : users) {
			userResList.add(UserRes.of(user));
		}
		return userResList;
	}

	@Override
	public boolean getTeamByUserId(String userId) {
		User user = null;
		if(userRepositorySupport.findUserByUserId(userId).isPresent())
			user = userRepositorySupport.findUserByUserId(userId).get();

//		if(user == null || user.getGrp().getId() != 1) return false;
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepositorySupport.updateUser(user);
	}

	@Override
	public boolean updateUserTeamInfo(UserTeamMappingPutReq userTeamMappingPutReq){
		User user = getUserByUserId(userTeamMappingPutReq.getUserId());
		if(user == null) return false;

		Grp grp = grpRepositorySupport.findTeamById(userTeamMappingPutReq.getTeamId()).get();
		if(grp == null) return false;

//		user.setGrp(grp);
		return userRepositorySupport.updateUser(user);
	}

	@Override
	public boolean checkInUser(User user) {
		if (attendanceRepositorySupport.getAttendanceToday(user).isPresent()) {
			return false;
		}
		Attendance attendance = new Attendance();
		attendance.setUser(user);
		attendance.setCheckInTime(LocalTime.now());
		attendanceRepository.save(attendance);
		return true;
	}

	@Override
	public boolean checkOutUser(User user) {
		// 출석 정보 가져오기
		Attendance attendance = attendanceRepositorySupport.findAttendanceByUserId(user.getUserId());
		if (attendance == null) {
			return  false;
		}
		attendance.setCheckOutTime(LocalTime.now());
		return attendanceRepositorySupport.checkOutUser(attendance);
	}

	@Override
	public List<Attendance> findAllByDateBetween(Map<String, Object> dateMap) {
		return attendanceRepositorySupport.findAllByDateBetween(dateMap);
	}

	@Override
	public Integer findAllByDateBetween(User user, String startDate, String endDate) {
		return attendanceRepositorySupport.findAllByDateBetween(user, startDate, endDate);
	}

	public Attendance getAttendanceToday(User user) {
		Attendance attendance = null;
		if (attendanceRepositorySupport.getAttendanceToday(user).isPresent()) {
			attendance = attendanceRepositorySupport.getAttendanceToday(user).get();
		}
		return attendance;
	}

	@Override
	public Department getDepartmentById(long departmentId){
		Department department = null;
		if(departmentRepositorySupport.findDepartmentById(departmentId).isPresent()){
			department = departmentRepositorySupport.findDepartmentById(departmentId).get();
		} else department = new Department(1, "empty");
		return department;
	}

	@Override
	public boolean deleteUser(String userId) {
		return userRepositorySupport.deleteUserByUserId(userId);
	}
}
