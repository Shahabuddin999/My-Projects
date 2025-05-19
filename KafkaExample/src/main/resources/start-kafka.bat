@echo off

REM Navigate to Kafka bin directory
cd /d C:\kafka_2.13-3.8.1\bin\windows

REM Start Kafka server
kafka-server-start.bat ..\..\config\server.properties
