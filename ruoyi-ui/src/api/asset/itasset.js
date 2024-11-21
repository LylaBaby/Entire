import request from '@/utils/request'

// 查询IT资产列表
export function listItasset(query) {
  return request({
    url: '/asset/itasset/list',
    method: 'get',
    params: query
  })
}

// 查询IT资产详细
export function getItasset(assetId) {
  return request({
    url: '/asset/itasset/' + assetId,
    method: 'get'
  })
}

// 新增IT资产
export function addItasset(data) {
  return request({
    url: '/asset/itasset',
    method: 'post',
    data: data
  })
}

// 修改IT资产
export function updateItasset(data) {
  return request({
    url: '/asset/itasset',
    method: 'put',
    data: data
  })
}

// 删除IT资产
export function delItasset(assetId) {
  return request({
    url: '/asset/itasset/' + assetId,
    method: 'delete'
  })
}
