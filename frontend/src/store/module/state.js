const loginUser = {}
const columns = [
  {
    name: 'id',
    required: true,
    label: '글번호',
    align: 'center',
    field: row => row.id,
    format: val => `${val}`,
    sortable: true
  },
  { name: 'title', align: 'center', label: '제목', field: 'title', sortable: true },
  { name: 'writer', label: '글쓴이', field: 'writer', sortable: true },
  { name: 'registrationTime', label: '작성시간', field: 'registrationTime', sortable: true },
  { name: 'view', label: '조회수', field: 'view', sortable: true }
]

const rows = []

const boardList = []
const pageNumber = 1
const department = []
export default function () {
  return {
    loginUser: loginUser,
    userId: '',
    name: '',
    columns: columns,
    rows: rows,
    boardList: boardList,
    pageNumber: pageNumber,
    department: department
  }
}
