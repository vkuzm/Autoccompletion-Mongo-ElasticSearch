FROM gradle:7.1-jdk11

WORKDIR /home/app
COPY /src /home/app/src
COPY /data /home/app/data
COPY *.gradle /home/app
RUN gradle build -x test

EXPOSE 8080

ENTRYPOINT ["java","-jar","/home/app/build/libs/backend.jar"]