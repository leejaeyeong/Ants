<template>
    <div class="q-mt-md">
        <q-fab
            id="dmBtn"
            v-model="dm"
            label="메신저"
            vertical-actions-align="left"
            color="purple"
            icon="keyboard_arrow_down"
            direction="down"
        >
            <div id="dm">
              <div v-show="!chatMode">
                <h5 style="margin:10px 10px; font-weight:bold;">그룹원 목록</h5>
                <div id="list">
                    <div @click="enter" class="det" v-for="member in memberList" :key="member.userId">
                      <div class="row">
                        <div class="col-3">
                          <img :src="member.profileLocation" style="margin-top:8px; width:70px; height:70px; border-radius:50px; margin-left:20px;">
                        </div>
                        <div class="col-9" style="line-height:85px;">
                          {{ member.name }} {{ member.department }}
                        </div>
                      </div>
                    </div>
                </div>
              </div>
              <div id="chat" v-show="chatMode">
                <div id="conversation">
                  <q-btn @click="back" round color="amber" icon="reply" style="float:right; margin-top:5px; margin-right:5px;"/>
                </div>
                <div id="form">
                  <input id="chatMessage" style="display:inline-block; width:400px; height:47px;" class="form-control" placeholder="메세지 입력"/>
                  <button id="chatSend" class="btn btn-default" type="submit">보내기</button>
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
    onMounted(() => {
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
    const enter = function () {
      store.commit('module/setChatMode', true)
    }
    const back = function () {
      store.commit('module/setChatMode', false)
    }
    return {
      dm: ref(false),
      memberList,
      chatMode,
      enter,
      back
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
  background-color:whitesmoke;
  border-radius:15px;
}
#list{
  width:90%;
  height:448px;
  margin: 0 auto;
  border-bottom:1px solid rgb(212, 212, 212);
  border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px;
  overflow-y: scroll;
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
#conversation{
  height:450px;
  overflow-y:scroll;
}
#form{
  height:50px;
}
#chat{
  width:100%;
  height:448px;
}
#chatSend{
  background-color:#18C75E;
  border:0.5px solid #18C75E;
  font-weight:bold;
  color:white;
  padding:10px;
  font-size:15px;
  cursor:pointer;
  width:99px;
  margin-top:3px;
}
@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'Eulyoo1945-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2102-01@1.0/Eulyoo1945-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
body {
  font-family: 'NEXON Lv1 Gothic OTF';
}
</style>
