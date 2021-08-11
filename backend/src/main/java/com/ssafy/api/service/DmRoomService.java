package com.ssafy.api.service;

import com.ssafy.api.response.DmRoomRes;
import com.ssafy.db.entity.Dm;
import com.ssafy.db.entity.DmRoom;

import java.util.List;

public interface DmRoomService {

    public List<DmRoomRes> getDmRoomList(String userId);
    public List<Dm> getDmHistory(String roomId);
    public Dm putDm(String roomId, String name, String message);
}
