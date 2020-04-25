const dbhelper = require("../db/dbhelper/curd");
const db = require("../db/index")
module.exports = {
    async getAllNews(req,res){
        let data = {
            title: String,
            category: String,
            author_name: String,
            date:String,
            url:String,
            image:String,
        }

        let result = await dbhelper.find(db.news);
        res.data(result);
    }
}