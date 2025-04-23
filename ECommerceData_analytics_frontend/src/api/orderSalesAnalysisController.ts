// @ts-ignore
/* eslint-disable */
import request from '@/request'

/** getAgeGroupConsumption GET /api/analysis/sales/age-consumption */
export async function getAgeGroupConsumptionUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseListMapStringObject_>('/api/analysis/sales/age-consumption', {
    method: 'GET',
    ...(options || {}),
  })
}

/** getHotCategoryRanking GET /api/analysis/sales/hot-category */
export async function getHotCategoryRankingUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseListMapStringObject_>('/api/analysis/sales/hot-category', {
    method: 'GET',
    ...(options || {}),
  })
}

/** getMemberComparison GET /api/analysis/sales/member-compare */
export async function getMemberComparisonUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseListMapStringObject_>('/api/analysis/sales/member-compare', {
    method: 'GET',
    ...(options || {}),
  })
}

/** getOrderTrend GET /api/analysis/sales/order-trend */
export async function getOrderTrendUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getOrderTrendUsingGETParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseListMapStringObject_>('/api/analysis/sales/order-trend', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  })
}

/** getRegionSales GET /api/analysis/sales/region-sales */
export async function getRegionSalesUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseListMapStringObject_>('/api/analysis/sales/region-sales', {
    method: 'GET',
    ...(options || {}),
  })
}
