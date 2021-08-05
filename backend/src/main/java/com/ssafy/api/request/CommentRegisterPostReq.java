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
@ApiModel("CommentRegisterPostRequest")
public class CommentRegisterPostReq {
	@ApiModelProperty(name="댓글 내용", example="댓글입니다.")
	String comment;
	@ApiModelProperty(name="댓글 작성자 아이디", example="test-1")
	String writer;
}
