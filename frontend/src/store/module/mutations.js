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

export function setMarkList (state, markList) {
  state.markList = markList
}

export function setMark (state, mark) {
  state.mark = mark
}

export function setDmRoomList (state, dmRoomList) {
  state.dmRoomList = dmRoomList
}

export function setDmHistory (state, dmHistory) {
  state.dmHistory = dmHistory
}

export function setMode1 (state, mode1) {
  state.mode1 = mode1
}

export function setMode2 (state, mode2) {
  state.mode2 = mode2
}

export function setMode3 (state, mode3) {
  state.mode3 = mode3
}

export function setMode4 (state, mode4) {
  state.mode4 = mode4
}

export function setMode5 (state, mode5) {
  state.mode5 = mode5
}

export function setMode6 (state, mode6) {
  state.mode6 = mode6
}

// export function setModePx1 (state, modePx1) {
//   state.modePx1 = modePx1
// }

// export function setModePx2 (state, modePx2) {
//   state.modePx2 = modePx2
// }

// export function setModePx3 (state, modePx3) {
//   state.modePx3 = modePx3
// }

// export function setModePx4 (state, modePx4) {
//   state.modePx4 = modePx4
// }

// export function setModePx5 (state, modePx5) {
//   state.modePx5 = modePx5
// }

// export function setModePx6 (state, modePx6) {
//   state.modePx6 = modePx6
// }

export function setBoardType (state, boardType) {
  state.boardType = boardType
}

export function setDownMode (state, downMode) {
  state.downMode = downMode
}

export function setRtcFileList (state, rtcFileList) {
  state.rtcFileList = rtcFileList
}

export function setDayLog (state, dayLog) {
  state.dayLog = dayLog
}

export function setLinkList (state, linkList) {
  state.linkList = linkList
}

export function addLinkList (state, link) {
  state.linkList.push(link)
}
