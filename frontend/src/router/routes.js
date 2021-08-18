
// const Swal = require('sweetalert2')
// const userstate = localStorage.getItem('userState')

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
    // beforeEnter: (to, from, next) => {
    //   if (userstate === '3') {
    //     next('/')
    //     Swal.fire({
    //       title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">승인 대기 상태입니다.</span>',
    //       confirmButtonColor: '#ce1919',
    //       confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
    //     })
    //   } else {
    //     next()
    //     Swal.fire({
    //       title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">환영합니다.</span>',
    //       confirmButtonColor: '#19CE60',
    //       confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
    //     })
    //   }
    // },
    children: [
      // 메인 대시보드
      {
        path: '/mainpage',
        component: () => import('pages/Main2.vue')
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
            path: '/boardMe',
            component: () => import('pages/board/BoardMe.vue')
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
        component: () => import('pages/group/GroupList.vue')
        // beforeEnter: (to, from, next) => {
        //   if (userstate !== '1') {
        //     next('/main')
        //     Swal.fire({
        //       title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">접근 권한이 없습니다.</span>',
        //       confirmButtonColor: '#ce1919',
        //       confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
        //     })
        //   } else if (userstate === '1') {
        //     next()
        //   }
        // }
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
            path: '/management',
            component: () => import('pages/user/MyManagement.vue')
          }
        ]
      },
      // 화상회의
      {
        path: '/rtc',
        component: () => import('layouts/rtcTest.vue')
      },
      {
        name: 'Downloads',
        path: '/downloads',
        component: () => import('pages/downloads/Downloads.vue')
      },
      {
        name: 'Downloads2',
        path: '/downloads2',
        component: () => import('pages/downloads/Downloads2.vue')
      }
    ]
  },
  {
    path: '/chat',
    component: () => import('layouts/chat.vue')
  }
//   Always leave this as last one,
//   but you can also remove it
]
export default routes
