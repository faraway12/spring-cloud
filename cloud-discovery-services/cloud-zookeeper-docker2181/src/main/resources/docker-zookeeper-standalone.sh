docker run -it --name zoo1 -p 2181:2181 zookeeper /bin/bash
./bin/zkServer.sh restart
./bin/zkCli.sh