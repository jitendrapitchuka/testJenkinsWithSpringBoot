# Spring Boot Jenkins Project

This is a sample spring boot project to test the integration with Jenkins.


i have run jenkins in docker container and created a pipeline job to build this project.

run the jenkins docker container with the root user and map the jenkins home directory to a local directory.

After setup the jenkins , add maven and jdk in the global tool configuration.

install the docker inside the jenkins container.

docker exec -it jenkins bash
apt-get update
apt-get install -y docker.io
exit

