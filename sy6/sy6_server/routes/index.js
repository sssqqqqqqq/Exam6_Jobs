'use strict';
const user = require("./user");
const news = require("./news");
module.exports = (app) => {
    app.use("/user",user);
    app.use("/news", news);
 
  
}