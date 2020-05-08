FROM java:8
EXPOSE 9092:9092
ADD /target/user-management.jar user-management.jar
ENTRYPOINT ["java","-jar","user-management.jar"]