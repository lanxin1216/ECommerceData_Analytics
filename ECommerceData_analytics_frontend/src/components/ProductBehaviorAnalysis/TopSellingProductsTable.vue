<template>
  <a-card title="单品销量排行">
    <a-table :dataSource="dataSource" :columns="columns" :loading="loading" rowKey="productName" />
  </a-card>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
// import { getTopSellingProductsUsingGet } from '@/api/productPurchaseAnalysisController'
import { mockTopSellingProducts } from '@/mock/productPurchaseMock.ts'

const loading = ref(false)
const dataSource = ref([])

const columns = [
  { title: '商品名称', dataIndex: 'productName', key: 'productName' },
  { title: '销量', dataIndex: 'quantity', key: 'quantity' }
]

const fetchData = async () => {
  loading.value = true
  // const res = await getTopSellingProductsUsingGet()
  const res = { data: mockTopSellingProducts }
  dataSource.value = res.data.map((item: any, index: number) => ({
    key: index,
    productName: item.name,
    quantity: item.value
  }))
  loading.value = false
}

onMounted(fetchData)
</script>