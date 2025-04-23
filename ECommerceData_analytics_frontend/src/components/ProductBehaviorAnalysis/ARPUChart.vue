<template>
  <a-card title="平均客单价分析 (ARPU)">
    <div class="card-content">
      <a-spin :spinning="loading">
        <a-empty v-if="isEmpty && !loading" description="暂无数据" />
        <div ref="chartRef" v-show="!isEmpty && !loading" class="chart" />
      </a-spin>
    </div>
  </a-card>
</template>

<script setup lang="ts">
import { ref, onMounted, nextTick } from 'vue'
import * as echarts from 'echarts'
import { message } from 'ant-design-vue'
import { getArpuTrendUsingGet } from '@/api/productPurchaseAnalysisController.ts'

const chartRef = ref<HTMLDivElement | null>(null)
const loading = ref(false)
const isEmpty = ref(false)
let chartInstance: echarts.ECharts | null = null

const renderChart = (data: any) => {
  if (!chartRef.value) return
  if (chartInstance) chartInstance.dispose()
  chartInstance = echarts.init(chartRef.value)

  chartInstance.setOption({
    tooltip: {
      trigger: 'axis',
      formatter: (params: any) => {
        const item = params[0]
        return `${item.axisValue}<br/>${item.seriesName}: ¥${item.data}`
      }
    },
    xAxis: {
      type: 'category',
      data: data.xAxis,
      axisLabel: { rotate: 30 }
    },
    yAxis: {
      type: 'value',
      name: '元'
    },
    series: data.series
  })

  setTimeout(() => chartInstance?.resize(), 100)
}

const fetchData = async () => {
  loading.value = true
  try {
    const res = await getArpuTrendUsingGet()
    if (res.data.code === 0 && res.data.data?.xAxis?.length > 0) {
      isEmpty.value = false
      await nextTick()
      renderChart(res.data.data)
    } else {
      isEmpty.value = true
      message.warning('暂无 ARPU 数据')
    }
  } catch (err) {
    isEmpty.value = true
    message.error('加载失败，请稍后重试')
    console.error('ARPU 数据加载失败:', err)
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
