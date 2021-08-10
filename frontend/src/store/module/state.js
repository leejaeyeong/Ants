const loginUser = {
  id: '',
  name: '',
  department: ''
}

const columns = [
  {
    name: 'id',
    required: true,
    label: '글번호',
    align: 'center',
    field: row => `${row.id}` ,
    format: val => `${val}`,
    sortable: true
  },
  { name: 'title', align: 'center', label: '제목', field: 'title', sortable: true },
  { name: 'writer', label: '글쓴이', field: 'writer', sortable: true },
  { name: 'registrationTime', label: '작성시간', field: 'registrationTime', sortable: true },
  { name: 'view', label: '조회수', field: 'view', sortable: true }
]
const rows = []

const columnsM = [
  {
    name: 'title',
    required: true,
    label: '제목',
    align: 'center',
    field: row => row.title,
    format: val => `${val}`,
    sortable: true
  },
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

const userInfo = {
  department: '',
  email: '',
  holiday: '',
  name: '',
  position: '',
  profileLocation: '',
  userId: '',
  dpID: ''
}

const roomName = ''

const memberList = []

const chatMode = false

const todoList = []

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
    totalHourOfWeek: totalHourOfWeek,
    memberList: memberList,
    chatMode: chatMode,
    todoList: todoList
  }
}
