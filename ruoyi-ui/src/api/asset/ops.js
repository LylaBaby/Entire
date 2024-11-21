import request from '@/utils/request'

// 查询系统运维列表
export function listOps(query) {
  return request({
    url: '/asset/ops/list',
    method: 'get',
    params: query
  })
}

// 查询系统运维详细
export function getOps(appItems) {
  return request({
    url: '/asset/ops/' + appItems,
    method: 'get'
  })
}

// 新增系统运维
export function addOps(data) {
  return request({
    url: '/asset/ops',
    method: 'post',
    data: data
  })
}

// 修改系统运维
export function updateOps(data) {
  return request({
    url: '/asset/ops',
    method: 'put',
    data: data
  })
}

// 删除系统运维
export function delOps(appItems) {
  return request({
    url: '/asset/ops/' + appItems,
    method: 'delete'
  })
}
