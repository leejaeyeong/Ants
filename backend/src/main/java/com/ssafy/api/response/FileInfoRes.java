package com.ssafy.api.response;

import com.ssafy.db.entity.Attendance;
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
@ApiModel("FileInfoResponse")
public class FileInfoRes {
	Long id;
	LocalDate date;
	String fileExtension;
	String fileLocation;
	String fileName;
	String size;

}
