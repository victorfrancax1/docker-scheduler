FROM java:8-alpine
RUN mkdir -p /app /app/resources
WORKDIR /app
COPY target/*-standalone.jar .
CMD java -jar docker-scheduler-0.1.0-SNAPSHOT-standalone.jar
EXPOSE 3000
