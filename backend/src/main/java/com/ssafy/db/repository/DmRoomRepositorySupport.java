package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.UserService;
import com.ssafy.db.entity.Dm;
import com.ssafy.db.entity.DmRoom;
import com.ssafy.db.entity.QDm;
import com.ssafy.db.entity.QDmRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DmRoomRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private DmRoomRepository dmRoomRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DmRepository dmRepository;

    QDmRoom qDmRoom = QDmRoom.dmRoom;
    QDm qDm = QDm.dm;

    public Optional<List<DmRoom>> getDmRoomList(String user1){
        List<UserRes> list1 = userService.findAll();
        List<UserRes> list2 = userService.findAll();

        List<DmRoom> list = new ArrayList<>();
        for(int i = 0; i < list1.size(); i++){
            for(int j = i; j < list2.size(); j++){
                if(i == j) continue;

                DmRoom sample = jpaQueryFactory.select(qDmRoom).from(qDmRoom)
                        .where(qDmRoom.user1.eq(list1.get(i).getUserId()).and(qDmRoom.user2.eq(list2.get(j).getUserId()))
                                .or(qDmRoom.user1.eq((list2.get(j).getUserId())).and((qDmRoom.user2.eq(list1.get(i).getUserId())))))
                        .fetchOne();

                if(sample == null){
                    DmRoom dmRoom = new DmRoom();
                    dmRoom.setUser1(list1.get(i).getUserId());
                    dmRoom.setUser2(list2.get(j).getUserId());
                    dmRoom.setUsername1(userService.getUserByUserId(list1.get(i).getUserId()).getName());
                    dmRoom.setUsername2(userService.getUserByUserId(list2.get(j).getUserId()).getName());
                    dmRoom.setDepartmentName1(departmentRepository.findDepartmentById(list1.get(i).getDepartment()).get().getDepartmentName());
                    dmRoom.setDepartmentName2(departmentRepository.findDepartmentById(list2.get(j).getDepartment()).get().getDepartmentName());
                    dmRoom.setProfileLocation1(userService.getUserByUserId(list1.get(i).getUserId()).getProfileLocation());
                    dmRoom.setProfileLocation2(userService.getUserByUserId(list2.get(j).getUserId()).getProfileLocation());
                    dmRoom.setRoomId(UUID.randomUUID().toString());

                    if(dmRoom.getUser1().equals(user1) || dmRoom.getUser2().equals(user1)){
                        list.add(dmRoom);
                    }

                    dmRoomRepository.save(dmRoom);
                } else {
                    if(sample.getUser1().equals(user1) || sample.getUser2().equals(user1)){
                        list.add(sample);
                    }
                }
            }
        }
        return Optional.of(list);
    }

    public Optional<List<Dm>> getDmHistory(String roomId){
        List<Dm> dmHistoryList = jpaQueryFactory.select(qDm).from(qDm)
                .where(qDm.roomId.eq(roomId)).fetch();

        if(dmHistoryList == null) return Optional.empty();
        return Optional.of(dmHistoryList);
    }

    public Dm putDm(String roomId, String name, String message){
        DmRoom dmRoom = jpaQueryFactory.select(qDmRoom).from(qDmRoom)
                .where(qDmRoom.roomId.eq(roomId)).fetchOne();

        Dm dm = new Dm();
        dm.setMessage(message);
        dm.setRoomId(roomId);
        dm.setUser(name);


        return dmRepository.save(dm);
    }
}
