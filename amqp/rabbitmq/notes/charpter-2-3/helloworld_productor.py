import sys
import pika
from utils.ConnectRabbitMq import RabbitMQConnection
from config_info import config
# 发布者的任务
# 连接到RabbitMQ
# 获取信道
# 声明交换器
# 发布消息
# 关闭信道
# 关闭连接

# 建立到代理服务器的连接
# 获取channel信道
connection = RabbitMQConnection()
channel = connection.connect_to_rabbitmq()

# 声明交换器,direct类型
connection.exchange_declare(
    exchange_name=config.rabbitMQ_exchange_hello,
    exchange_type='direct'
)

# 创建一个文本消息
msg = sys.argv[1]   # 消息内容是改脚本的第一个参数
msg_props = pika.BasicProperties()
msg_props.content_type = 'text/plain'

# 发布消息
channel.basic_publish(
    body=msg,
    exchange=config.rabbitMQ_exchange_hello,
    properties=msg_props,
    routing_key='hola'
)
