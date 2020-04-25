const express = require('express');
const config = require("./config");

const cors = require("cors");

const responseStatus = require("./middleware/responseStatus");
const route = require('./routes');



const app = express();
app.use('/static',express.static('static'));
const http = require('http').Server(app);
if (process.env.NODE_ENV === "development") {
  // 开发模式下 允许跨域访问
  app.use(cors());
}

app
  .use(express.json()) 
  .use(express.urlencoded()) 
  .use(responseStatus) // 返回值类型中间件 前后端统一 详见config-api

route(app); // 路由


http.listen(config.server.port, () => {
  console.log("server listen on", config.server.port);
});
console.log("ok");

module.exports = {
  app,
  http,
}