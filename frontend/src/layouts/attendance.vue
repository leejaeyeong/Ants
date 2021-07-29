<template>
    <Header></Header>
    <div id="content">
        <Side></Side>
        <div id="main">
            <div id="calandar">
              <div>
                <q-splitter
                  v-model="splitterModel"
                  style="height: 450px"
                >
                  <template v-slot:before>
                    <div class="q-pa-md">
                      <q-date
                        v-model="date"
                        :events="events"
                        event-color="orange"
                      />
                    </div>
                  </template>

                  <template v-slot:after>
                    <q-tab-panels
                      v-model="date"
                      animated
                      transition-prev="jump-up"
                      transition-next="jump-up"
                    >
                      <q-tab-panel name="2021/08/01">
                        <div class="text-h4 q-mb-md">2019/02/01</div>
                        <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis praesentium cumque magnam odio iure quidem, quod illum numquam possimus obcaecati commodi minima assumenda consectetur culpa fuga nulla ullam. In, libero.</p>
                        <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis praesentium cumque magnam odio iure quidem, quod illum numquam possimus obcaecati commodi minima assumenda consectetur culpa fuga nulla ullam. In, libero.</p>
                      </q-tab-panel>

                      <q-tab-panel name="2021/08/05">
                        <div class="text-h4 q-mb-md">2019/02/05</div>
                        <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis praesentium cumque magnam odio iure quidem, quod illum numquam possimus obcaecati commodi minima assumenda consectetur culpa fuga nulla ullam. In, libero.</p>
                        <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis praesentium cumque magnam odio iure quidem, quod illum numquam possimus obcaecati commodi minima assumenda consectetur culpa fuga nulla ullam. In, libero.</p>
                      </q-tab-panel>

                      <q-tab-panel name="2021/08/06">
                        <div class="text-h4 q-mb-md">2019/02/06</div>
                        <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis praesentium cumque magnam odio iure quidem, quod illum numquam possimus obcaecati commodi minima assumenda consectetur culpa fuga nulla ullam. In, libero.</p>
                        <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis praesentium cumque magnam odio iure quidem, quod illum numquam possimus obcaecati commodi minima assumenda consectetur culpa fuga nulla ullam. In, libero.</p>
                        <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis praesentium cumque magnam odio iure quidem, quod illum numquam possimus obcaecati commodi minima assumenda consectetur culpa fuga nulla ullam. In, libero.</p>
                      </q-tab-panel>
                    </q-tab-panels>
                  </template>
                </q-splitter>
              </div>
            </div>
        </div>
    </div>
</template>

<script>
import { defineComponent, ref } from 'vue'
import Header from '../components/header.vue'
import Side from '../components/side.vue'

export default defineComponent({
  name: 'board',
  components: {
    Header,
    Side
  },
  setup () {
    return {
      splitterModel: ref(50),
      date: ref('2021/07/29'),
      events: ['2021/08/01', '2021/08/05', '2021/08/06']
    }
  }
})
</script>

<style scoped>
#content{
    width:100%;
    height: 680px;
}
#main{
    width:95%;
    height:692px;
    float:right;
    border-bottom:0.5px solid rgb(212, 212, 212);
    background-color:white;
}
</style>
