import request from '@/utils/request';

export function getSummaryData() {
  return request({
    url: '/asset/statistics/summary',
    method: 'get'
  });
}