# Utiliser l'image debian officielle comme image parent
FROM debian:latest

RUN apt update && apt -y upgrade

RUN apt install -y sudo
RUN sudo -i

RUN sudo apt-get install -y openjdk-11-jdk-headless

RUN sudo apt-get clean

COPY . /test
WORKDIR /test/bataillenavale

RUN javac @compile.list -encoding utf-8

WORKDIR /test

CMD java bataillenavale.Serveur

EXPOSE 9000