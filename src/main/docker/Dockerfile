FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/development-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "java", "$JAVA_OPTS", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar" ]

