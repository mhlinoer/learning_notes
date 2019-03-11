# -*- coding: utf-8 -*-
from utils.NormalUtil import *


def critical_notify(m, method, header, body):
    """
    :param m:
    :param method:
    :param header:
    :param body:
    :return:
    """

    EMAIL_ADDRESS = 'alertemail@163.com'
    message = json.loads(body)

    send_email(EMAIL_ADDRESS, "CRITICAL ALERT", message)

    m.basic_ack(
        delivery_tag=method.delivery_tag
    )


def rate_limit_notify(m, method, header, body):
    EMAIL_ADDRESS = 'ratelimitemail@163.com'
    message = json.loads(body)

    send_email(EMAIL_ADDRESS, "RATE LIMIT ALERT", message)

    m.basic_ack(
        delivery_tag=method.delivery_tag
    )


if __name__ == "__main__":
    AMQP_SERVER = "127.0.0.1"
    AMQP_USER = "alert_user"
    AMQP_PASS = "root"
    AMQP_VHOST = "/"
    AMQP_EXCHANGE = "alerts"

    conn_params = pika.ConnectionParameters(
        AMQP_SERVER,
        virtual_host=AMQP_VHOST,
        credentials=pika.PlainCredentials(AMQP_USER, AMQP_PASS)
    )
    conn_broker = pika.BlockingConnection(conn_params)
    channel = conn_broker.channel()

    # 创建一个交换器
    channel.exchange_declare(
        exchange=AMQP_EXCHANGE,
        exchange_type='topic',
        auto_delete=False
    )

    # 创建一个队列
    channel.queue_declare(
        queue='critical',   # 队列名
        auto_delete=False
    )

    # 绑定队列
    channel.queue_bind(
        queue='critical',
        exchange=AMQP_EXCHANGE,
        routing_key='critical.*'
    )

    # 声明另一个队列
    channel.queue_declare(
        queue='rate_limit',
        auto_delete=False
    )

    # 绑定它
    channel.queue_bind(
        queue='rate_limit',
        exchange=AMQP_EXCHANGE,
        routing_key='*.rate_limit'
    )

    # 订阅消息
    channel.basic_consume(
        critical_notify,    # 接收到之后的回调
        queue='critical',   # 从哪个队列获取消息
        no_ack=False,       # 显示确认收到的消息，会让rabbitmq暂停从队列发送新的消息，直到收到最后一条消息并处理完成发送ack为止.
        consumer_tag='critical'     # 标识符
    )

    channel.basic_consume(
        rate_limit_notify,
        queue='rate_limit',
        no_ack=False,
        consumer_tag='rate_limit'
    )

    # 轮询开始咯
    channel.start_consuming()










