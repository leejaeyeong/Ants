export function someMutation (/* state */) {
}

export function setLoginUser (state, user) {
  state.loginUser = user
}

export function setColumns (state, columns) {
  state.columns = columns
}

export function setRows (state, rows) {
  state.rows = rows
}

export function setColumnsM (state, columnsM) {
  state.columnsM = columnsM
}

export function setRowsM (state, rowsM) {
  state.rowsM = rowsM
}

export function setBoardList (state, boardList) {
  state.boardList = boardList
}

export function setPageNumber (state, pageNumber) {
  state.pageNumber = pageNumber
}

export function setRooms (state, rooms) {
  state.rooms = rooms
}

export function setCheckInTime (state, CheckInTime) {
  state.CheckInTime = CheckInTime
}

export function setCheckOutTime (state, CheckOutTime) {
  state.CheckOutTime = CheckOutTime
}

export function setDetail (state, detail) {
  state.detail = detail
}

export function setDepartmentInfo (state, department) {
  state.department = department
}

export function setUserInfo (state, userInfo) {
  state.userInfo = userInfo
}

export function setRoomName (state, roomName) {
  state.roomName = roomName
}

export function setComments (state, comments) {
  state.comments = comments
}
export function setMontlywork (state, montlyWork) {
  state.montlyWork = montlyWork
}
export function setFileinfoList (state, fileInfoList) {
  state.fileInfoList = fileInfoList
  console.log('setfileinfo 찍혀야지')
}
export function setTotalHourOfWeek (state, totalHourOfWeek) {
  state.totalHourOfWeek = totalHourOfWeek
}

export function setAllusers (state, allusers) {
  state.allusers = allusers
  console.log(allusers, '뮤테이션')
  console.log(state.allusers)
}

export function setMemberList (state, memberList) {
  state.memberList = memberList
  console.log(memberList, '뮤테이션')
}

export function setChatMode (state, chatMode) {
  state.chatMode = chatMode
}

export function setTodoList (state, todoList) {
  state.todoList = todoList
}
