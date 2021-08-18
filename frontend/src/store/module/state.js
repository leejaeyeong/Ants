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
  { name: 'title', align: 'left', label: '제목', field: 'title', sortable: true },
  { name: 'userName', label: '글쓴이', field: 'userName', sortable: true },
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
  { name: 'title', align: 'left', label: '제목', field: 'title', sortable: true },
  { name: 'userName', align: 'center', label: '글쓴이', field: 'userName', sortable: true },
  { name: 'view', align: 'center', label: '조회수', field: 'view', sortable: true }
]

const fileImage = {
  ppt: 'https://aux.iconspalace.com/uploads/freeform-powerpoint-icon-256.png',
  pptx: 'https://aux.iconspalace.com/uploads/freeform-powerpoint-icon-256.png',
  zip: 'https://blog.kakaocdn.net/dn/dK3cLI/btqGteLVjDW/ddq5d2gBFO0kxK6eMkreW1/img.png',
  pdf: 'https://blog.kakaocdn.net/dn/cj4Y3U/btqMPi7uAh8/sYik4nsvvqUmG36Hhbwwj1/img.png',
  xlsx: 'data:image/png;base64,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',
  hwp: 'data:image/png;base64,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',
  docx: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ7Gc35vru52VOvdoidjxRXOHZjLdFOSJND7fh-ADfqKBqz3MMYLbW2II5thFpukmye2nY&usqp=CAU',
  jpg: 'https://us.123rf.com/450wm/dxinerz/dxinerz1509/dxinerz150901426/45612323-jpg-%ED%8C%8C%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg?ver=6',
  JPG: 'https://us.123rf.com/450wm/dxinerz/dxinerz1509/dxinerz150901426/45612323-jpg-%ED%8C%8C%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg?ver=6',
  png: 'https://us.123rf.com/450wm/dxinerz/dxinerz1509/dxinerz150901426/45612323-jpg-%ED%8C%8C%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg?ver=6',
  PNG: 'https://us.123rf.com/450wm/dxinerz/dxinerz1509/dxinerz150901426/45612323-jpg-%ED%8C%8C%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg?ver=6',
  other: 'https://www.geochang.go.kr/portal/popup/20190111/20190111.gif'
}
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
const rtcFileList = []

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

const boardType = {
  name: '',
  intro: ''
}

const dayLog = []

const linkList = []

// const modePx1 = [15, 20]
// const modePx2 = [561, 20]
// const modePx3 = [15, 385]
// const modePx4 = [261, 385]
// const modePx5 = [15, 1215]
// const modePx6 = [381, 1215]

const downMode = false

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
    mode6: mode6,
    fileImage: fileImage,
    // modePx1: modePx1,
    // modePx2: modePx2,
    // modePx3: modePx3,
    // modePx4: modePx4,
    // modePx5: modePx5,
    // modePx6: modePx6
    boardType: boardType,
    downMode: downMode,
    rtcFileList: rtcFileList,
    dayLog: dayLog,
    linkList: linkList
  }
}
