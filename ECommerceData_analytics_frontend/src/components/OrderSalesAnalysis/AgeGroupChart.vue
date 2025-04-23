<template>
  <a-card title="用户年龄段消费分析">
    <div class="card-content">
      <a-spin :spinning="loading">
        <a-empty v-if="isEmpty && !loading" description="暂无数据" />
        <div ref="chartRef" v-show="!isEmpty && !loading" class="chart" />
      </a-spin>
    </div>
  </a-card>
</template>

<script setup lang="ts">
import { onMounted, ref, nextTick } from 'vue'
import * as echarts from 'echarts'
import { message } from 'ant-design-vue'
import { getAgeGroupConsumptionUsingGet } from '@/api/orderSalesAnalysisController.ts'

const chartRef = ref<HTMLDivElement | null>(null)
const loading = ref(false)
const isEmpty = ref(false)
let chartInstance: echarts.ECharts | null = null

const renderChart = (data: any) => {
  if (!chartRef.value) return

  if (chartInstance) chartInstance.dispose()
  chartInstance = echarts.init(chartRef.value)

  chartInstance.setOption({
    tooltip: { trigger: 'axis' },
    xAxis: { type: 'category', data: data.xAxis },
    yAxis: { type: 'value' },
    series: data.series
  })

  setTimeout(() => {
    chartInstance?.resize()
  }, 100)
}

const fetchData = async () => {
  loading.value = true
  try {
    const res = await getAgeGroupConsumptionUsingGet()
    const data = res.data.data?.[0]
    if (res.data.code === 0 && data && Array.isArray(data.xAxis) && data.xAxis.length > 0) {
      isEmpty.value = false
      await nextTick()
      renderChart(data)
    } else {
      isEmpty.value = true
      message.warning('暂无年龄段消费数据')
    }
  } catch (err) {
    isEmpty.value = true
    message.error('请求失败')
    console.error('年龄段消费分析加载失败', err)
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchData()
  window.addEventListener('resize', () => chartInstance?.resize())
})
</script>

<style scoped>
.card-content {
  min-height: 300px;
}

.chart {
  height: 400px;
  width: 100%;
}
</style>
