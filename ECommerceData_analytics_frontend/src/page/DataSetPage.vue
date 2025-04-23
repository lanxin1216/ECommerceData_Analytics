<template>
  <div>
    <h1 class="page-title">数据集</h1>
    <div class="notice-box">
      1、 本数据集完全由算法生成，所有数据均为虚构<br>
      2、阿迪达斯订单数据: 行数：500000 列数：14<br>
      3、阿迪达斯用户数据: 行数：10000 列数：6<br>
      4、数据来源：<a href="https://www.heywhale.com/mw/project/67d0eb5524aa5d006f2ad3e6/content">和鲸社区</a>
    </div>

    <!-- 用户数据表格 -->
    <a-card title="阿迪达斯用户数据">
      <a-table
        :columns="userColumns"
        :data-source="userData"
        :pagination="userPagination"
        row-key="user_id"
        @change="handleUserPageChange"
        :loading="userLoading"
      />
    </a-card>
    <div style="margin-top: 10px" />
    <!-- 订单数据查询 -->
    <!-- 订单数据表格 -->
    <a-card title="阿迪达斯订单数据">
      <a-table
        :columns="orderColumns"
        :data-source="orderData"
        row-key="order_id"
        :loading="orderLoading"
        scroll="{ x: true }"
      />
    </a-card>

  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { message } from 'ant-design-vue'

const userColumns = [
  { title: '用户ID', dataIndex: 'user_id' },
  { title: '性别', dataIndex: 'gender' },
  { title: '年龄', dataIndex: 'age' },
  { title: '是否会员', dataIndex: 'is_member' },
  { title: '省份', dataIndex: 'province' },
  { title: '城市', dataIndex: 'city' }
]

const orderColumns = [
  { title: '订单ID', dataIndex: 'order_id' },
  { title: '用户ID', dataIndex: 'user_id' },
  { title: '商品名称', dataIndex: 'product_name' },
  { title: '单价', dataIndex: 'unit_price' },
  { title: '数量', dataIndex: 'quantity' },
  { title: '订单总额', dataIndex: 'total_amount' },
  { title: '下单时间', dataIndex: 'order_time' },
  { title: '省份', dataIndex: 'delivery_province' },
  { title: '城市', dataIndex: 'delivery_city' }
]

const userData = ref([])
const userPagination = ref({ current: 1, pageSize: 10, total: 0 })
const userLoading = ref(false)

const fetchUsers = async (page = 1, pageSize = 10) => {
  userLoading.value = true
  try {
    // const res = await axios.get('/api/dataset/users', {
    //   params: { page, pageSize },
    // })
    // userData.value = res.data.data

    // 👉 Mock 数据
    const mockUsers = Array.from({ length: pageSize }, (_, i) => ({
      user_id: `U${(page - 1) * pageSize + i + 1}`,
      gender: i % 2 === 0 ? '男' : '女',
      age: 20 + (i % 10),
      is_member: i % 3 === 0 ? '是' : '否',
      province: '浙江省',
      city: `城市${i + 1}`
    }))
    userData.value = mockUsers
    userPagination.value.total = 50 // 假设总共 50 条
  } catch (e) {
    message.error('获取用户数据失败')
  } finally {
    userLoading.value = false
  }
}

// const fetchUsers = async (page = 1, pageSize = 10) => {
//   userLoading.value = true
//   try {
//     const res = await axios.get('/api/dataset/users', {
//       params: { page, pageSize },
//     })
//     userData.value = res.data.data
//     // 可以根据总数做更精准分页（需后端支持 total）
//   } catch (e) {
//     message.error('获取用户数据失败')
//   } finally {
//     userLoading.value = false
//   }
// }

const handleUserPageChange = (pagination: any) => {
  userPagination.value = pagination
  fetchUsers(pagination.current, pagination.pageSize)
}

onMounted(() => {
  fetchUsers()
  fetchOrders()
})

// 订单数据
const orderData = ref([])
const orderLoading = ref(false)

const fetchOrders = async () => {
  orderLoading.value = true
  try {
    // const res = await axios.get('/api/dataset/orders')
    // orderData.value = res.data.data

    // 👉 Mock 数据（可删除后注释取消）
    const mockOrders = Array.from({ length: 20 }, (_, i) => ({
      order_id: `ORD1000${i + 1}`,
      user_id: `U${i + 1}`,
      product_name: `商品${i + 1}`,
      unit_price: (100 + i * 10).toFixed(2),
      quantity: (i % 5) + 1,
      total_amount: ((100 + i * 10) * ((i % 5) + 1)).toFixed(2),
      order_time: `2025-01-${String((i % 30) + 1).padStart(2, '0')}`,
      delivery_province: '浙江省',
      delivery_city: `城市${i + 1}`
    }))
    orderData.value = mockOrders
  } catch (e) {
    message.error('获取订单数据失败')
  } finally {
    orderLoading.value = false
  }
}

// const fetchOrders = async () => {
//   orderLoading.value = true
//   try {
//     const res = await axios.get('/api/dataset/orders', {
//       params: { month: selectedMonth.value },
//     })
//     orderData.value = res.data.data
//   } catch (e) {
//     message.error('获取订单数据失败')
//   } finally {
//     orderLoading.value = false
//   }
// }
</script>

<style scoped>
.page-title {
  text-align: center;
  margin-bottom: 24px;
}

.notice-box {
  background-color: #f8f8f8;
  color: #666;
  padding: 15px;
  margin: 0 auto 20px;
  border-radius: 4px;
  text-align: left;
  line-height: 1.6;
  border: 1px solid #dddddd;
}

.mb-4 {
  margin-bottom: 16px;
}

.flex {
  display: flex;
  align-items: center;
}

.gap-2 {
  gap: 8px;
}
</style>
