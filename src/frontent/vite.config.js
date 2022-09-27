// import { fileURLToPath, URL } from 'node:url'
//
// import { defineConfig } from 'vite'
// import vue from '@vitejs/plugin-vue'
//
// // https://vitejs.dev/config/
// export default defineConfig({
//   plugins: [vue()],
//   resolve: {
//     alias: {
//       '@': fileURLToPath(new URL('./src', import.meta.url))
//     }
//   }
// })

// const { defineConfig } = require("@vue/cli-service");
const path = require("path");
module.exports = {
  transpileDependencies: true,

  devServer: {
    proxy: {
      "/": {
        target: "http://localhost:9000", // server port
        changeOrigin: true,
        ws: true,
      }
    },
    port: 8080, // client port
  },
  outputDir: path.resolve("__dirname", "../../main/resources/static"),
};
