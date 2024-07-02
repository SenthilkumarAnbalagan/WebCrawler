FROM docker.io/openjdk:11-jre

WORKDIR /opt

EXPOSE 6363

COPY *.jar /opt/webcrawler_server_v1.jar

ENTRYPOINT ["/usr/local/openjdk-11/bin/java", "-jar", "webcrawler_server_v1.jar"]

