import pika
import json


creds_broker = pika.PlainCredentials("linoer", "root")
conn_params = pika.ConnectionParameters(
    host="127.0.0.1",
    virtual_host="/",
    credentials=creds_broker,
    port=5672,
)
conn_broker = pika.BlockingConnection(conn_params)
channel = conn_broker.channel()

channel.exchange_declare(
    exchange="rpc",
    exchange_type="direct",
    auto_delete=False
)

channel.queue_declare(
    queue="ping",
    auto_delete=False
)

channel.queue_bind(
    queue="ping",
    exchange="rpc",
    routing_key="ping"
)


def api_ping(channels, method, header, body):
    """
    :param channels:
    :param method:
    :param header:
    :param body:
    :return:
    """
    print("[method:%s] [header:%s] [body:%s]" % (method, header, body))
    channels.basic_ack(
        delivery_tag=method.delivery_tag
    )
    msg_dict = json.loads(str(body, encoding="utf-8"))
    channels.basic_publish(
        body="Pong! :" + str(msg_dict["time"]),
        exchange="",
        routing_key=header.reply_to
    )


channel.basic_consume(
    api_ping,
    queue="ping",
    consumer_tag="ping"
)
channel.start_consuming()
