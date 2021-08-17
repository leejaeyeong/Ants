package com.ssafy.api.response;

import com.ssafy.db.entity.Attendance;
import com.ssafy.db.entity.FileInfo;
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
public class FileInfoRes extends CalendarDetail {
	Long id;
	LocalDate date;
	String fileExtension;
	String fileLocation;
	String fileName;
	String size;
	String uploader;
	LocalTime time;
	String type = "file-upload";
	String userName;
	String profileLocation;

	public static FileInfoRes of (FileInfo fileInfo) {
		FileInfoRes fileInfoRes = new FileInfoRes();
		fileInfoRes.setId(fileInfo.getId());
		fileInfoRes.setDate(fileInfo.getDate());
		fileInfoRes.setFileExtension(fileInfo.getFileExtension());
		fileInfoRes.setFileLocation(fileInfo.getFileLocation());
		fileInfoRes.setFileName(fileInfo.getFileName());
		fileInfoRes.setSize(fileInfo.getSize());
		fileInfoRes.setUploader(fileInfo.getUser().getUserId());
		fileInfoRes.setTime(fileInfo.getTime());
		fileInfoRes.setUserName(fileInfo.getUser().getName());
		fileInfoRes.setProfileLocation(fileInfo.getUser().getProfileLocation());
		return fileInfoRes;
	}
}
