// @ts-ignore
/* eslint-disable */
import request from '@/request'

/** getOrderSample GET /api/dataset/orders */
export async function getOrderSampleUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseListMapStringObject_>('/api/dataset/orders', {
    method: 'GET',
    ...(options || {}),
  })
}

/** getUsers GET /api/dataset/users */
export async function getUsersUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUsersUsingGETParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseListMapStringObject_>('/api/dataset/users', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  })
}
