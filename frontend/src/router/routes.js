
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
    path: '/mainpage',
    component: () => import('layouts/MainPage.vue')
  },
  {
    path: '/notice',
    component: () => import('layouts/notice.vue')
  },
  {
    path: '/board',
    component: () => import('layouts/board.vue')
  }
  // Always leave this as last one,
  // but you can also remove it
]
export default routes
