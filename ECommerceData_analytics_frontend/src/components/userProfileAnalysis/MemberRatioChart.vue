<template>
  <a-card title="会员用户占比图">
    <a-spin :spinning="loading" tip="加载中...">
      <a-empty v-if="isEmpty && !loading" description="暂无数据" />
      <div v-show="!isEmpty && !loading" ref="chartRef" class="chart" />
    </a-spin>
  </a-card>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import * as echarts from 'echarts'
// import { getGenderDistribution } from '@/api/userBehaviorFeatureController'
import { mockMemberData } from '@/mock/userBehaviorFeatureMock'

const chartRef = ref<HTMLDivElement | null>(null)
const loading = ref(false)
const isEmpty = ref(false)

const renderChart = (data: any[]) => {
  const chart = echarts.init(chartRef.value!)
  chart.setOption({
    tooltip: { trigger: 'item' },
    series: [
      {
        type: 'pie',
        radius: '60%',
        data: data.map(item => ({ name: item.type, value: item.count })),
        label: { formatter: '{b}: {d}%' }
      }
    ]
  })
}

const fetchData = async () => {
  loading.value = true
  // const res = await getGenderDistribution()
  // const data = res.data
  const data = mockMemberData
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
