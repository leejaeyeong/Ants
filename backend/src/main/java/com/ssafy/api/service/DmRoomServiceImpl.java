package com.ssafy.api.service;

import com.ssafy.api.response.DmRoomRes;
import com.ssafy.db.entity.Dm;
import com.ssafy.db.entity.DmRoom;
import com.ssafy.db.repository.DmRepository;
import com.ssafy.db.repository.DmRoomRepository;
import com.ssafy.db.repository.DmRoomRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("DmRoomService")
public class DmRoomServiceImpl implements DmRoomService{

    @Autowired
    private DmRoomRepositorySupport dmRoomRepositorySupport;

    @Override
    public List<DmRoomRes> getDmRoomList(String userId){
        List<DmRoom> list = dmRoomRepositorySupport.getDmRoomList(userId).get();
        List<DmRoomRes> dmList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            DmRoomRes dmRoomRes = new DmRoomRes();
            System.out.println("유저아이디: " + userId);
            if(list.get(i).getUser1().equals(userId)) {
                dmRoomRes.setUserId(list.get(i).getUser2());
                dmRoomRes.setUsername(list.get(i).getUsername2());
                dmRoomRes.setDepartmentName(list.get(i).getDepartmentName2());
                dmRoomRes.setProfileLocation(list.get(i).getProfileLocation2());
            } else {
                dmRoomRes.setUserId(list.get(i).getUser1());
                dmRoomRes.setUsername(list.get(i).getUsername1());
                dmRoomRes.setDepartmentName(list.get(i).getDepartmentName1());
                dmRoomRes.setProfileLocation(list.get(i).getProfileLocation1());
            }
            dmRoomRes.setRoomId(list.get(i).getRoomId());
            dmList.add(dmRoomRes);
        }
        return dmList;
    }

    @Override
    public List<Dm> getDmHistory(String roomId){
        List<Dm> dmHistory = dmRoomRepositorySupport.getDmHistory(roomId).get();
        return dmHistory;
    }

    public Dm putDm(String roomId, String name, String message){
        return dmRoomRepositorySupport.putDm(roomId, name, message);
    }
}
