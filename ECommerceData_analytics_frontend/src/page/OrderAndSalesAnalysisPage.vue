<template>
  <div>
    <h1 class="page-title">订单与销售分析</h1>
    <a-row :gutter="16">
      <a-col :span="12">
        <OrderTrendChart />
      </a-col>
      <a-col :span="12">
        <HotCategoryChart />
      </a-col>
    </a-row>
    <div style="margin-top: 10px" />
    <a-row :gutter="16">
      <a-col :span="12">
        <MemberCompareChart />
      </a-col>
      <a-col :span="12">
        <AgeGroupChart />
      </a-col>
    </a-row>
    <div style="margin-top: 10px" />
    <a-row :gutter="24">
      <a-col :span="24">
        <RegionSalesChart />
      </a-col>
    </a-row>
  </div>
</template>

<script setup lang="ts">
import OrderTrendChart from '@/components/OrderSalesAnalysis/OrderTrendChart.vue'
import HotCategoryChart from '@/components/OrderSalesAnalysis/HotCategoryChart.vue'
import RegionSalesChart from '@/components/OrderSalesAnalysis/RegionSalesChart.vue'
import AgeGroupChart from '@/components/OrderSalesAnalysis/AgeGroupChart.vue'
import MemberCompareChart from '@/components/OrderSalesAnalysis/MemberCompareChart.vue'
</script>

<style>
.page-title {
  text-align: center;
  margin-bottom: 24px;
}
</style>
