const dbhelper = require("../db/dbhelper/curd");
const db = require("../db/index")
module.exports = {
    async getAllNews(req,res){
        

        let result = await dbhelper.find(db.news);
        res.data(result);
    },
    async getFuzzNews(req,res){
        let regx = req.query.regx;
        let result = await dbhelper.fuzzyFind(db.news,regx);
        res.data(result);
    }
}