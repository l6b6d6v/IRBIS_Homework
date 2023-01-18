FROM openjdk:17
COPY . /javaProject
WORKDIR /javaProject
EXPOSE 8080
RUN javac DemoApplication.java
CMD ["java", "Main"]
