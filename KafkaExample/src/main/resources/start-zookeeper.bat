@echo off

REM Change to Kafka windows bin directory
cd /d C:\kafka_2.13-3.8.1\bin\windows

REM Start Zookeeper server
zookeeper-server-start.bat ..\..\config\zookeeper.properties
