<template>
  <div id="container">
      <div id="wrapper">
          <div id="join" style="position:absolute; left:60px; top:-30px;" class="animate join">
              <!-- <h1>Join a Room</h1> -->
              <form onsubmit="register(); return false;" accept-charset="UTF-8">
                  <p>
                      <input v-show="false" v-model="state.roomName" style="margin-top:10px;" type="text" name="room" id="roomName" placeholder="Room" required>
                  </p>
                  <p>
                      <input v-show="false" v-model="state.id" type="text" name="name" id="rtcName" placeholder="Username" required>
                  </p>
                  <p class="submit"><input id="submit" type="submit" name="commit" value="회의생성" @click="getRoomName(state.roomName)">
                  </p>
              </form>
              <div class="q-pa-md row items-start q-gutter-md">
                <q-card v-for="room in rooms" :key="room.id" class="my-card" style="margin-top:100px;">
                    <!-- <q-img src="https://cdn.quasar.dev/img/parallax2.jpg"> -->
                    <q-img src="~assets/images/office.jpg" style="">
                      <div class="absolute-bottom">
                        <div class="text-h6">{{ room.roomName }}</div>
                        <div class="text-subtitle2">{{ room.id }}</div>
                      </div>
                    </q-img>
                    <q-card-actions>
                      <!-- <q-btn class="enter">회의입장</q-btn> -->
                      <form class="enter" onsubmit="joinConference($(this).find('input').eq(0).val(), $(this).find('input').eq(1).val()); return false;" accept-charset="UTF-8">
                        <input id="joinUser" :value="id" style="display : none">
                        <input id="joinConferenceRoom" style="display : none" :value="room.roomName">
                        <input class="enter" type="submit" name="commit" value="회의입장" @click="getRoomName(room.roomName)">
                      </form>
                    </q-card-actions>
                </q-card>
              </div>
          </div>
          <div id="room" style="display: none;">
              <div id="participants"></div>
              <!-- 채팅 섹션 -->
              <div id="chat">
                <div style="font-size:30px; font-weight:bold; margin-top:5px; margin-left:5px;">대화내용</div>
                <div id="chatDiv">
                <table id="conversation" class="table table-striped">
                    <thead>
                    <tr>
                    </tr>
                    </thead>
                    <tbody id="greetings">
                    </tbody>
                  </table>
                </div>
                  <!-- 전송 버튼 -->
                  <div id="send">
                    <form onsubmit="sendChat($(this).find('input').eq(0).val(), $(this).find('input').eq(1).val(), $(this).find('input').eq(2).val()); return false;" accept-charset="UTF-8">
                      <input type="text" id="guest" class="form-control" :value="name" style="display:none"/>
                      <input id="chatMessage" style="display:inline-block; margin-top:-5px; height:52px; width:390px;" class="form-control" placeholder="메세지 입력"/>
                      <input type="text" id="roomName" class="form-control" style="display : none" v-model="rName"/>
                      <button id="chatSend" class="btn btn-default" type="submit">보내기</button>
                    </form>
                  </div>
              </div>
              <!-- 나가기 버튼 -->
              <form class="form-inline" onsubmit="disconnect(); return false;" accept-charset="UTF-8">
                <div class='btDiv1'>
                  <q-icon style="font-size: 2.8em; color: white;" name="mic"/>
                </div>
                <div class=btDiv2>
                  <q-icon style="font-size: 2.8em; color: white;" name="photo_camera"/>
                </div>
                <input type="button" id="button-leave" onmouseup="leaveRoom();">
              </form>
          </div>
      </div>
  </div>
  <div id="room" style="display: none">
    <h2 id="room-header"></h2>
    <div id="participants"></div>
    <input
      type="button"
      id="button-leave"
      onmouseup="leaveRoom();"
      value="Leave room"
    />
  </div>
  <div id="room" style="display: none">
    <h2 id="room-header"></h2>
    <div id="participants"></div>
    <input
      type="button"
      id="button-leave"
      onmouseup="leaveRoom();"
      value="Leave room"
    />
  </div>

</template>
<script>
import { reactive, computed, defineComponent, onMounted } from 'vue'
import { useStore } from 'vuex'

export default defineComponent({
  setup () {
    const store = useStore()
    const state = reactive({
      id: localStorage.getItem('id'),
      roomName: localStorage.getItem('id') + '님의 회의방'
    })
    const user = store.getters['module/getLoginUser']
    const id = user.id
    const name = user.name
    const rooms = computed(() => store.getters['module/getRooms'])
    const rName = computed(() => store.getters['module/getRoomName'])
    const getRoomName = function (rName) {
      store.commit('module/setRoomName', rName)
    }
    var temp = []
    onMounted(() => {
      store.dispatch('module/getRooms', {})
        .then(function (result) {
          for (let i = 0; i < result.data.length; i++) {
            var object = {
              roomName: result.data[i][0],
              id: result.data[i][1]
              // department: result.data[i][2]
            }
            temp.push(object)
          }
          store.commit('module/setRooms', temp)
          temp = []
          console.log(rooms)
        })
    })

    return {
      rooms,
      state,
      id,
      name,
      rName,
      getRoomName
    }
  }
})
</script>
<style>
#wrapper{
  width:1858px;
  height:908px;
  float:right;
  position: absolute;
  top: 60px;
  left: 60px;
  border-bottom:0.5px solid rgb(212, 212, 212);
  background-color: rgb(242, 247, 244);
}
#submit{
    background-color:#18C75E;
    font-weight:bold;
    color:white;
    padding:10px;
    font-size:30px;
    cursor: pointer;
    border:0.5px solid #18C75E;
    border-radius:5px;
    position:absolute;
    top:850px;
    left:1590px;
}
#submit:hover{
    opacity: 0.7;
}
#participants{
    width:1340px;
    height:892px;
    position:absolute;
    top:15px;
    left:30px;
}
#chat{
    width:490px;
    height:892px;
    border-left:1px solid rgb(212, 212, 212);
    border-top: 1px solid rgb(212, 212, 212);
    position:absolute;
    left:1350px;
    top:15px;
}
#button-leave{
    position:absolute;
    width:50px;
    height:50px;
    top:850px;
    left:705px;
    border:0.5px solid #18C75E;
    font-weight:bold;
    color:white;
    padding:10px;
    font-size:30px;
    cursor: pointer;
    border-radius:50px;
    background: url('~assets/images/exit.png') no-repeat right center #18C75E;
}
.my-card{
  width:380px;
  margin:30px 0 0 30px;
  box-shadow: 5px 5px 5px;
}
.enter{
  background-color:#18C75E;
  border:0.5px solid #18C75E;
  width:100%;
  margin:0 auto;
  font-weight:bold;
  color:white;
  font-size:18px;
  cursor: pointer;
  border-radius:5px;
}
.enter:hover{
  opacity: 0.7;
}
#chatSend{
  background-color:#18C75E;
  border:0.5px solid #18C75E;
  font-weight:bold;
  color:white;
  padding:10px;
  font-size:20px;
  cursor:pointer;
  margin-top:5px;
}
#send{
  position: absolute;
  top:93%;
}
#chatDiv{
  width:488px;
  height:840px;
  overflow-y:scroll;
}
.msg{
  font-size:20px;
}
.btDiv1{
  width:50px;
  height:50px;
  border-radius: 50px;
  position:absolute;
  top:850px;
  left:585px;
  background-color:#18C75E;
  cursor: pointer;
  padding-left:5px;
  padding-top:5px;
}
.btDiv2{
  width:50px;
  height:50px;
  border-radius: 50px;
  position:absolute;
  top:850px;
  left:645px;
  background-color:#18C75E;
  cursor: pointer;
  padding-left:5px;
  padding-top:5px;
}
</style>
