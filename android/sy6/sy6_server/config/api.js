// api响应规范

const api = (type, message, value) => {
  return {
    type,
    data: {
      message, // 提示信息
      value, // 附带数据
    }
  }
}

module.exports = {
  // 请求成功
  success: (message, value) => api(200, message, value),

  // 请求被拒绝
  fail: (message, value) => api(300, message, value),

  // 请求执行出错
  error: (message, value) => api(400, message, value),

  // 纯数据
  data: (value) => {
    return {
      type: 500,
      data: {
        value, // 附带数据
      }
    }
  },
}