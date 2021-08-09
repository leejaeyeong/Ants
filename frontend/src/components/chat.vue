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
                <h5 style="margin:10px 10px; font-weight:bold;">그룹원 목록</h5>
                <div id="list">
                    <div id="detail">
                    </div>
                    <div id="detail">
                    </div>
                    <div id="detail">
                    </div>
                    <div id="detail">
                    </div>
                    <div id="detail">
                    </div>
                    <div id="detail">
                    </div>
                    <div id="detail">
                    </div>
                    <div id="detail">
                    </div>
                    <div id="detail">
                    </div>
                    <div id="detail">
                    </div>
                    <div id="detail">
                    </div>
                    <div id="detail">
                    </div>
                    <div id="detail">
                    </div>
                </div>
            </div>
        </q-fab>
    </div>
</template>

<script>
import { defineComponent, onMounted, ref } from 'vue'

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
    onMounted(() => {
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
    return {
      dm: ref(false)
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
  width:100%;
  height:448px;
  border:1px solid yellow;
  border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px;
  overflow-y: scroll;
}
#detail{
  width:100%;
  height:75px;
  border:1px solid red;
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
