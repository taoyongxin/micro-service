FROM registry.cn-hangzhou.aliyuncs.com/library-bd/openjdk:8-jdk-alpine
LABEL maintainer="https://4anclfac.mirror.aliyuncs.com"
# 指定工作目录
WORKDIR /app
COPY user-center-1.0-SNAPSHOT.jar ./app.jar
# 设置时区为上海
ENV TZ Asia/Shanghai
# 设置时区为上海
ENV JAVA_OPS -Duser.timezone=Asia/Shanghai
EXPOSE 8011
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]