'use strict';
const express = require('express');
const router = express.Router();
const news = require("../controller/news");
router.get("/get",news.getAllNews);
module.exports = router;