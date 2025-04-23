<template>
  <div id="userProfileAnalysis">
    <h1 class="page-title">用户行为与特征分析</h1>
    <a-row :gutter="16">
      <a-col :span="12">
        <GenderDistributionChart />
      </a-col>
      <a-col :span="12">
        <AgeStructureChart />
      </a-col>
    </a-row>
    <div style="margin-top: 10px" />
    <a-row :gutter="16">
      <a-col :span="12">
        <MemberRatioChart />
      </a-col>
      <a-col :span="12">
        <ProvinceDistributionChart />
      </a-col>
    </a-row>
  </div>
</template>

<script setup lang="ts">

import GenderDistributionChart from '@/components/userProfileAnalysis/GenderDistributionChart.vue'
import AgeStructureChart from '@/components/userProfileAnalysis/AgeStructureChart.vue'
import MemberRatioChart from '@/components/userProfileAnalysis/MemberRatioChart.vue'
import ProvinceDistributionChart from '@/components/userProfileAnalysis/ProvinceDistributionChart.vue'
</script>

<style scoped>
.page-title {
  text-align: center;
  margin-bottom: 24px;
}

</style>