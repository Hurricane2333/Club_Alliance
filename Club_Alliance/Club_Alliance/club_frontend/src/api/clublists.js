import request from '@/utils/request'

/**
 * 获取全部社团列表
 * @returns {Promise} 包含社团列表的Promise
 */
export function getAllClubs() {
  return request({
    url: '/api/clublists',
    method: 'get'
  }).then(response => {
    if (response.code === 'success') {
      return Array.isArray(response.data) ? response.data : []
    } else {
      return Promise.reject(new Error(response.msg || '获取社团列表失败'))
    }
  })
}
