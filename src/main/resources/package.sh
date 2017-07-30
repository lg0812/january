#!/bin/bash
cd /code/may
git pull
if [ $? == 0 ]
then
        echo "build start"
        npm run build
        if [ $? == 0 ]
        then    
                echo "build end"

                cd /code/january
                git pull
                if [ $? == 0 ]
                then
                        mvn clean
                        mvn package -Dmaven.test.skip=true
                        cp ./target/january.war /lg0812/apache-tomcat-8.5.15/webapps
                        cp /code/may/build/index.html /lg0812/apache-tomcat-8.5.15/webapps/january/
                        rm -rf /lg0812/apache-tomcat-8.5.15/webapps/static
                        cp -rf /code/may/build/static /lg0812/apache-tomcat-8.5.15/webapps
                        tail -f  /lg0812/apache-tomcat-8.5.15/logs/catalina.out
                else
                        echo "git pull failed"
                fi
        fi
        
fi