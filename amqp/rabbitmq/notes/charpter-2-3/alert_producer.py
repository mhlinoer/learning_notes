# -*- coding: utf-8 -*-
import json
import pika

from optparse import OptionParser

opt_parser = OptionParser()

opt_parser.add_option(
    "-r",
    "--routing_key",
    dest="routing_key",
    help="Routing key from message"
)

opt_parser.add_option(
    "-m",
    "--message",
    dest="message",
    help="Message text"
)

args = opt_parser.parse_args()[0]

creds_broker = pika.PlainCredentials("alert_user", "root")

conn_params = pika.ConnectionParameters(
    'localhost',
    port=5672,
    credentials=creds_broker
)

conn_broker = pika.BlockingConnection(conn_params)
channel = conn_broker.channel()

msg = json.dumps(args.message)
msg_props = pika.BasicProperties()
msg_props.content_type = "application/json"

msg_props.durable = False

channel.basic_publish(
    body=msg,
    exchange="alerts",
    properties=msg_props,
    routing_key=args.routing_key
)


