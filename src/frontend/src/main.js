import { createApp } from "vue";
import App from "./App.vue";  
import router from "./router";  // Vue Router(src/router.js)
import store from "./store";    // Vuex(src/store/)
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

createApp(App)
    .use(router)
    .use(store)
    .mount("#app");