// @ts-ignore
/* eslint-disable */
import request from '@/request'

/** getAgeStructure GET /api/analysis/user/age-structure */
export async function getAgeStructureUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseListMapStringObject_>('/api/analysis/user/age-structure', {
    method: 'GET',
    ...(options || {}),
  })
}

/** getGenderDistribution GET /api/analysis/user/gender-distribution */
export async function getGenderDistributionUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseListMapStringObject_>('/api/analysis/user/gender-distribution', {
    method: 'GET',
    ...(options || {}),
  })
}

/** getMembershipRatio GET /api/analysis/user/membership-ratio */
export async function getMembershipRatioUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseListMapStringObject_>('/api/analysis/user/membership-ratio', {
    method: 'GET',
    ...(options || {}),
  })
}

/** getProvinceDistribution GET /api/analysis/user/province-distribution */
export async function getProvinceDistributionUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseListMapStringObject_>('/api/analysis/user/province-distribution', {
    method: 'GET',
    ...(options || {}),
  })
}
