# ---- Base maven ----
FROM maven:3.5-jdk-8-slim AS base

# prepare the source files for build
RUN mkdir /tmp/job-api
COPY . /tmp/job-api

# global install bower and grunt
RUN cd /tmp/job-api && mvn clean install -DskipTests=true

# find the JAR file and move it
RUN bash -c 'find /tmp/job-api/target -maxdepth 1 -size +1048576c | grep job-definition-api | xargs -I{} mv {} /app.jar'

# now the runnable image
FROM adoptopenjdk/openjdk8:alpine

# copy over the dist from the base build image
COPY --from=base /app.jar /app.jar

COPY ./docker/wait-for-it.sh /wait-for-it.sh

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

EXPOSE 8081
