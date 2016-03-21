test -f RUNNING_PID && kill `cat RUNNING_PID` && sleep 5;
rm RUNNING_PID;