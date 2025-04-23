<template>
  <a-card title="单品销量排行">
    <a-table
      :dataSource="dataSource"
      :columns="columns"
      :loading="loading"
      rowKey="productName"
      :pagination="false"
    />
  </a-card>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { message } from 'ant-design-vue'
import { getTopSellingProductsUsingGet } from '@/api/productPurchaseAnalysisController.ts'

const loading = ref(false)
const dataSource = ref<{ productName: string; quantity: number }[]>([])

const columns = [
  { title: '商品名称', dataIndex: 'productName', key: 'productName' },
  { title: '销量', dataIndex: 'quantity', key: 'quantity' }
]

const fetchData = async () => {
  loading.value = true
  try {
    const res = await getTopSellingProductsUsingGet()
    if (res.data.code === 0 && res.data.data?.xAxis?.length > 0) {
      const names = res.data.data.xAxis as string[]
      const values = (res.data.data.series?.[0]?.data || []) as number[]

      dataSource.value = names.map((name, index) => ({
        productName: name,
        quantity: values[index] ?? 0
      }))
    } else {
      message.warning('暂无销量数据')
      dataSource.value = []
    }
  } catch (err) {
    message.error('加载失败，请稍后重试')
    console.error('加载单品销量排行失败:', err)
  } finally {
    loading.value = false
  }
}

onMounted(fetchData)
</script>
