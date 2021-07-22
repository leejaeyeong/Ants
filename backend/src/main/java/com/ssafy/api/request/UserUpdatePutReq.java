package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserUpdatePutRequest")
public class UserUpdatePutReq {
	@ApiModelProperty(name="유저 Password", example="your_password")
	String password;
	@ApiModelProperty(name="유저 소속", example = "1")
	Long departmentId;
	@ApiModelProperty(name="유저 구분", example = "교육생")
	String position;
	@ApiModelProperty(name="유저 이름", example = "홍길동")
	String name;
}
