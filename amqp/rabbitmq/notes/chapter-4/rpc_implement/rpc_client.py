import time
import json
import pika

creds_broker = pika.PlainCredentials("linoer", "root")

conn_params = pika.ConnectionParameters(
    host="127.0.0.1",
    virtual_host="/",
    port=5672,
    credentials=creds_broker
)

conn_broker = pika.BlockingConnection(conn_params)

channel = conn_broker.channel()
msg = json.dumps(
    {"client_name": "RPC 1.0", "time": time.time()}
)

result = channel.queue_declare(
    exclusive=True,
    auto_delete=False
)
msg_props = pika.BasicProperties()
msg_props.reply_to = result.method.queue
channel.basic_publish(
    body=msg,
    exchange="rpc",
    properties=msg_props,
    routing_key="ping"
)


def reply_callback(channels, method, header, body):
    print("[method:%s] [header:%s] [body:%s]" % (method, header, body))
    channels.stop_consuming()


channel.basic_consume(
    reply_callback,
    queue=result.method.queue,
    consumer_tag=result.method.queue
)
channel.start_consuming()
