import pika


class RabbitMQConnection:
    _channel = None

    def connect_to_rabbitmq(self):
        credentials = pika.PlainCredentials('linoer', 'root')
        conn_params = pika.ConnectionParameters('localhost', port=5672, credentials=credentials)
        conn_broker = pika.BlockingConnection(conn_params)
        self._channel = conn_broker.channel()
        return self._channel

    def exchange_declare(self, exchange_name, exchange_type='direct'):
        '''
        :param exchange_name:
        :param exchange_type:
        :return:
        '''
        if self._channel:
            self._channel.exchange_declare(
                exchange=exchange_name,
                exchange_type=exchange_type,
                passive=False,  # 声明此处是一个交换器
                durable=True,  # 持久化交换器
                auto_delete=False,  # 持久化交换器
            )
        else:
            pass
