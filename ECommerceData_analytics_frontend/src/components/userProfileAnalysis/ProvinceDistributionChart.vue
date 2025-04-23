<template>
  <a-card title="用户地域分布（省份）">
    <a-spin :spinning="loading" tip="图表加载中...">
      <a-empty v-if="isEmpty && !loading" description="暂无数据" />
      <div v-show="!isEmpty && !loading" ref="chartRef" class="chart" />
    </a-spin>
  </a-card>
</template>

<script setup lang="ts">
import { ref, onMounted, nextTick } from 'vue'
import * as echarts from 'echarts'
import { message } from 'ant-design-vue'
import { getProvinceDistributionUsingGet } from '@/api/userBehaviorFeatureAnalysisController.ts'

const chartRef = ref<HTMLDivElement | null>(null)
const loading = ref(false)
const isEmpty = ref(false)
let chartInstance: echarts.ECharts | null = null

const renderChart = (data: any[]) => {
  if (!chartRef.value) return

  // 防止重复初始化
  if (chartInstance) {
    chartInstance.dispose()
  }

  chartInstance = echarts.init(chartRef.value)
  chartInstance.setOption({
    tooltip: { trigger: 'axis' },
    xAxis: {
      type: 'category',
      data: data.map(item => item.name),
      axisLabel: { rotate: 40 } // 避免重叠
    },
    yAxis: { type: 'value' },
    series: [
      {
        type: 'bar',
        data: data.map(item => item.value),
        itemStyle: { color: '#fa541c' }
      }
    ]
  })

  setTimeout(() => {
    chartInstance?.resize()
  }, 0)
}

const fetchData = async () => {
  loading.value = true
  try {
    const res = await getProvinceDistributionUsingGet()
    if (res.data.code === 0 && Array.isArray(res.data.data)) {
      isEmpty.value = res.data.data.length === 0
      if (!isEmpty.value) {
        await nextTick()
        renderChart(res.data.data)
      }
    } else {
      isEmpty.value = true
      console.error('返回格式错误或 code 非 0', res)
      message.error(res.data.message || '请求失败或数据格式错误')
    }
  } catch (error) {
    isEmpty.value = true
    message.error('请求异常')
    console.error('请求异常:', error)
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchData()
  window.addEventListener('resize', () => {
    chartInstance?.resize()
  })
})
</script>

<style scoped>
.chart {
  height: 420px;
  width: 100%;
  min-height: 300px;
}
</style>
