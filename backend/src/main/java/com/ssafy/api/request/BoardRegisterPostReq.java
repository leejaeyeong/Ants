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
@ApiModel("BoardRegisterPostRequest")
public class BoardRegisterPostReq {
	@ApiModelProperty(name="게시판 타입", example="1")
	Long type;
	@ApiModelProperty(name="작성자 ID", example="test-1")
	String writer;
	@ApiModelProperty(name="게시글 제목", example="공지사항 제목")
	String title;
	@ApiModelProperty(name="게시글 내용", example = "공지내용")
	String content;
	@ApiModelProperty(name="이미지 파일", example = "이미지 파일")
	MultipartFile image;

	public static BoardRegisterPostReq of(Long type, String writer, String title, String content, MultipartFile image) {
		BoardRegisterPostReq boardRegisterPostReq = new BoardRegisterPostReq();
		boardRegisterPostReq.setType(type);
		boardRegisterPostReq.setWriter(writer);
		boardRegisterPostReq.setTitle(title);
		boardRegisterPostReq.setContent(content);
		boardRegisterPostReq.setImage(image);
		return boardRegisterPostReq;
	}
}
