<template>
  <a-card title="用户性别分布">
    <a-spin :spinning="loading" tip="加载中...">
      <a-empty v-if="isEmpty && !loading" description="暂无数据" />
      <div v-show="!isEmpty && !loading" ref="chartRef" class="chart" />
    </a-spin>
  </a-card>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import * as echarts from 'echarts'
import { getGenderDistributionUsingGet } from '@/api/userBehaviorFeatureAnalysisController.ts'
import { message } from 'ant-design-vue'

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
    tooltip: { trigger: 'item' },
    series: [
      {
        type: 'pie',
        radius: '60%',
        data: data,
        label: { formatter: '{b}: {d}%' }
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
    const res = await getGenderDistributionUsingGet()
    if (res.data.code === 0 && Array.isArray(res.data.data)) {
      isEmpty.value = res.data.data.length === 0
      if (!isEmpty.value) {
        renderChart(res.data.data)
      }
    } else {
      isEmpty.value = true
      console.error('请求失败或返回数据格式不正确', res)
      message.error(res.data.message || '请求失败或返回数据格式不正确')
    }
  } catch (error) {
    isEmpty.value = true
    message.error('请求异常')
    console.error('请求异常', error)
  } finally {
    loading.value = false
  }
}

onMounted(fetchData)
</script>

<style scoped>
.chart {
  height: 400px;
  width: 100%;
}
</style>
