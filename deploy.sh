#!/bin/bash

REMOTE=root@159.203.73.159
REMOTE_APP=/root/capstone

ssh $REMOTE <<EOF
    if [ -d "$REMOTE_APP" ]; then
        git clone git@github.com:ShaneRich5/capstone.git
        cd capstone
    else
        cd capstone
        git pull origin master
    fi

    sbt stage || exit 1;
    rsync -va target/ $REMOTE:$REMOTE_APP/target;
    ssh $REMOTE "cd $REMOTE_APP"; ./stop.sh";
    ssh $REMOTE "cd $REMOTE_APP; .start.sh"
EOF


