<template>
    <Header></Header>
    <div>
        <Side></Side>
        <div id="container">
            <div id="wrapper">
                <div id="join" class="animate join">
                    <!-- <h1>Join a Room</h1> -->
                    <form onsubmit="register(); return false;" accept-charset="UTF-8">
                        <p>
                            <input style="margin-top:10px;" type="text" name="room" value="" id="roomName" placeholder="Room" required>
                        </p>
                        <p>
                            <input type="text" name="name" value="" id="name" placeholder="Username" required>
                        </p>
                        <p class="submit"><input id="submit" type="submit" name="commit" value="회의생성">
                        </p>
                    </form>
                </div>
                <div id="room" style="display: none;">
                    <!-- <h2 id="room-header"></h2> -->
                    <div id="participants"></div>
                    <div id="chat"></div>
                    <input type="button" id="button-leave" onmouseup="leaveRoom();" value="나가기">
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import { defineComponent } from 'vue'
import Header from '../components/header.vue'
import Side from '../components/side.vue'

export default defineComponent({
  setup () {},
  components: {
    Header,
    Side
  },
  data () {
    return {
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
</style>
