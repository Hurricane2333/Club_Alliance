import request from '@/utils/request'

/**
 * 获取首页推荐社团列表
 * @returns {Promise} 包含社团列表的Promise
 */
export function getHomeClubs() {
  return request({
    url: '/api/clubs',
    method: 'get',
    /* params: {
      pageSize: 10
    } */
  }).then(response => {
    if (response.code === 'success') {
      return Array.isArray(response.data) ? response.data : [];
      //return response.data
    } else {
      return Promise.reject(new Error(response.msg || '获取社团列表失败'))
    }
  })
}

export function getRecentActivities(limit = 3) {
  return request({
    url: '/api/activities',
    method: 'get',
    params: { limit }
  }).then(response => {
    if (response.code === 'success') {
      return Array.isArray(response.data) ? response.data : [];
    } else {
      return Promise.reject(new Error(response.msg || '获取活动列表失败'))
    }
  })
}


/**
 * 获取社团详情
 * @param {number} clubId 社团ID
 * @returns {Promise} 包含社团详情的Promise
 */
/* export function getClubDetail(clubId) {
  return request({
    url: `/club/${clubId}`,
    method: 'get'
  })
}
 */
/**
 * 收藏社团
 * @param {number} clubId 社团ID
 * @returns {Promise} 操作结果的Promise
 */
/* export function favoriteClub(clubId) {
  return request({
    url: `/club/${clubId}/favorite`,
    method: 'post'
  })
} */
