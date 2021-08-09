<template>
  <div class="content" align="center">
    <div class="content-title">
        <div class="row">
          <div class="col-2">
          </div>
          <div class="col-6">
            <q-input outlined v-model="text" :dense="dense" />
          </div>
          <div class="col-2">
            <q-btn class="search-btn">자료검색</q-btn>
          </div>
        </div>
    </div>
    <div class="content-body">
      <div class="q-pa-md">
    <q-table
      title="Treats"
      :rows="rows"
      :columns="columns"
      row-key="name"
      v-model:pagination="pagination"
      hide-pagination
    >
      <template v-slot:body="props">
        <q-tr :props="props">
          <q-td
            v-for="col in props.cols"
            :key="col.name"
            :props="props"
          >
            <span v-if="col.name !='Image'" >{{ col.value }}</span>
            <q-avatar v-if="col.name =='Image'" size="50px" class="shadow-10">
              <img :src="props.row.image">
            </q-avatar>
          </q-td>
        </q-tr>
      </template>
    </q-table>

    <div class="row justify-center q-mt-md">
      <q-pagination
        v-model="pagination.page"
        color="grey-8"
        :max="pagesNumber"
        size="sm"
      />
    </div>
  </div>
    </div>
  </div>
</template>

<script>
import { defineComponent, ref, computed } from 'vue'
// import { useRouter } from 'vue-router'
import { useStore } from 'vuex'

const columns = [
  { name: 'Image', label: '파일 분류', field: 'image', sortable: true, style: 'width: 10px' },
  {
    name: 'desc',
    required: true,
    label: 'Dessert (100g serving)',
    align: 'center',
    field: row => row.name,
    format: val => `${val}`,
    sortable: true
  },
  { name: 'calories', align: 'center', label: 'Calories', field: 'calories', sortable: true },
  { name: 'fat', label: 'Fat (g)', field: 'fat', sortable: true },
  { name: 'carbs', label: 'Carbs (g)', field: 'carbs' },
  { name: 'protein', label: 'Protein (g)', field: 'protein' }
]

const rows = [
  {
    name: 'Frozen Yogurt',
    calories: 159,
    fat: 6.0,
    carbs: 24,
    protein: 4.0,
    image: 'https://i.pinimg.com/474x/ea/83/d6/ea83d672e55bdda2fa44e676eacad9ff.jpg'
  },
  {
    name: 'Ice cream sandwich',
    calories: 237,
    fat: 9.0,
    carbs: 37,
    protein: 4.3,
    image: 'https://i.pinimg.com/474x/ea/83/d6/ea83d672e55bdda2fa44e676eacad9ff.jpg'
  },
  {
    name: 'Eclair',
    calories: 262,
    fat: 16.0,
    carbs: 23,
    protein: 6.0,
    image: 'https://aux.iconspalace.com/uploads/freeform-powerpoint-icon-256.png'
  },
  {
    name: 'Cupcake',
    calories: 305,
    fat: 3.7,
    carbs: 67,
    protein: 4.3,
    image: 'https://aux.iconspalace.com/uploads/freeform-powerpoint-icon-256.png'
  },
  {
    name: 'Gingerbread',
    calories: 356,
    fat: 16.0,
    carbs: 49,
    protein: 3.9,
    image: 'https://aux.iconspalace.com/uploads/freeform-powerpoint-icon-256.png'
  },
  {
    name: 'Jelly bean',
    calories: 375,
    fat: 0.0,
    carbs: 94,
    protein: 0.0,
    image: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFEz9UnOx3StKSJUQs12DuWje3MwDOV6yAfufygK38zgZIuNsizJimqpCRI6ae2gbJuD0&usqp=CAU'
  },
  {
    name: 'Lollipop',
    calories: 392,
    fat: 0.2,
    carbs: 98,
    protein: 0,
    image: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFEz9UnOx3StKSJUQs12DuWje3MwDOV6yAfufygK38zgZIuNsizJimqpCRI6ae2gbJuD0&usqp=CAU'
  },
  {
    name: 'Honeycomb',
    calories: 408,
    fat: 3.2,
    carbs: 87,
    protein: 6.5,
    image: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFEz9UnOx3StKSJUQs12DuWje3MwDOV6yAfufygK38zgZIuNsizJimqpCRI6ae2gbJuD0&usqp=CAU'
  }
  // {
  //   name: 'Donut',
  //   calories: 452,
  //   fat: 25.0,
  //   carbs: 51,
  //   protein: 4.9
  // },
  // {
  //   name: 'KitKat',
  //   calories: 518,
  //   fat: 26.0,
  //   carbs: 65,
  //   protein: 7
  // }
]

export default defineComponent({
  name: 'DonwloadsContent',
  components: {
  },
  setup () {
    const store = useStore()
    console.log(store)
    const pagination = ref({
      sortBy: 'desc',
      descending: false,
      page: 2,
      rowsPerPage: 6
      // rowsNumber: xx if getting data from a server
    })
    return {
      text: ref(''),
      dense: ref(false),
      pagination,
      columns,
      rows,
      pagesNumber: computed(() => Math.ceil(rows.length / pagination.value.rowsPerPage))
    }
  }
})
</script>

<style scoped>
.content {
  width: 1400px;
  height: 800px;
  position: absolute;
  left: 450px;
  top: 85px;
  /* background: orange; */
}
.content-title {
  /* background: yellowgreen; */
  width: 100%;
  height: 80px;
  margin-top: 50px;
}
.content-body {
  width: 80%;
}
.margin-auto {
  margin-left: auto;
  margin-right: auto;
}
.search-btn {
  background-color: #00BF6F;
  margin-left: 20px;
  height: 50px;
}
</style>
