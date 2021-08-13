const loginUser = {
  id: '',
  name: '',
  department: '',
  position: ''
}

const columns = [
  // {
  //   name: 'type',
  //   required: true,
  //   label: ' ',
  //   align: 'left',
  //   field: row => row.type,
  //   format: val => `${val}`,
  //   sortable: true
  // },
  { name: 'id', align: 'center', label: '글번호', field: 'id', sortable: true },
  { name: 'title', align: 'center', label: '제목', field: 'title', sortable: true },
  { name: 'writer', label: '글쓴이', field: 'writer', sortable: true },
  { name: 'registrationTime', label: '작성시간', field: 'registrationTime', sortable: true },
  { name: 'view', label: '조회수', field: 'view', sortable: true }
]
const rows = []

const columnsM = [
  // {
  //   name: 'type',
  //   // required: true,
  //   label: ' ',
  //   align: 'left',
  //   field: row => row.type,
  //   format: val => `${val}`,
  //   sortable: true
  // },
  { name: 'title', align: 'center', label: '제목', field: 'title', sortable: true },
  { name: 'writer', align: 'center', label: '글쓴이', field: 'writer', sortable: true },
  { name: 'view', align: 'center', label: '조회수', field: 'view', sortable: true }
]
const rowsM = []

const boardList = []
const pageNumber = 1

const rooms = []

const checkInTime = ''
const checkOutTime = ''
const totalHourOfWeek = ''

const detail = {
  comments: [],
  content: '',
  id: '',
  registrationTime: '',
  title: '',
  typeId: '',
  view: '',
  writer: ''
}

const comments = []

const department = []

const fileInfoList = []

const userInfo = {
  department: '',
  email: '',
  holiday: '',
  name: '',
  position: '',
  profileLocation: '',
  userId: '',
  dpID: '',
  userState: ''
}

const roomName = ''

const montlyWork = []

const memberList = []

const chatMode = false

const todoList = []

const markList = []
const mark = false
const dmRoomList = []
const dmHistory = []

const mode1 = true
const mode2 = true
const mode3 = true
const mode4 = true
const mode5 = true
const mode6 = true

export default function () {
  return {
    loginUser: loginUser,
    userId: '',
    name: '',
    columns: columns,
    rows: rows,
    boardList: boardList,
    pageNumber: pageNumber,
    rooms: rooms,
    columnsM: columnsM,
    rowsM: rowsM,
    checkInTime: checkInTime,
    checkOutTime: checkOutTime,
    detail: detail,
    department: department,
    roomName: roomName,
    comments: comments,
    userInfo: userInfo,
    montlyWork: montlyWork,
    totalHourOfWeek: totalHourOfWeek,
    fileInfoList: fileInfoList,
    memberList: memberList,
    chatMode: chatMode,
    todoList: todoList,
    markList: markList,
    mark: mark,
    dmRoomList: dmRoomList,
    dmHistory: dmHistory,
    mode1: mode1,
    mode2: mode2,
    mode3: mode3,
    mode4: mode4,
    mode5: mode5,
    mode6: mode6
  }
}
