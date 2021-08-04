<template>
  <Header></Header>
  <div>
    <Side></Side>
    <div id="container">
      <div id="wrapper">
        <div id="join" class="animate join">
          <h1>Join a Room</h1>
          <form onsubmit="register(); return false;" accept-charset="UTF-8">
            <p>
              <input
                type="text"
                name="name"
                value=""
                id="name"
                placeholder="Username"
                required
              />
            </p>
            <p>
              <input
                type="text"
                name="room"
                value=""
                id="roomName"
                placeholder="Room"
                required
              />
            </p>
            <p class="submit">
              <input type="submit" name="commit" value="Join!" />
            </p>
          </form>
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
      </div>
    </div>
    <a href='http://localhost:8080/chat/rooms'/>
  </div>
</template>
<script>
import { defineComponent, onMounted } from 'vue'
import Header from '../components/header.vue'
import Side from '../components/side.vue'
import { useStore } from 'vuex'

export default defineComponent({
  setup () {
    const store = useStore()
    onMounted(() => {
      store.dispatch('module/getRooms', {}).then(function (result) {
        console.log(result.data)
      })
    })
  },
  components: {
    Header,
    Side
  }
})
</script>
<style></style>
