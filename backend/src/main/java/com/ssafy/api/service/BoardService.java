package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.request.UserTeamMappingPutReq;
import com.ssafy.api.response.BoardCommentRes;
import com.ssafy.api.response.BoardRes;
import com.ssafy.db.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */

public interface BoardService {
	List<BoardType> getBoardTypeList();
	List<BoardRes> getBoardList();
	List<BoardRes> getBoardListByTypeId(Long id);
	List<BoardRes> getBoardListByMarker(String userId);
	List<BoardRes> getBoardByWriterId(String userId);
	List<BoardRes> getBoardByTitle(String title);
	BoardRes getBoardDetail(Long id, String userId);
	boolean isMarker(Long boardId, String userId);
}
