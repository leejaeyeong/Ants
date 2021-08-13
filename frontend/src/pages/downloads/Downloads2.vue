<template>
<div>
  <downloads-side></downloads-side>
  <downloads-content></downloads-content>
</div>
</template>

<script>
import { defineComponent } from 'vue'
import DownloadsSide from './DownloadsSide.vue'
import DownloadsContent from './DownloadsContent.vue'

export default defineComponent({
  name: 'downloads2',
  components: {
    DownloadsSide,
    DownloadsContent
  },
  setup () {
    return {
    }
  }
})
</script>

<style scoped>
.row {
  margin-top: 20px;
  margin-bottom: 20px;
  /* border: 1px solid rgba(86,61,124,.2) */
}
.margin-auto {
  margin-left: auto;
  margin-right: auto;
}
.upload-btn {
  background-color: #00BF6F;
}
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateX(-20px);
  }
  to {
    opacity: 3;
    transform: none;
  }
}
.side {
  /* animation: fadeIn 0.5s ease-in-out; */
}
</style>
