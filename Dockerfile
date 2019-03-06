FROM docker.io/java

MAINTAINER zhanwei_huang hzwy23@163.com

WORKDIR /opt

ADD ./target/ ./basic

WORKDIR basic

EXPOSE 8080

ENV TZ Asia/Shanghai

CMD java -Xms768m -Xmx768m  -jar task-executor-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
