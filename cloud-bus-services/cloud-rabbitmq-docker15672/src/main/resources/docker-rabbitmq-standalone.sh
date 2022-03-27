docker run -it --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq /bin/bash
rabbitmq-plugins enable rabbitmq_management
cat /etc/rabbitmq/conf.d/management_agent.disable_metrics_collector.conf
echo management_agent.disable_metrics_collector = false > /etc/rabbitmq/conf.d/management_agent.disable_metrics_collector.conf
rabbitmq-server start
#默认账号密码为 guest guest

docker run -d --restart always -p 9411:9411 --name zipkin openzipkin/zipkin