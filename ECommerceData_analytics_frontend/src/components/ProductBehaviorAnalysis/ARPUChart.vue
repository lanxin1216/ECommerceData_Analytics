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
import { ref, onMounted } from 'vue'
import * as echarts from 'echarts'
// import { getARPUTrendUsingGet } from '@/api/productPurchaseAnalysisController'
import { mockARPUTrend } from '@/mock/productPurchaseMock.ts'

const chartRef = ref<HTMLDivElement | null>(null)
let chart: echarts.ECharts
const loading = ref(false)
const isEmpty = ref(false)

const fetchData = async () => {
  loading.value = true
  // const res = await getARPUTrendUsingGet()
  const res = { data: mockARPUTrend }
  if (res.data.xAxis?.length === 0) isEmpty.value = true
  else renderChart(res.data)
  loading.value = false
}

const renderChart = (data: any) => {
  chart.setOption({
    tooltip: { trigger: 'axis' },
    xAxis: { type: 'category', data: data.xAxis },
    yAxis: { type: 'value', name: '元' },
    series: data.series
  })
  isEmpty.value = false
}

onMounted(() => {
  if (chartRef.value) chart = echarts.init(chartRef.value)
  fetchData()
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