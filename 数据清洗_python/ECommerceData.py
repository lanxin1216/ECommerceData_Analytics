import pandas as pd
from datetime import datetime

# 配置路径（根据实际路径修改）
user_csv_path = "./data/阿迪达斯用户数据.csv"
order_csv_path = "./data/阿迪达斯订单数据.csv"
output_user_path = "./data/processed_users.csv"
output_order_path = "./data/processed_orders.csv"

# ---------------------- 用户数据列名映射 ----------------------
user_column_mapping = {
    "用户ID": "user_id",
    "性别": "gender",
    "年龄": "age",
    "是否会员": "is_member",
    "常用省份": "province",
    "常用城市": "city"
}

# 处理用户数据
df_user = pd.read_csv(user_csv_path)
df_user.rename(columns=user_column_mapping, inplace=True)
df_user.to_csv(output_user_path, index=False, encoding='utf-8')

# ---------------------- 订单数据列名映射 + 分区字段生成 ----------------------
order_column_mapping = {
    "订单ID": "order_id",
    "用户ID": "user_id",
    "下单时间": "order_time",
    "商品大类": "category",
    "商品子类": "sub_category",
    "商品名称": "product_name",
    "单价": "unit_price",
    "购买数量": "quantity",
    "订单总额": "total_amount",
    "收货省份": "delivery_province",
    "收货城市": "delivery_city",
    "性别": "gender",  # 订单表中的冗余字段可能需要保留
    "年龄": "age",     # 同上
    "是否会员": "is_member"  # 同上
}

# 处理订单数据
df_order = pd.read_csv(order_csv_path)
df_order.rename(columns=order_column_mapping, inplace=True)

# 添加分区字段：从下单时间提取月份（格式：yyyy-MM）
df_order['order_month'] = df_order['order_time'].apply(
    lambda x: datetime.strptime(x.split('.')[0], "%Y-%m-%d %H:%M:%S").strftime("%Y-%m")
)

df_order.to_csv(output_order_path, index=False, encoding='utf-8')

print("数据处理完成！输出文件：", output_user_path, output_order_path)