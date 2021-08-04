
const routes = [
  {
    path: '/',
    component: () => import('pages/Login.vue')
  },
  {
    path: '/signup',
    component: () => import('pages/SignUp.vue')
  },
  {
    path: '/main',
    component: () => import('layouts/main/MainLayout.vue'),
    redirect: '/mainpage',
    children: [
      // 메인 대시보드
      {
        path: '/mainpage',
        component: () => import('pages/Main.vue')
      },
      // 게시판라우터
      {
        path: '/boardlayout',
        component: () => import('layouts/board/BoardLayout.vue'),
        redirect: '/board',
        children: [
          {
            path: '/board',
            component: () => import('pages/board/Board.vue')
          },
          {
            path: '/boardMark',
            component: () => import('pages/board/BoardMark.vue')
          },
          {
            path: '/boardType',
            component: () => import('pages/board/BoardType.vue')
          },
          {
            path: '/boardDetail',
            component: () => import('pages/board/BoardDetail.vue')
          },
          {
            path: '/write',
            component: () => import('pages/board/BoardWrite.vue')
          }
        ]
      },
      // 그룹페이지
      {
        path: '/group',
        component: () => import('pages/group/Group.vue')
      },
      // 마이페이지 라우터
      {
        path: '/mylayout',
        component: () => import('layouts/user/UserLayout.vue'),
        redirect: '/mypage',
        children: [
          {
            path: '/mypage',
            component: () => import('pages/user/MyPage.vue')
          },
          {
            path: '/myedit',
            component: () => import('pages/user/MypageEdit.vue')
          },
          {
            path: '/management',
            component: () => import('pages/user/MyManagement.vue')
          }
        ]
      }
    ]
  },
  {
    path: '/rtc',
    component: () => import('layouts/rtcTest.vue')
  },
  {
    path: '/attendance',
    component: () => import('layouts/attendance.vue')
  },
  {
    path: '/chat',
    component: () => import('layouts/chat.vue')
  }
  // Always leave this as last one,
  // but you can also remove it
]
export default routes
