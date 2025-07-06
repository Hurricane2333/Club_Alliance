import request from '@/utils/request.js'

const userCache = new Map()

export async function getUserName(id) {
  if (userCache.has(id)) {
    return userCache.get(id)
  }

  try {
    const res = await request.get(`/user/${id}`)
    const name = res.data?.stuName ?? '未知用户'
    console.log('获取用户信息:', res.data?.stuName)
    userCache.set(id, name)
    return name
  } catch (error) {
    console.error('获取用户信息失败:', error)
    return '未知用户'
  }
}

export function clearUserCache() {
  userCache.clear()
}
