<template>
    <div class="q-mt-md">
        <q-fab
            id="dmBtn"
            v-model="dm"
            label="메신저"
            vertical-actions-align="left"
            color="yellow-10"
            icon="keyboard_arrow_down"
            direction="down"
        >
            <div id="dm">
              <div v-show="!chatMode">
                <h4 style="margin-top:20px; margin-left: 25px; margin-bottom:8px; font-weight:bold;">DM List</h4>
                <q-scroll-area style="height: 400px; max-width: 600px;">
                  <div id="list">
                      <!-- <div @click="enter" class="det" v-for="member in memberList" :key="member.userId">
                        <div class="row">
                          <div class="col-3">
                            <img :src="member.profileLocation" style="margin-top:8px; width:70px; height:70px; border-radius:50px; margin-left:20px;">
                          </div>
                          <div class="col-9" style="line-height:85px;">
                            {{ member.name }} {{ member.department }}
                          </div>
                        </div>
                      </div> -->
                      <div v-for="dmRoom in dmRoomList" :key="dmRoom.roomId">
                        <form class="detail" onsubmit="dmConnect($(this).find('input').eq(0).val(), $(this).find('input').eq(1).val()); return false;">
                          <input :value="dmRoom.roomId" style="display:none">
                          <input :value="loginUser.name" style="display:none">
                          <input v-bind:style="{ backgroundImage: 'url(' + dmRoom.profileLocation + ')' }" class='enter' type="submit" @click="enter(dmRoom.roomId, dmRoom.username)" value=" "/>
                          <span class="txtName">{{ dmRoom.username }}</span> <span class="txt">{{ dmRoom.departmentName }}</span>
                        </form>
                      </div>
                  </div>
                </q-scroll-area>
              </div>
              <div id="chat" v-show="chatMode">
                <div id="conversation">
                  <form onsubmit="dmDisconnect(); return false;">
                    <q-btn @click="back" round color="yellow-10" icon="clear" size="11px" style="position:relative; left:450px; margin-top:10px;" type="submit"/>
                  </form>
                  <div id="dmChatWindow" class="q-pa-md row justify-center">
                    <div v-for="history in dmHistory" :key="history.id">
                      <div class='box1' v-if="history.user === loginUser.name">
                        <div class='name1'>
                          {{ history.user }}
                        </div>
                        <div>
                          <div class='left1'></div>
                          <div class='right1'>
                            {{ history.message }}
                          </div>
                        </div>
                      </div>
                      <div class='box' v-if="history.user !== loginUser.name">
                        <div style='font-weight:bold; font-size: 14px;'>
                          {{ history.user }}
                        </div>
                        <div>
                          <div class='left'></div>
                          <div class='right'>
                            {{ history.message }}
                          </div>
                        </div>
                      </div>
                    </div>
                    <div id="nowChat"></div>
                  </div>
                </div>
                <div id="form">
                  <form onsubmit="dmSendChat($(this).find('input').eq(0).val(), $(this).find('input').eq(1).val(), $(this).find('input').eq(2).val()); return false;">
                    <input v-model="sendTo.roomId" style="display:none">
                    <input v-model="loginUser.name" style="display:none">
                    <input id="chatMessage" style="display:inline-block; width:400px; height:47px; border-radius: 8px; border: 1px solid green;" class="form-control" placeholder="메세지를 입력하세요" v-model="message"/>
                    <button id="chatSendSend" class="btn btn-default" type="submit" @click="sendDm(sendTo.roomId, loginUser.name, message)">Send</button>
                  </form>
                </div>
              </div>
            </div>
        </q-fab>
    </div>
</template>

<script>
import { defineComponent, onMounted, ref, computed } from 'vue'
import { useStore } from 'vuex'

export default defineComponent({
  name: 'mainpage',
  components: {
  },
  methods: {
    mvAttendance () {
      this.$router.push('/management')
    }
  },
  setup () {
    const store = useStore()
    const memberList = computed(() => store.getters['module/getMemberList'])
    const chatMode = computed(() => store.getters['module/getChatMode'])
    const dmRoomList = computed(() => store.getters['module/getDmRoomList'])
    const loginUser = computed(() => store.getters['module/getLoginUser'])
    const sendTo = {
      name: '',
      roomId: ''
    }
    const dmHistory = computed(() => store.getters['module/getDmHistory'])
    const message = ''
    onMounted(() => {
      store.dispatch('module/dmRoomList', store.getters['module/getLoginUser'].id)
        .then(function (result) {
          console.log(result.data, '받은 데이터')
          store.commit('module/setDmRoomList', result.data)
          console.log(store.getters['module/getDmRoomList'], '테스트 확인')
        })
      store.dispatch('module/memberList')
        .then(function (result) {
          store.dispatch('module/departmentInfo')
            .then(function (res) {
              for (let i = 0; i < result.data.length; i++) {
                for (let j = 0; j < res.data.length; j++) {
                  if (result.data[i].department === res.data[j].id) {
                    result.data[i].department = res.data[j].departmentName
                  }
                }
              }
              store.commit('module/setMemberList', result.data)
              console.log('포문 다돔')
              console.log(result.data)
              console.log(memberList)
            })
        })
      const dm = document.getElementById('dmBtn')
      var x = 0
      var y = 0
      var mousedown = false

      dm.addEventListener('mousedown', function (e) {
        mousedown = true
        x = dm.offsetLeft - e.clientX
        y = dm.offsetTop - e.clientY
      }, true)

      dm.addEventListener('mouseup', function (e) {
        mousedown = false
      }, true)

      dm.addEventListener('mousemove', function (e) {
        if (mousedown) {
          dm.style.left = e.clientX + x + 'px'
          dm.style.top = e.clientY + y + 'px'
        }
      })
    })
    const enter = function (roomId, username) {
      store.dispatch('module/getDmHistory', roomId)
        .then(function (result) {
          document.getElementById('nowChat').innerHTML = ''
          console.log(result.data)
          store.commit('module/setDmHistory', result.data)
        })
      sendTo.roomId = roomId
      sendTo.name = username
      store.commit('module/setChatMode', true)
    }
    const back = function () {
      store.commit('module/setChatMode', false)
    }
    const sendDm = function (room, name, message) {
      const body = {
        roomId: room,
        user: name,
        message: message
      }
      store.dispatch('module/sendDm', body)
        .then(function (result) {
          console.log(result.data)
        })
    }
    return {
      dm: ref(false),
      memberList,
      chatMode,
      dmRoomList,
      loginUser,
      sendTo,
      message,
      dmHistory,
      enter,
      back,
      sendDm
    }
  }
})
</script>

<style scoped>
#dmBtn{
  position: absolute;
  top:8px;
  left:1357px;
}
#dm{
  width: 500px;
  height:500px;
  border:1px solid #b9b9b9;
  background-color: rgb(252, 253, 252);
}
#list{
  width:100%;
  height:500px;
  padding-left:20px;
  /* border-bottom:1px solid rgb(212, 212, 212); */
  /* border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px; */
  /* overflow-y: scroll; */
}
.detail{
  width:95%;
  font-size:16px;
  padding-top:5px;
  padding-bottom:5px;
  padding-left:15px;
  border-bottom:1px solid rgb(212, 212, 212)
}
.detail:hover{
  background-color:rgba(236, 236, 236, 0.979);
}
.det{
  width:95%;
  height:85px;
  margin: 0 auto;
  border-bottom:1px solid black;
  cursor: pointer;
  font-size:22px;
}
.det:hover{
  background-color:rgb(168, 163, 163);
}
#dmChatWindow{
  height:420px;
  overflow-y:scroll;
  overflow-x:hidden;
}
#form{
  height:50px;
  margin-top: 11px;
}
#chat{
  width:100%;
  height:448px;
  background-color: rgb(252, 253, 252);
}
#chatSendSend{
  background-color:#249752;
  border:0.5px solid #249752;
  font-weight:bold;
  color:white;
  padding:10px;
  font-size:15px;
  cursor:pointer;
  width:98px;
  margin-top:3px;
  border-radius: 8px;
}
.enter{
  /* border:0.5px solid #18C75E; */
  /* background:url('~assets/images/send2.png') no-repeat #18C75E; */
  border:1px solid rgb(212, 212, 212);
  background:no-repeat;
  background-size:60px 60px;
  background-position: center;
  width:60px;
  height:60px;
  border-radius:30px;
  cursor: pointer;
}
.txt{
  margin-left:20px;
}
.txtName{
  margin-left:20px;
  font-weight: bold;
}
.box{
  width:460px;
  height:80px;
}
.left{
  height:10px;
  width:6px;
  border-top-left-radius:30px;
  border-bottom-left-radius:0px;
  margin-left:10px;
  position:relative;
  top:21px;
  display:inline-block;
  background-color: rgb(212, 212, 212);
}
.right{
  height:55px;
  background-color: rgb(212, 212, 212);
  display:inline-block;
  border-top-left-radius:10px;
  border-top-right-radius:10px;
  border-bottom-right-radius:10px;
  line-height:55px;
  font-size:17px;
  padding-left:10px;
  padding-right:10px;
}

.box1{
  width:460px;
  height:80px;
  padding-left: 10%;
}

.name1{
  text-align:right;
  margin-right:10px;
  font-size: 14px;
  font-weight:bold;
}

.left1{
  height:10px;
  width:6px;
  border-top-right-radius:30px;
  border-bottom-right-radius:0px;
  margin-right:10px;
  position:relative;
  top:45px;
  display:inline-block;
  background-color: #9eccaf;
  float:right;
}

.right1{
  height:55px;
  background-color: #9eccaf;
  display:inline-block;
  border-top-left-radius:10px;
  border-top-right-radius:10px;
  border-bottom-left-radius:10px;
  line-height:55px;
  font-size:17px;
  padding-left:10px;
  padding-right:10px;
  float:right;
}
@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
body {
  font-family: 'NEXON Lv1 Gothic OTF';
}
</style>
