FROM docker.io/openjdk:11-jre

WORKDIR /opt

COPY *.jar /opt/webcrawler_client_v1.jar

ENTRYPOINT exec java $JAVA_OPTS -jar webcrawler_client_v1.jar -z

