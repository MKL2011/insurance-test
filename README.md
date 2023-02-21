# insurance-test

feature 1 : list policies
feature 2 : add policy
feature 3 : get policy
feature 4 : edit policy


technical stack : 
java 17
springboot
springdata 
springweb
h2
docker

docker commands : 
docker build -t tinubutest.jar .
docker run -p 9090:8080 tinubutest.jar

in order to init database h2 , set spring.sql.init.mode=always then launch the application , then reset to spring.sql.init.mode=never 
