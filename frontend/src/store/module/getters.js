
export function someGetter (/* state */) {
}

export function getLoginUser (state) {
  return state.loginUser
}

export function getColumns (state) {
  return state.columns
}

export function getRows (state) {
  return state.rows
}

export function getColumnsM (state) {
  return state.columnsM
}

export function getRowsM (state) {
  return state.rowsM
}

export function getBoardList (state) {
  return state.boardList
}

export function getPageNumber (state) {
  return state.pageNumber
}

export function getRooms (state) {
  return state.rooms
}

export function getCheckInTime (state) {
  return state.CheckInTime
}

export function getCheckOutTime (state) {
  return state.CheckOutTime
}

export function getDetail (state) {
  return state.detail
}

export function getDepartmentInfo (state) {
  return state.department
}

export function getUserInfo (state) {
  return state.userInfo
}

export function getRoomName (state) {
  return state.roomName
}

export function getComments (state) {
  return state.comments
}

export function getMontlywork (state) {
  return state.montlyWork
}
export function getFileInfoList (state) {
  console.log('getFileinfo 찍혀야지')
  return state.fileInfoList
}

export function getTotalHourOfWeek (state) {
  return state.totalHourOfWeek
}

export function getMemberList (state) {
  console.log(state, 'getters')
  return state.memberList
}

export function getChatMode (state) {
  return state.chatMode
}

export function getTodoList (state) {
  return state.todoList
}

export function getMarkList (state) {
  return state.markList
}

export function getMark (state) {
  return state.mark
}

export function getDmRoomList (state) {
  return state.dmRoomList
}

export function getDmHistory (state) {
  return state.dmHistory
}
