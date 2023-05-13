# Utiliser l'image debian officielle comme image parent
FROM debian:latest

RUN apt update && apt -y upgrade

RUN apt install -y sudo
RUN sudo -i

RUN sudo apt-get install -y openjdk-11-jdk-headless

RUN sudo apt-get install -y git

RUN sudo apt-get clean

RUN git clone https://github.com/Amaxi76/sae_2.03_grp_08.git

ADD . /test
WORKDIR /test

CMD hostname

RUN chmod +x lancement.sh

CMD ./lancement.sh

EXPOSE 9000