package com.ssafy.api.response;

import com.ssafy.db.entity.Attendance;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("AttendanceResponse")
public class AttendanceListRes {

	public static List<AttendanceRes> of(List<Attendance> attendances) {
		List<AttendanceRes> attendanceResList = new ArrayList<>();
		for (Attendance attendance : attendances) {
			attendanceResList.add(new AttendanceRes(attendance.getDate(), attendance.getCheckInTime(), attendance.getCheckOutTime()));
		}
		return attendanceResList;
	}
}
