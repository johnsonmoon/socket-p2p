#!/bin/bash

source /etc/profile

BIN_DIR=$(cd "$(dirname "$0")"; pwdclear)
USER_DIR=${BIN_DIR}/..
cd ${USER_DIR}

APP_NAME="socket-p2p"
JAVA_CMD="java"
JAVA_OPTS="-Dapp.name=${APP_NAME} -Xmx512m -Xms256m -Xss256K -XX:MaxMetaspaceSize=256m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSClassUnloadingEnabled -XX:+ExplicitGCInvokesConcurrent"
CLASS_PATH="${USER_DIR}/lib/*:${CLASSPATH}"
MAIN_CLASS="com.github.johnsonmoon.socket.p2p.UIEndPoint"

nohup jre/bin/java -classpath "${CLASS_PATH}" ${MAIN_CLASS} >> /dev/null 2>&1 &
