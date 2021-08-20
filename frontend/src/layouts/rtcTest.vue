<template>
  <div id="container">
      <div id="wrapper">
          <div id="join" style="position:absolute; left:60px; top:-30px;" class="animate join">
              <!-- <h1>Join a Room</h1> -->
              <div v-if="rooms.length === 0" style="font-size:45px; font-weight: bold; color:grey; position:absolute; top:400px; left:530px; width:700px;">
                현재 개설된 회의가 없습니다.
              </div>
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
              <div id="down" v-if="downMode">
                <q-btn @click="reRtcFileList" round style="background-color:#249752; color:white; float:right; margin-right:5px; margin-top:5px; width:10px;" icon="refresh" />
                <div id="registFile">
                   <!-- 파일 지정 상태 -->
                   <div v-if="form.images" class="margin-auto file-mounted"
                    @drop.prevent="dropInputTag($event)"
                    @dragover.prevent>
                    <div style="margin-left: 60px; margin-top:10px;">
                      <img :src="form.images" alt="image" class="present-image"><br>
                    </div>
                    <div align="center">{{form.image.name}}</div>
                    <div align="center">{{ fileSizeFilter }}</div>
                    </div>
                    <!-- 파일 미지정 상태 -->
                    <div id="unSelectFileFrame" v-else class="margin-auto file-frame"
                      @click="clickInputTag()"
                      @drop.prevent="dropInputTag($event)"
                      @dragenter="dragEnter()"
                      @dragleave="dragLeave()"
                      @dragover.prevent>
                      <div align="center" style="line-height:150px">파일을 끌어서 올려주세요</div>
                      <!-- <input ref="image" id="input" type="file" name="image" @change="presentImage()" v-show="false"> -->
                    </div>
                    <div style="display:inline-block; margin-left:30px; position:absolute; top:80px; left:255px;">
                      <div >
                        <q-btn @click="regist" class="upload-btn ">파일 업로드</q-btn>
                      </div>
                      <div style="margin-top:10px;">
                        <q-btn @click="reset" class="reset-btn">초기화</q-btn>
                      </div>
                    </div>
                </div>
                <div id="viewFile" class="row">
                  <div class="col-3" v-for="file in rtcFileList" :key="file.id">
                    <q-avatar size="70px" class="shadow-10" style="cursor:pointer; margin-top:40px; margin-left:27px;" @click="downloadFile(file.id)">
                      <img :src="file.imageLocation">
                    </q-avatar>
                    <div style="margin-top:15px; text-align:center;">
                      {{ file.fileName }}
                    </div>
                  </div>
                </div>
              </div>
              <div id="chatchat">
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
              <div id="btDiv">
                <!-- 나가기 버튼 -->
                <form class="form-inline" onsubmit="disconnect(); return false;" accept-charset="UTF-8">
                    <form style="display:inline-block;" onsubmit="rtcMute($(this).find('input').eq(0).val()); return false;">
                      <input :value="id" style="display:none">
                      <!-- <q-icon style="font-size: 2.8em; color: white;" name="mic" type="submit"/> -->
                      <button id="bt1" type="submit" value="mute"/>
                    </form>
                    <form style="display:inline-block;" onsubmit="rtcVideoVision($(this).find('input').eq(0).val()); return false;">
                      <input :value="id" style="display:none">
                      <!-- <q-icon style="font-size: 2.8em; color: white;" name="photo_camera"/> -->
                      <button id="bt2" type="submit" value="mute"/>
                    </form>
                  <input type="button" id="downBt" @click="downView">
                  <input type="button" id="button-leave" onmouseup="leaveRoom();">
                </form>
              </div>
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
  computed: {
    fileSizeFilter () {
      const size = this.form.image.size
      let convertedFileSize = ''
      if (size / 1000000 >= 1) {
        convertedFileSize = (size / 1000000).toFixed(2) + 'MB'
      } else if (size / 1000 >= 1) {
        convertedFileSize = (size / 1000).toFixed(2) + 'KB'
      } else {
        convertedFileSize = size + 'B'
      }
      return convertedFileSize
    }
  },
  setup () {
    const Swal = require('sweetalert2')
    function sweetAlert (title) {
      Swal.fire({
        title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">' + title + '</span>',
        confirmButtonColor: '#19CE60',
        confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
      })
    }
    const store = useStore()
    const state = reactive({
      id: localStorage.getItem('id'),
      roomName: localStorage.getItem('id') + '님의 회의방'
    })
    const form = reactive({
      image: null,
      tmp: '',
      images: '',
      subSdieVisible: false
    })
    const user = store.getters['module/getLoginUser']
    const id = user.id
    const name = user.name
    const rooms = computed(() => store.getters['module/getRooms'])
    const rName = computed(() => store.getters['module/getRoomName'])
    const getRoomName = function (rName) {
      store.commit('module/setRoomName', rName)
    }
    const downMode = computed(() => store.getters['module/getDownMode'])
    const rtcFileList = computed(() => store.getters['module/getRtcFileList'])
    const fileImage = computed(() => store.getters['module/getFileImage']).value
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

      store.commit('module/setDownMode', false)
    })
    const downView = function () {
      const tmp = document.getElementById('chatchat')
      const tmp2 = document.getElementById('chatDiv')
      const mode = store.getters['module/getDownMode']
      if (!mode) {
        tmp.style.height = 449 + 'px'
        tmp.style.top = 428 + 'px'
        tmp2.style.height = 375 + 'px'
      } else {
        tmp.style.height = 909 + 'px'
        tmp.style.top = 0 + 'px'
        tmp2.style.height = 795 + 'px'
      }
      store.commit('module/setDownMode', !mode)

      store.dispatch('module/loadFileData')
        .then(function (result) {
          var count = 0
          var tmp = []
          for (let i = result.data.length - 1; i >= 0; i--) {
            const extension = result.data[i].fileExtension.substring(1, result.data[i].fileExtension.length)
            result.data[i].imageLocation = (fileImage[extension] === undefined) ? fileImage.other : fileImage[extension]
            if (count === 4) {
              break
            }
            tmp.push(result.data[i])
            count++
          }
          store.commit('module/setRtcFileList', tmp)
          console.log(result.data)
          tmp = []
        })
    }
    const reRtcFileList = function () {
      store.dispatch('module/loadFileData')
        .then(function (result) {
          var count = 0
          var tmp = []
          for (let i = result.data.length - 1; i >= 0; i--) {
            const extension = result.data[i].fileExtension.substring(1, result.data[i].fileExtension.length)
            result.data[i].imageLocation = (fileImage[extension] === undefined) ? fileImage.other : fileImage[extension]
            if (count === 4) {
              break
            }
            tmp.push(result.data[i])
            count++
          }
          store.commit('module/setRtcFileList', tmp)
          tmp = []
        })
    }
    function downloadFile (id) {
      console.log(id)
      store.dispatch('module/downloadFile', id)
        .then(function (result) {
          const diposition = result.headers['content-disposition']
          const fileName = diposition.split("''")[1]
          console.log(result.headers['content-type'])
          console.log(diposition)
          const url = window.URL.createObjectURL(new Blob([result.data]))
          const anchor = document.createElement('a')
          anchor.href = url
          anchor.setAttribute('download', fileName)
          document.body.appendChild(anchor)
          anchor.click()
          anchor.remove()
          window.URL.revokeObjectURL(url)
        })
        .catch(function (err) {
          console.log(err)
        })
    }
    function dropInputTag (event) {
      console.log('영역안에 파일이 지정됨')
      const file = Array.from(event.dataTransfer.files, v => v)[0]
      // 파일이 jpg, png가 아니면 다른 파일 객체에
      const extension = file.name.split('.')[1] // jpg, zip 등

      const imageExtension = ['jpg', 'JPG', 'jpeg', 'JPEG', 'png', 'PNG', 'gif', 'GIF']
      if (imageExtension.includes(extension)) {
        form.images = URL.createObjectURL(file)
      } else {
        form.images = (fileImage[extension] === undefined) ? fileImage.other : fileImage[extension]
      }
      form.image = file
      // presentImage(file)
      console.log('영역안에 파일이 지정됨 end')
    }
    function clickInputTag () {
      console.log('영역 안을 클릭함')
      console.log('영역 안을 클릭함 end')
    }
    function dragEnter () {
      const frame = document.getElementById('unSelectFileFrame')
      // frame.style.borderColor = 'green'
      // file-mounted
      // frame.className = 'file-mounted'
      frame.classList.add('file-mounted', 'margin-auto')
    }
    function dragLeave () {
      const frame = document.getElementById('unSelectFileFrame')
      frame.classList.remove('file-mounted')
      frame.classList.add('file-frame')
      // frame.style.borderColor = 'gray'
    }
    function reset () {
      form.image = null
      form.images = null
    }
    function regist () {
      if (form.image == null) {
        sweetAlert('업로드할 파일을 지정해주세요.')
        return
      }
      const frm = new FormData()
      frm.append('file', form.image)
      store.dispatch('module/uploadFile', frm)
        .then(function (result) {
          console.log(result.data)
          if (result.status === 200) {
            store.dispatch('module/loadFileData')
              .then(function (result) {
                var count = 0
                var tmp = []
                for (let i = result.data.length - 1; i >= 0; i--) {
                  const extension = result.data[i].fileExtension.substring(1, result.data[i].fileExtension.length)
                  result.data[i].imageLocation = (fileImage[extension] === undefined) ? fileImage.other : fileImage[extension]
                  if (count === 4) {
                    break
                  }
                  tmp.push(result.data[i])
                  count++
                }
                store.commit('module/setRtcFileList', tmp)
                tmp = []
              })
            sweetAlert('파일이 정상적으로 업로드 됐습니다.')
            return true
          }
        })
        .catch(function (err) {
          console.log(err)
          sweetAlert('파일 업로드 중 문제가 발생했습니다.')
        })
    }
    return {
      rooms,
      state,
      id,
      name,
      rName,
      getRoomName,
      downView,
      downMode,
      rtcFileList,
      reRtcFileList,
      downloadFile,
      form,
      dropInputTag,
      clickInputTag,
      dragEnter,
      dragLeave,
      reset,
      regist
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
    background-color:#249752;
    font-weight:bold;
    color:white;
    padding:10px;
    font-size:28px;
    cursor: pointer;
    border:0.5px solid #249752;
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
#down{
    position:absolute;
    left:1365px;
    width:490px;
    height:428px;
    border-left:0.5px solid rgb(212, 212, 212);
}
#registFile{
    width:490px;
    height:214px;
}
#viewFile{
    width:490px;
    height:214px;
}
.file-mounted {
  width: 220px;
  height: 160px;
  border: 2px dashed transparent;
  margin-top:45px;
  border-radius: 5%;
  animation: changeColor 1.8s infinite linear;
  margin-left:30px;
  display:inline-block;
}
.file-frame {
  width: 220px;
  height: 160px;
  border: 2px dashed gray;
  margin-top:45px;
  border-radius: 5%;
  margin-left:30px;
  display:inline-block;
}
.upload-btn {
  width:132px;
  font-size:15px;
  background-color: #00BF6F;
}
.reset-btn {
  width:132px;
  font-size:15px;
  background-color: wheat;
}
.present-image {
  width: 100px;
  height: 100px;
}
@keyframes changeColor {
  0% {
    border-color: rgb(255, 10, 10);
  }
  20% {
    border-color: rgb(236, 145, 7);
  }
  40% {
    border-color: rgb(225, 238, 37);
  }
  60% {
    border-color: rgb(20, 155, 49);
  }
  80% {
    border-color: rgb(32, 67, 143);
  }
  100% {
    border-color: rgb(22, 17, 97);
  }
}
#chatchat{
    width:490px;
    height:909px;
    border-left:1px solid rgb(212, 212, 212);
    border-top: 1px solid rgb(212, 212, 212);
    position:absolute;
    left:1365px;
}
.my-card{
  width:380px;
  margin:30px 0 0 30px;
  box-shadow: 5px 5px 5px;
}
.enter{
  background-color:#249752;
  border:0.5px solid #249752;
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
  background-color:#249752;
  border:0.5px solid #249752;
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
  height:795px;
  overflow-y:scroll;
}
.msg{
  font-size:20px;
}
#btDiv{
  width:240px;
  position:absolute;
  top:850px;
  left:555px;
}
#button-leave{
    width:50px;
    height:50px;
    border-radius:50px;
    border:0.5px solid #249752;
    display:inline-block;
    color:white;
    padding:10px;
    cursor: pointer;
    margin-left:10px;
    background: url('~assets/images/exit.png') no-repeat right center #249752;
}
#downBt{
    width:50px;
    height:50px;
    border-radius:50px;
    border:0.5px solid #249752;
    display:inline-block;
    color:white;
    padding:10px;
    cursor: pointer;
    margin-left:10px;
    background: url('~assets/images/download.png') no-repeat right center #249752;
}
#bt1{
  background: url('~assets/images/mic.png') no-repeat right center #249752;
  border-radius: 50px;
  width:50px;
  height:50px;
  cursor: pointer;
  display:inline-block;
  padding-left:5px;
  padding-top:5px;
  border:0.5px solid #249752;
}
#bt2{
  background: url('~assets/images/video.png') no-repeat right center #249752;
  border-radius: 50px;
  width:50px;
  height:50px;
  cursor: pointer;
  display:inline-block;
  padding-left:5px;
  padding-top:5px;
  border:0.5px solid #249752;
  margin-left:10px;
}
body {
  font-family: 'NEXON Lv1 Gothic OTF';
}
</style>
