FROM bde2020/spark-maven-template:3.1.2-hadoop3.2

MAINTAINER Rimpal Suthar

ENV SPARK_APPLICATION_MAIN_CLASS com.njit.winequalitypred.Prediction

ENV SPARK_APPLICATION_JAR_NAME Wine-Quality-Prediction-1.0

ENV SPARK_APPLICATION_ARGS "file:///usr/src/app/data/Test-File.csv file:///usr/src/app/data/model"

#ADD ./data/model /usr/src/app/data/

COPY data/model/* /usr/src/app/data/model


