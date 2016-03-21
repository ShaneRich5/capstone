#!/bin/bash

REMOTE=root@159.203.73.159
REMOTE_APP=/root/capstone

ssh $REMOTE << EOF

    if [ -d "$REMOTE_APP" ]; then
        git clone git@github.com:ShaneRich5/capstone.git
        cd capstone
    elsehttps://www.youtube.com/watch?v=-ToxHXK2gDc&ebc=ANyPxKoCYbuyoSsfvcxqF1dptROW1y-tVIqXYU-ZID3rbifYV5joYj6QUddtI_auH8mXzu5rJLhToRyXzHENtVRe3LeQKUAKbA
        cd capstone
        echo "pulled"
        git pull origin master
    fi


    sbt stage || exit 1
    #rsync -va target/ $REMOTE:$REMOTE_APP/target;
    #ssh $REMOTE "cd $REMOTE_APP"; ./stop.sh";
    #ssh $REMOTE "cd $REMOTE_APP; ./start.sh"
    #./stop.sh
    #./start.sh

    #stop
    #test -f RUNNING_PID && kill `cat RUNNING_PID` && sleep 5;
    #rm RUNNING_PID;

    #start
    #nohup ./target/universal/stage/bin/APPLICATION_SBT_NAME -Dhttp.port=9090 &

    ./activator playGenerateSecret
    ./activator dist
    unzip target/universal/capstone-1.0-SNAPSHOT.zip
    nohup ./capstone-1.0-SNAPSHOT/bin/capstone -Dhttp.port=9090
EOF