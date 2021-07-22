package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Attendance;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("AttendanceResponse")
public class AttendanceRes {
	@ApiModelProperty(name="출근 날짜", example="2021-07-22")
	LocalDate date;
	@ApiModelProperty(name="출근 시간", example="08:30:11")
	LocalTime checkInTime;
	@ApiModelProperty(name="퇴근 날짜", example="18:00:11")
	LocalTime checkOutTime;

	AttendanceRes(LocalDate date, LocalTime checkInTime, LocalTime checkOutTime) {
		this.date = date;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
	}
	public static AttendanceRes of(Attendance attendance) {
		if (attendance == null) {
			return new AttendanceRes(null, null, null);
		}
		return new AttendanceRes(attendance.getDate(), attendance.getCheckInTime(), attendance.getCheckOutTime());
	}
}
