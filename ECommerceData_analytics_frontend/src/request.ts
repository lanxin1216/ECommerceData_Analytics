import axios from 'axios'
import { message } from 'ant-design-vue'

// 创建 Axios 实例
const myAxios = axios.create({
    baseURL: 'http://localhost:8080',
    // timeout: 60000,
    withCredentials: true,
})

// 全局请求拦截器
myAxios.interceptors.request.use(
    function (config) {
        // Do something before request is sent
        return config
    },
    function (error) {
        // Do something with request error
        return Promise.reject(error)
    },
)

// 全局响应拦截器
myAxios.interceptors.response.use(
    function (response) {
        const { data } = response
        return response
    },
    function (error) {
        // Any status codes that falls outside the range of 2xx cause this function to trigger
        // Do something with response error
        return Promise.reject(error)
    },
)

export default myAxios
