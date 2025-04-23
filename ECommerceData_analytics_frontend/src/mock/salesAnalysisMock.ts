export const mockOrderTrend = {
  xAxis: ['01月', '02月', '03月', '04月', '05月'],
  series: [
    {
      name: '订单量',
      type: 'line',
      data: [120, 200, 150, 80, 70]
    }
  ]
}

export const mockHotCategory = {
  xAxis: ['手机', '家电', '电脑', '美妆', '图书'],
  series: [
    {
      name: '销售额',
      type: 'bar',
      data: [50000, 42000, 38000, 26000, 18000]
    }
  ]
}

export const mockRegionSales = {
  xAxis: ['广东', '江苏', '北京', '上海', '浙江'],
  series: [
    {
      name: '销售额',
      type: 'bar',
      data: [80000, 72000, 65000, 61000, 50000]
    }
  ]
}

export const mockAgeGroup = {
  xAxis: ['18-25', '26-35', '36-45', '46-60', '60+'],
  series: [
    {
      name: '消费金额',
      type: 'bar',
      data: [30000, 50000, 40000, 25000, 10000]
    }
  ]
}

export const mockMemberCompare = {
  xAxis: ['会员', '非会员'],
  series: [
    {
      name: '订单数',
      type: 'bar',
      data: [600, 400]
    },
    {
      name: '消费总额',
      type: 'bar',
      data: [120000, 75000]
    }
  ]
}
