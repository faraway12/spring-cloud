docker run -it --name consul -p 8500:8500 consul /bin/sh
consul agent -dev -client 0.0.0.0 -ui