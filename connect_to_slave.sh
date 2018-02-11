#!/usr/bin/env bash

while [ true ]
do
    sleep 20
    SCRIPT_NAME="/get_$JENKINS_AGENT_NAME"
    SCRIPT_NAME+="_secret.groovy"
    SECRET=$(curl -s --user 'admin:admin' --data-urlencode "script=$(< $SCRIPT_NAME)" $JENKINS_URL/scriptText)

    echo jenkins-slave -url http://$JENKINS_URL $SECRET $JENKINS_AGENT_NAME
    jenkins-slave -url http://$JENKINS_URL $SECRET $JENKINS_AGENT_NAME
done

