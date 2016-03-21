#!/bin/bash

REMOTE=root@159.203.73.159
REMOTE_APP=/home/play/capstone/

sbt stage || exit 1;
rsync -va target/ $REMOTE:$REMOTE_APP/target;
ssh $REMOTE "cd $REMOTE_APP"; ./stop.sh";
ssh $REMOTE "cd $REMOTE_APP; .start.sh"