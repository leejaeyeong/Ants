<template>
  <div id="container">
      <div id="wrapper">
          <div id="join" class="animate join">
              <!-- <h1>Join a Room</h1> -->
              <form onsubmit="register(); return false;" accept-charset="UTF-8">
                  <p>
                      <input v-show="false" v-model="state.roomName" style="margin-top:10px;" type="text" name="room" id="roomName" placeholder="Room" required>
                  </p>
                  <p>
                      <input v-show="false" v-model="state.id" type="text" name="name" id="name" placeholder="Username" required>
                  </p>
                  <p class="submit"><input id="submit" type="submit" name="commit" value="회의생성">
                  </p>
              </form>
              <div class="q-pa-md row items-start q-gutter-md">
                <q-card v-for="room in rooms" :key="room.id" class="my-card">
                    <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
                      <div class="absolute-bottom">
                        <div class="text-h6">{{ room.roomName }}</div>
                        <div class="text-subtitle2">{{ room.id }}</div>
                      </div>
                    </q-img>
                    <q-card-actions>
                      <!-- <q-btn class="enter">회의입장</q-btn> -->
                      <input class="enter" type="submit" name="commit" value="회의입장">
                    </q-card-actions>
                </q-card>
              </div>
          </div>
          <div id="room" style="display: none;">
              <!-- <h2 id="room-header"></h2> -->
              <div id="participants"></div>
              <div id="chat"></div>
              <input type="button" id="button-leave" onmouseup="leaveRoom();" value="나가기">
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
    const rooms = computed(() => store.getters['module/getRooms'])
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
      state
    }
  }
})
</script>
<style>
#submit{
    background-color:#18C75E;
    font-weight:bold;
    color:white;
    padding:10px;
    font-size:20px;
    cursor: pointer;
    border:0.5px solid #18C75E;
    border-radius:5px;
    position:absolute;
    top:690px;
    left:1370px;
}
#submit:hover{
    opacity: 0.7;
}
#participants{
    width:1150px;
    height:690px;
    border:1px solid green;
    position:absolute;
    left:75px;
}
#chat{
    width:295px;
    height:690px;
    border:1px solid blue;
    position:absolute;
    left:1225px;
}
#button-leave{
    position:absolute;
    top:90%;
    background-color:#18C75E;
    font-weight:bold;
    color:white;
    padding:10px;
    font-size:20px;
    cursor: pointer;
    border:0.5px solid #18C75E;
    border-radius:5px;
}
.my-card{
  width:300px;
  margin:30px 0 0 30px;
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
</style>
