
const routes = [
  {
    path: '/',
    component: () => import('layouts/login.vue')
  },
  {
    path: '/signUp',
    component: () => import('layouts/SignUp.vue')
  },
  {
    path: '/main',
    component: () => import('layouts/main.vue')
  },
  {
    path: '/group',
    component: () => import('layouts/group.vue')
  },
  {
    path: '/board',
    component: () => import('layouts/board.vue')
  },
  {
    path: '/management',
    component: () => import('layouts/management.vue')
  },
  {
    path: '/mypage',
    component: () => import('layouts/MyPage.vue')
  },
  {
    path: '/write',
    component: () => import('layouts/write.vue')
  }
  // Always leave this as last one,
  // but you can also remove it
]
export default routes
