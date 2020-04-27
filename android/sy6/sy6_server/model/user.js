
var mongoose = require('../db/connect');
var Schema = mongoose.Schema;

var user = new Schema({
  name: String,
  email: String,
  password: String
 
}, { collection: 'user' });

module.exports = mongoose.model('user', user);