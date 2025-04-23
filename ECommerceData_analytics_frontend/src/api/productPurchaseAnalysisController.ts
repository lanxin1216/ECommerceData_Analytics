// @ts-ignore
/* eslint-disable */
import request from '@/request'

/** getARPUTrend GET /analysis/purchase/arpu */
export async function getArpuTrendUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseMapStringObject_>('/analysis/purchase/arpu', {
    method: 'GET',
    ...(options || {}),
  })
}

/** getTopSellingProducts GET /analysis/purchase/top-products */
export async function getTopSellingProductsUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseMapStringObject_>('/analysis/purchase/top-products', {
    method: 'GET',
    ...(options || {}),
  })
}
