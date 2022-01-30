FROM maven:3-jdk-11

ADD . /configuration
WORKDIR /configuration

RUN ls -l

RUN mvn clean install -U

CMD mvn spring-boot:run
