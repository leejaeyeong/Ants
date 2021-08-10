<script>
import { defineComponent } from 'vue'
import { Bar } from 'vue3-chart-v2'

export default defineComponent({
  name: 'Chart',
  extends: Bar,
  mounted () {
    // Overwriting base render method with actual data.
    this.renderChart({
      labels: ['월요일', '화요일', '수요일', '목요일', '금요일'],
      datasets: [
        {
          label: '이번 주 근무시간',
          backgroundColor: '#f87979',
          data: [6, 7, 8, 11, 9]
        }
      ]
    })
  }
})
</script>
<style>

</style>
