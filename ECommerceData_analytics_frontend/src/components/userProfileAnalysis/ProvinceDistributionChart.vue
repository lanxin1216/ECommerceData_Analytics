<template>
  <a-card title="用户地域分布（省份）">
    <a-spin :spinning="loading" tip="图表加载中...">
      <a-empty v-if="isEmpty && !loading" description="暂无数据" />
      <div v-show="!isEmpty && !loading" ref="chartRef" class="chart" />
    </a-spin>
  </a-card>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import * as echarts from 'echarts'
import { mockProvinceData } from '@/mock/userBehaviorFeatureMock'

const chartRef = ref<HTMLDivElement | null>(null)
const loading = ref(false)
const isEmpty = ref(false)

const renderChart = (data: any[]) => {
  const chart = echarts.init(chartRef.value!)
  chart.setOption({
    tooltip: { trigger: 'axis' },
    xAxis: { type: 'category', data: data.map(item => item.province) },
    yAxis: { type: 'value' },
    series: [
      {
        type: 'bar',
        data: data.map(item => item.count),
        itemStyle: { color: '#fa541c' }
      }
    ]
  })
}

const fetchData = async () => {
  loading.value = true
  const data = mockProvinceData
  isEmpty.value = data.length === 0
  if (!isEmpty.value) renderChart(data)
  loading.value = false
}

onMounted(fetchData)
</script>

<style scoped>
.chart {
  height: 400px;
  width: 100%;
}
</style>
