#!/bin/bash


source  /etc/profile


jarDir=/home/ymdxtest/yangguang/linecommon/machine_learning/lib
serviceName='manage web'
jarFile=${jarDir}/ml-nb-1.0-SNAPSHOT.jar


startService(){

  ps -ef|grep -v grep|grep $jarFile    1>/dev/null 2>&1

        if [ $? -eq 0 ];then

                        echo "$serviceName is already started"

        else

          echo -n "Starting the $serviceName service......"

         allJars="$(find   ${jarDir}  -name '*.jar' | paste -d : -s)"   ;
           echo   $allJars     ;
   #       java  -cp   $allJars   com.ymdx.ml.TestMatrix  "/home/ymdxtest/yangguang/linecommon/machine_learning/car.data"
    java  -cp   $allJars  com.example.ml.MultinomialNB   "/home/ymdxtest/yangguang/linecommon/machine_learning/train.txt"
        sleep  10

                ps -ef|grep -v grep|grep $jarFile 1>/dev/null 2>&1

                if [ $? -eq 0 ];then

                                echo "done"

                else

                                echo "failure"

                fi

        fi
}


 startService

