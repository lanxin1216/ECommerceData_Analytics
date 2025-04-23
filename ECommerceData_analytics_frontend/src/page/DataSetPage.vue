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
        row-key="users.user_id"
        @change="handleUserPageChange"
        :loading="userLoading"
        :scroll="{ x: 'max-content', y: 400 }"
        bordered
      />
    </a-card>

    <a-card title="阿迪达斯订单数据" style="margin-top: 10px">
      <a-table
        :columns="orderColumns"
        :data-source="orderData"
        row-key="orders.order_id"
        :loading="orderLoading"
        :scroll="{ x: 'max-content', y: 400 }"
        bordered
      />
    </a-card>

  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { message } from 'ant-design-vue'
import { getOrderSampleUsingGet, getUsersUsingGet } from '@/api/getDataSetController.ts'

const userColumns = [
  { title: '用户ID', dataIndex: 'users.user_id', key: 'user_id' },
  { title: '性别', dataIndex: 'users.gender' },
  { title: '年龄', dataIndex: 'users.age' },
  { title: '是否会员', dataIndex: 'users.is_member' },
  { title: '省份', dataIndex: 'users.province' },
  { title: '城市', dataIndex: 'users.city' }
]

const orderColumns = [
  { title: '订单ID', dataIndex: 'orders.order_id' },
  { title: '用户ID', dataIndex: 'orders.user_id' },
  { title: '下单时间', dataIndex: 'orders.order_time' },
  { title: '品类', dataIndex: 'orders.category' },
  { title: '子品类', dataIndex: 'orders.sub_category' },
  { title: '商品名称', dataIndex: 'orders.product_name' },
  { title: '单价', dataIndex: 'orders.unit_price' },
  { title: '数量', dataIndex: 'orders.quantity' },
  { title: '订单总额', dataIndex: 'orders.total_amount' },
  { title: '省份', dataIndex: 'orders.delivery_province' },
  { title: '城市', dataIndex: 'orders.delivery_city' },
  { title: '性别', dataIndex: 'orders.gender' },
  { title: '年龄', dataIndex: 'orders.age' },
  { title: '是否会员', dataIndex: 'orders.is_member' },
  { title: '下单月份', dataIndex: 'orders.order_month' }
]

const userData = ref([])
const userPagination = ref({ current: 1, pageSize: 100, total: 0 })
const userLoading = ref(false)

const fetchUsers = async (page = 1, pageSize = 100) => {
  userLoading.value = true
  try {
    const res = await getUsersUsingGet({ page: page, pageSize: pageSize })
    if (res.data.code === 0) {
      userData.value = res.data.data
      userPagination.value.total = 10000
    } else {
      message.error(res.data.message || '获取用户数据失败')
    }
  } catch (e) {
    message.error('获取用户数据失败')
  } finally {
    userLoading.value = false
  }
}

const handleUserPageChange = (pagination: any) => {
  userPagination.value = pagination
  fetchUsers(pagination.current, pagination.pageSize)
}

const orderData = ref([])
const orderLoading = ref(false)

const fetchOrders = async () => {
  orderLoading.value = true
  try {
    const res = await getOrderSampleUsingGet()
    if (res.data.code === 0) {
      orderData.value = res.data.data
    } else {
      message.error(res.message || '获取订单数据失败')
    }
  } catch (e) {
    message.error('获取订单数据失败')
  } finally {
    orderLoading.value = false
  }
}

onMounted(() => {
  fetchUsers()
  fetchOrders()
})
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
