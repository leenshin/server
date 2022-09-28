const { defineConfig } = require('@vue/cli-service')
const path = require('path');
module.exports = defineConfig({
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
})
