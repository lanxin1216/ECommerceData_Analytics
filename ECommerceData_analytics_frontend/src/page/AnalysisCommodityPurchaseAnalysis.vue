<template>
  <div>
    <h1 class="page-title">商品购买行为分析</h1>
    <ARPUChart />
    <div style="margin-top: 20px" />
    <TopSellingProductsTable />
  </div>
</template>

<script setup lang="ts">
import TopSellingProductsTable from '@/components/ProductBehaviorAnalysis/TopSellingProductsTable.vue'
import ARPUChart from '@/components/ProductBehaviorAnalysis/ARPUChart.vue'
</script>

<style scoped>
.page-title {
  text-align: center;
  margin-bottom: 24px;
}
</style>
