#!/bin/bash
tar -zxvf apache-tomcat-8.0.43.tar.gz
mv apache-tomcat-8.0.43 tomcat8-linux


tar -zxvf jdk-8u131-linux-x64.tar.gz
mv jdk-8u131-linux-x64 jdk8-linux


xz -d node-v6.10.2-linux-x64.tar.xz
tar -xvf node-v6.10.2-linux-x64.tar


tar -zxvf apache-maven-3.5.0-bin.tar.gz
tar -zxvf subversion-1.9.5.tar.gz
