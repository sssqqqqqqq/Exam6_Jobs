const dbhelper = require("../db/dbhelper/curd");
const db = require("../db/index")
const util = require("../util")
const test = async()=>{
    await dbhelper.create(db.news,{"title": "全球新冠肺炎COVID-19实时追踪，关注全球疫情动态",
    "date": "2020-04-19 22:23:02",
    "category": "头条",
    "author_name": "健康资讯",
    "url": "",
    "image": "static/2.png"});

     await dbhelper.create(db.news,{"title": "意外还是必然 34岁的乔丹三连冠后为何退役？他本可以延续传奇",
    "date": "2020-04-19 21:31",
    "category": "头条",
    "author_name": "篮球老学姐",
    "url": "",
    "image": "static/3.png"});

     await dbhelper.create(db.news,{"title": "欧洲防疫“最严格”的国家拐点出现了吗？那里70万华侨华人怎么样了",
     "date": "2020-04-19 21:28",
     "category": "头条",
     "author_name": "人民威评",
    "url": "",
    "image": "static/4.png"});

    await dbhelper.create(db.news,{"title": "英国新增新冠肺炎感染者5850例，累计超12万",
    "date": "2020-04-19 21:23",
    "category": "头条",
    "author_name": "人民日报",
   "url": "",
   "image": "static/5.png"});

   
   await dbhelper.create(db.news,{"title": "国足这么多年，都是05年那批国青队球员，在撑着门面",
   "date": "2020-04-19 20:58",
   "category": "头条",
   "author_name": "龙之队球迷",
  "url": "",
  "image": "static/6.png"});

     
  await dbhelper.create(db.news,{"title": "高新消防救援大队深入复工企业开展消防安全培训",
  "date": "2020-04-19 20:55",
  "category": "头条",
  "author_name": "广西新闻网",
 "url": "",
 "image": "static/7.png"});

 await dbhelper.create(db.news,{"title": "中方捐赠1250万只口罩！该国驻华大使：不允许有伤害中国人民言论",
 "date": "2020-04-19 20:51",
 "category": "头条",
 "author_name": "中外观察",
"url": "",
"image": "static/8.png"});

await dbhelper.create(db.news,{	"title": "手机芯片排行大洗牌：骁龙865力压麒麟990，980竟输给了765G？",
"date": "2020-04-19 20:32",
"category": "头条",
"author_name": "天极网",
"url": "",
"image": "static/9.png"});
    
    
}
test();

