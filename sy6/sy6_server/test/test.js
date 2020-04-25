const dbhelper = require("../db/dbhelper/curd");
const db = require("../db/index")
const util = require("../util")
const test = async()=>{
    let k = await dbhelper.find(db.news);
    console.log(k);
    console.log(k._id);
    
}
test();

