<template>
  <div id="allBoard">
    <div id="board">
      <q-select
        color="teal"
        outlined
        v-model="form.type"
        :options="options"
        style="margin-top: 25px; width: 15%; margin-left: 30px; float: left"
        label="분류"
      />
      <q-input
        color="teal"
        v-model="form.title"
        outlined
        
        style="
          width: 54%;
          margin-left: 15px;
          margin-top: 25px;
          display: inline-block;
          color: white;
        "
        label="제목"
      />

      <!-- <q-btn
        @click="imgLabelClick"
        style="
          background-color: #00bf6f;
          margin-left: 10px;
          padding: 10px;
          margin-top: -20px;
          color: white;
        "
        >이미지 업로드</q-btn
      > -->
      <div>
        <!-- <label for="image_uploads" style="color: black;" @click="imgLabelClick">Choose images to upload (PNG, JPG)</label> -->
        <input v-if="false"
          ref="imageInput"
          type="file"
          style="color: black; opacity: 0"
          @change="onChangeImages"
          accept=".jpg, .jpeg, .png"
          id="profile_img_upload"
        />
      </div>
      <img
        v-if="form.tmp"
        :src="form.tmp"
        style="border-radius: 50%; width: 100px; height: 100px"
        id="thumb"
      />
      <div class="q-pa-md q-gutter-sm">
        <q-editor
          v-model="form.content"
          min-height="32rem"
          style="margin-left: 22px; font-size: 30px"
        />
      </div>
      <q-btn
        @click="back"
        style="float: right; color: white; background-color: rgb(255, 167, 38); margin-right: 10px;"
        label="뒤로"
      />
      <q-btn
        @click="regist"
        style="
          float: right;
          margin-left: 10px;
          margin-right: 10px;
          color: white;
          background-color: #00bf6f;
        "
        label="등록"
      />
    </div>
  </div>
</template>

<script>
import { ref, defineComponent, reactive } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

export default defineComponent({
  name: "write",
  setup() {
    const store = useStore();
    const router = useRouter();
    const editor = ref("");
    const Swal = require("sweetalert2");
    const form = reactive({
      type: "",
      title: "",
      content: "",
      image: null,
      tmp: "",
      writer: "",
    });
    const options = ["공지사항", "익명게시판", "프론트엔드", "백엔드", "플랫폼", "마케팅"];

    const back = function () {
      router.back();
      console.log(form.title);
      console.log(form.content);
    };
    let rows = [];
    function regist() {
      const frm = new FormData();

      for (let i = 0; i < options.length; i++) {
        if(form.type === options[i]) {
          frm.append("type", i + 1)
          break
        }
      }
      // frm.append("type", 1); //form.type
      frm.append("writer", localStorage.getItem("id"));
      frm.append("title", form.title);
      console.log(form.content)
      let x = form.content.split('<div>')
      let temp = ''
      for (let i = 0; i < x.length; i++) {
        temp += x[i] + '\n'
      }
      console.log(temp)
      x = temp.split('</div>')
      temp = ''
      // form.content = temp.split('</div>')
      for (let i = 0; i < x.length; i++) {
        temp += x[i]
      }
      x = temp.split('<br>')
      temp = ''
      for (let i = 0; i < x.length; i++) {
        temp += x[i]
      }
      console.log(temp)
      frm.append("content", temp);
      store
        .dispatch("module/registBoard", frm)
        .then(function (result) {
          console.log(result);
          store
            .dispatch("module/board", {})
            .then(function (result) {
              for (let i = 0; i < result.data.length; i++) {
                console.log(i + "번쨰 포문");
                result.data[i].registrationTime = result.data[i].registrationTime.substr(0, 16);
                rows.push(result.data[i]);
              }
              store.commit("module/setRows", rows);
              rows = [];
              const pn = Math.ceil(rows.length / 8);
              store.commit("module/setPageNumber", pn);
              Swal.fire({
                title:
                  '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">게시글이 등록되었습니다.</span>',
                confirmButtonColor: "#19CE60",
                confirmButtonText:
                  '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>',
              });
              router.push("/board");
            })
            .catch(function (err) {
              console.log(err, "에러임");
              alert("오류발생", err);
            });
        })
        .catch(function (err) {
          console.log(err);
          alert("마지막 캐치 오류");
        });
    }
    function onChangeImages(e) {
      console.log(e.target.files);
      console.log(e.target);
      const file = e.target.files[0]; // Get first index in files
      this.form.tmp = URL.createObjectURL(file);
      this.form.image = file; // Create File URL
    }
    // 기존 파일업로드 버튼 숨기고 q-btn으로 대체
    function imgLabelClick() {
      const inputImg = document.getElementById("profile_img_upload"); // input file 태그 저장
      console.log(inputImg);
      inputImg.click(); // 클릭이벤트 실행
    }

    return {
      back,
      form,
      editor,
      options,
      imgLabelClick,
      onChangeImages,
      regist,
    };
  },
});
</script>

<style scoped>
#allBoard{
  width:1580px;
  height:909px;
  background-color: rgb(242, 247, 244);
  position: absolute;
  top:60px;
}
#board {
  width: 1350px;
  height: 750px;
  position: absolute;
  top:30px;
  left:100px;
}
@font-face {
  font-family: "NEXON Lv1 Gothic OTF";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
div {
  font-family: "NEXON Lv1 Gothic OTF";
}
span {
  font-family: "NEXON Lv1 Gothic OTF";
}
.btn {
}
</style>
