
var mongoose = require('../db/connect');
var Schema = mongoose.Schema;

var news = new Schema({
  title: String,
  category: String,
  author_name: String,
  date:String,
  url:String,
  image:String,
 
}, { collection: 'news' });

module.exports = mongoose.model('news', news);