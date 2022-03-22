docker run -it --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq /bin/bash
rabbitmq-plugins enable rabbitmq_management
rabbitmq-server start
#默认账号密码为 guest guest