FROM openjdk:8u102-jdk

# copy arthas-boot.jar 到镜像的根目录下
COPY arthas-boot.jar /
# copy arthas 启动脚本 到镜像的根目录下
COPY as.sh /
VOLUME /tmp
ADD target/shorter-service-2.0.0.jar  /app.jar

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms256m -Xmx1024m"
RUN ln -snf /usr/share/zoneinfo/$TZ  /etc/localtime && echo $TZ > /etc/timezone


ENTRYPOINT java ${JAVA_OPTS} ${JAVA_OPTS_DEP} -Djava.security.egd=file:/dev/./urandom -jar /app.jar