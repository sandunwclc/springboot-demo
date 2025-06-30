ARG DOCKER_GID=999

FROM jenkins/jenkins:lts

USER root

RUN groupadd -g ${DOCKER_GID} docker || true

# Install Git, Maven, Docker
RUN apt-get update && apt-get install -y \
    git \
    maven \
    docker.io \
    sudo \
 && rm -rf /var/lib/apt/lists/*

# Let Jenkins user use Docker
RUN usermod -aG docker jenkins
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers

USER jenkins

