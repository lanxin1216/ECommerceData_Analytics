<template>
  <a-card title="订单趋势分析（日/月）">
    <div class="card-content">
      <!-- 趋势类型切换 -->
      <div class="type-switch">
        <a-radio-group v-model:value="trendType" @change="fetchData">
          <a-radio-button value="day">按天</a-radio-button>
          <a-radio-button value="month">按月</a-radio-button>
        </a-radio-group>
      </div>

      <!-- 图表加载与展示 -->
      <a-spin :spinning="loading">
        <a-empty v-if="isEmpty && !loading" description="暂无数据" />
        <div ref="chartRef" v-show="!isEmpty && !loading" class="chart" />
      </a-spin>
    </div>
  </a-card>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import * as echarts from 'echarts'
// import { getOrderTrendUsingGet } from '@/api/sales'
import { mockOrderTrend } from '@/mock/salesAnalysisMock.ts'

const chartRef = ref<HTMLDivElement | null>(null)
let chart: echarts.ECharts
const loading = ref(false)
const isEmpty = ref(false)

// 趋势类型：'day' | 'month'
const trendType = ref<'day' | 'month'>('day')

const fetchData = async () => {
  loading.value = true

  // 请求后端接口（用 mock 替代）
  // const res = await getOrderTrendUsingGet({ type: trendType.value })
  const res = mockOrderTrend

  if (res.data.xAxis?.length === 0) isEmpty.value = true
  else renderChart(res.data)

  loading.value = false
}

const renderChart = (data: any) => {
  chart.setOption({
    tooltip: { trigger: 'axis' },
    xAxis: { type: 'category', data: data.xAxis },
    yAxis: { type: 'value' },
    series: data.series,
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

.type-switch {
  margin-bottom: 16px;
  text-align: right;
}
</style>
