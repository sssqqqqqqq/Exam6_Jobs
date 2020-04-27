const dbhelper = require("../db/dbhelper/curd");
const db = require("../db/index")
const util = require("../util/index")
const querystring = require('querystring')
module.exports = {
    async login(req, res) {
        let conditon = req.query;
        console.log("conditon:",req.method);
        conditon.password = util.aesTranslate(conditon.password);
        console.log("conditon:",conditon);
        let result = await dbhelper.find(db.user,conditon);
       
        
        if (result.length == 0) {
            console.log("no");
            res.data({ "message": "fail", "type": 1 });
        } else {
            res.data({ "message": "success", "type": 0 });
        }

    },

    async register(req, res) {
        let conditon = req.query;
        console.log(req.query);
        console.log(req.url);
        console.log("conditon:",req.method);
        if (!util.isEmail(conditon.email)) {
            res.data({ "message": "请输入正确的邮箱。", "type": 1 })
            return;
        }
        let namevertify = await dbhelper.find(db.user, { "name": conditon.name });

        if (namevertify.length != 0) {

            res.data({ "message": "此用户名已注册。", "type": 2 })
            return;
        }

        let emailvertify = await dbhelper.find(db.user, { "email": conditon.email });
        if (emailvertify.length != 0) {
            res.data({ "message": "此邮箱已注册。", "type": 3 })
            return;
        }
        conditon.password = util.aesTranslate(conditon.password);
        await dbhelper.create(db.user, conditon);
        res.data({ "message": "已注册成功。","type":0 })
    }
}