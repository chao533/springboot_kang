systemctl stop firewalld.service 	防火墙
systemctl stop iptables.service
sudo  firewall-cmd --zone=public --add-port=11129/tcp --permanent 	暴露端口
sudo firewall-cmd --reload  	重启防火墙
shutdown -r now 	重启
shutdown -h now 	关机

-- springboot项目启动容器
docker run -itd -p8081:8081 --name aio_auth --restart=always 205bd678ec9f /bin/bash
docker run -itd -p8080:8080 --name aio_pc --restart=always 205bd678ec9f /bin/bash

--springcloud项目启动容器
docker run -d --name="rc_service" --expose=8071 -p 8071:8071 -e "EUREKA_INSTANCE_IP-ADDRESS=192.168.0.100" -e "SERVER_PORT=8071" 0e4f1a81b640 /bin/bash
docker run -d --name="app_cloudconfig" --expose=8771 -p 8771:8771 -e "EUREKA_INSTANCE_IP-ADDRESS=192.168.0.200" -e "SERVER_PORT=8771" 0e4f1a81b640 /bin/bash
clean install docker:build -DpushImage

-- rabbitmq
docker run -d -p 5672:5672 -p 15672:15672 -e RABBITMQ_PASS="mypass" --restart=always tutum/rabbitmq
rabbitmqctl stop_app
rabbitmqctl reset
rabbitmqctl start_app

-- mongodb
docker run -d -p 27017:27017 -p 28017:28017 -e MONGODB_PASS="123456" tutum/mongodb
mongo admin -u admin -p 123456
docker exec -it a7b25e82be1b /bin/bash

-- elasticsearch
docker pull elasticsearch

-- redis
docker run -p 6379:6379 --name redis -v /zzyyuse/myredis/data:/data -v /zzyyuse/myredis/conf/redis.conf:/usr/local/etc/redis/redis.conf  -d redis:3.2 redis-server /usr/local/etc/redis/redis.conf --appendonly yes

-- mysql
docker run -p 3306:3306 --name mysql -v /zzyyuse/mysql/conf:/etc/mysql/conf.d -v /zzyyuse/mysql/logs:/logs -v /zzyyuse/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.6

-- zookeeper
docker pull zookeeper
docker run --name zk -p 2181:2181 -p 2888:2888 -p 3888:3888 --restart always -d zookeeper

-- FastDFS
docker pull morunchang/fastdfs
docker run -d --name tracker --net=host morunchang/fastdfs sh tracker.sh
docker run -d --name storage --net=host -e TRACKER_IP=192.168.36.128:22122 -e GROUP_NAME=group1 morunchang/fastdfs sh storage.sh

-- xxl_job
docker pull xuxueli/xxl-job-admin:2.0.1
docker run -e PARAMS="--spring.datasource.url=jdbc:mysql://47.105.189.247:3306/xxl_job?useUnicode=true&characterEncoding=UTF-8 --spring.datasource.password=123456" -p 8181:8080 -v /xxl-job-log:/data/applogs --name xxl-job-admin -d xuxueli/xxl-job-admin:2.0.1

-- docker私服
http://192.168.0.100:5000/v2/_catalog
docker pull registry
docker run -di --name=registry -p 5000:5000 registry
vi /etc/docker/daemon.json
{"insecure-registries":["192.168.184.141:5000"]} 

-- 配置DockerMaven插件
vim /lib/systemd/system/docker.service
ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock
systemctl daemon-reload
systemctl restart docker


--docker卸载
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-selinux \
                  docker-engine-selinux \
                  docker-engine