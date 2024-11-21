import request from '@/utils/request'

// 查询应用资产列表
export function listAppasset(query) {
  return request({
    url: '/asset/appasset/list',
    method: 'get',
    params: query
  })
}

// 查询应用资产详细
export function getAppasset(appId) {
  return request({
    url: '/asset/appasset/' + appId,
    method: 'get'
  })
}

// 新增应用资产
export function addAppasset(data) {
  return request({
    url: '/asset/appasset',
    method: 'post',
    data: data
  })
}

// 修改应用资产
export function updateAppasset(data) {
  return request({
    url: '/asset/appasset',
    method: 'put',
    data: data
  })
}

// 删除应用资产
export function delAppasset(appId) {
  return request({
    url: '/asset/appasset/' + appId,
    method: 'delete'
  })
}
