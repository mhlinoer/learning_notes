from utils.ConnectRabbitMq import RabbitMQConnection
from config_info import config

connections = RabbitMQConnection()
channel = connections.connect_to_rabbitmq()

# 声明交换器
connections.exchange_declare(
    exchange_name=config.rabbitMQ_exchange_hello,
    exchange_type='direct'
)

# 声明一个队列
channel.queue_declare(queue=config.rabbitMQ_queue_hello)

# 通过routing_key把队列和交换器绑定起来
channel.queue_bind(
    queue=config.rabbitMQ_queue_hello,
    exchange=config.rabbitMQ_exchange_hello,
    routing_key='hola'
)


def msg_consumer(channel, method, header, body):
    channel.basic_ack(delivery_tag=method.delivery_tag)
    if body == 'quit':
        channel.basic_cancel(consumer_tag=config.rabbitMQ_consumer_tag)
        channel.stop_consuming()
    else:
        print(body)
    return


# 订阅消费者
channel.basic_consume(
    msg_consumer,
    queue=config.rabbitMQ_queue_hello,
    consumer_tag=config.rabbitMQ_consumer_tag
)
channel.start_consuming()
