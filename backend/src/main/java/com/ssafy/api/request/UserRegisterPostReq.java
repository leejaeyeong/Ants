package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {
	@ApiModelProperty(name="유저 ID", example="ssafy_web")
	String userId;
	@ApiModelProperty(name="유저 Password", example="your_password")
	String password;
	@ApiModelProperty(name="유저 이름", example = "홍길동")
	String name;
	@ApiModelProperty(name="이메일", example = "test@naver.com")
	String email;
	@ApiModelProperty(name="유저 부서", example = "1")
	Long department;
	@ApiModelProperty(name="프로필 사진 경로", example = "파일객체")
	MultipartFile profile;

	public static UserRegisterPostReq of(String userId, String password, String name, String email, Long department, MultipartFile profile) {
		UserRegisterPostReq userReq = new UserRegisterPostReq();
		userReq.userId = userId;
		userReq.password = password;
		userReq.name = name;
		userReq.email = email;
		userReq.department = department;
		userReq.profile = profile;
		return userReq;
	}

}
