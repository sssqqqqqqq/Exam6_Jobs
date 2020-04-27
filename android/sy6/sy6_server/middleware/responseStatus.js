const api = require("../config/api");

module.exports = (req, res, next) => {
  res.success = (message, data) => res.json(api.success(message, data));
  res.fail = (message, data) => res.json(api.fail(message, data));
  res.error = (message, data) => res.json(api.error(message, data));
  res.data = (data) => res.json(api.data(data));
  
  next();
}