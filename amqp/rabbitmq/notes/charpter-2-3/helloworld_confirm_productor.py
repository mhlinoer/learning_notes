import sys
import pika
from pika import spec
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


def confirm_handler(frame):
    if type(frame.method) == spec.Confirm.SelectOk:
        print("Channel in confirm mode")
    elif type(frame.method) == spec.Basic.Nack:
        if frame.method.delivery_tag in msg_ids:
            print("Message is lost")
    elif type(frame.method) == spec.Basic.Ack:
        if frame.method.delivery_tag in msg_ids:
            print("Message is received")
            msg_ids.remove(frame.method.delivery_tag)


# 将信道设置成confirm模式
channel.confirm_delivery(callback=confirm_handler)

#
# # 声明交换器,direct类型
# connection.exchange_declare(
#     exchange_name=config.rabbitMQ_exchange_hello,
#     exchange_type='direct'
# )

# 创建一个文本消息
msg = sys.argv[1]   # 消息内容是改脚本的第一个参数
msg_props = pika.BasicProperties()
msg_props.content_type = 'text/plain'
msg_ids = []

# 发布消息
channel.basic_publish(
    body=msg,
    exchange=config.rabbitMQ_exchange_hello,
    properties=msg_props,
    routing_key='hola'
)
msg_ids.append(len(msg_ids) + 1)
channel.close()

