import request from '@/utils/request'

export function getAllActivities() {
  return request({
    url: '/api/activitylists',
    method: 'get'
  }).then(response => {
    if (response.code === 'success') {
      return Array.isArray(response.data) ? response.data : []
    } else {
      return Promise.reject(new Error(response.msg || '获取活动列表失败'))
    }
  })
}
