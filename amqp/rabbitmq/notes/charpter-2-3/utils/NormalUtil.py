import json
import smtplib
import pika


def send_email(recipients, subject, message):
    """
    :param recipients:
    :param subject:
    :param message:
    :return:
    """
    # headers = ("From %s\r\nTo: \r\nDate: \r\n" + "Subject: %s\r\n\r\n") \
    #             % ("987310175@qq.com", subject)
    # smtp_server = smtplib.SMTP()
    # smtp_server.connect("987310175@qq.com", 25)
    # smtp_server.sendmail()
    print("send an email, [From:%s] [subject:%s] [message:%s]" % (recipients, subject, message))
