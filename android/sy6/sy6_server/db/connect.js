const mongoose = require('mongoose');
const config = require("../config");

mongoose.connect(
    config.db.uri,
    {
        useNewUrlParser: true,
        useUnifiedTopology: true
    }
    
).catch((err) => console.log(err));

mongoose.connection.on('open', () => {
    console.log('打开 mongodb 连接');
})

mongoose.connection.on('err', (err) => {
    console.log('err:' + err);
  })

mongoose.connection.on('disconnected',() =>{
    console.log('断开 mongodb 连接')
})

module.exports = mongoose;