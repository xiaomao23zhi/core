FROM jdk-ffmpeg-cuda:1.8-5.1-11.0
LABEL MAINTAINER xiaomao23zhi

# set environment
ENV BASE_DIR="/home/java" \
    JAVA_OPTS="" \
    APPLICATION_NAME="core-service" \
    NACOS_CONFIG_SERVER="nacos-headless:8848" \
    NACOS_CONFIG_GROUPID="DEFAULT_GROUP" \
    NACOS_CONFIG_DATAID="core-service.yaml" \
    NACOS_DISCOVERY_SERVER="nacos-headless:8848" \
    STENTINEL_DASHBOARD_SERVER="sentinel-dashboard-svc:8021" \
    SERVER_PORT="9050" \
    TIME_ZONE="Asia/Shanghai"

WORKDIR /$BASE_DIR
#RUN set -x \
#    #&& apk --no-cache add ca-certificates  \
#    #&& update-ca-certificates \
#    && cp /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo '$TIME_ZONE' > /etc/timezone

ADD target/core-0.0.1-SNAPSHOT.jar app.jar
ADD bin/docker-entrypoint.sh bin/docker-entrypoint.sh

# set startup log dir
RUN mkdir -p logs \
        && cd logs \
        && touch start.out \
        && ln -sf /dev/stdout start.out \
        && ln -sf /dev/stderr start.out
RUN chmod +x bin/docker-entrypoint.sh

EXPOSE 8080
ENTRYPOINT ["bin/docker-entrypoint.sh"]