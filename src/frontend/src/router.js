import { createWebHistory, createRouter } from "vue-router";
import Home from "./components/Home.vue";
import Login from "./components/Login.vue";
// lazy-loaded
const Profile = () => import("./components/Profile.vue")
const BoardAdmin = () => import("./components/BoardAdmin.vue")
const BoardUser = () => import("./components/BoardUser.vue")

const routes = [
    {
        path: "/",
        name: "home",
        component: Home,
    },
    {
        path: "/home",
        component: Home,
    },
    {
        path: "/login",
        component: Login,
    },
    {
        path: "/profile",
        name: "profile",
        // lazy-loaded
        component: Profile,
    },
    {
        path: "/admin",
        name: "admin",
        // lazy-loaded
        component: BoardAdmin,
    },
    {
        path: "/user",
        name: "user",
        // lazy-loaded
        component: BoardUser,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

/** 
 * Unauthorized 접근 통제 
 * action 수행 마다 권한 검사
 */ 
// router.beforeEach((to, from, next) => {
//   const publicPages = ['/login', '/register', '/home'];
//   const authRequired = !publicPages.includes(to.path);
//   const loggedIn = localStorage.getItem('user');
//
//   // 권한 없음 & 로그인 안됨
//   // Login 페이지로 redirect
//   if (authRequired && !loggedIn) {
//     next('/login');
//   } else {
//     next();
//   }
// });

export default router;