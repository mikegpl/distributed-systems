#!/bin/bash
case $1 in
start)
	echo "Starting zookeepers"
	misc/zookeeper-3.4.12/bin/zkServer.sh start "conf/s1.cfg"
	misc/zookeeper-3.4.12/bin/zkServer.sh start "conf/s2.cfg"
	misc/zookeeper-3.4.12/bin/zkServer.sh start "conf/s3.cfg"
	;;
stop)
	echo "Stopping zookeepers"
	misc/zookeeper-3.4.12/bin/zkServer.sh stop "conf/s1.cfg"
	misc/zookeeper-3.4.12/bin/zkServer.sh stop "conf/s2.cfg"
	misc/zookeeper-3.4.12/bin/zkServer.sh stop "conf/s3.cfg"
	;;
esac