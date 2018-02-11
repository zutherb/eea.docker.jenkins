#!groovy

import hudson.model.Node.Mode
import hudson.slaves.*
import jenkins.model.Jenkins

def launcher = new JNLPLauncher()

DumbSlave slave_1 = new DumbSlave(
        "slave-1",
        "/var/jenkins_home/worker_1",
        launcher)

DumbSlave slave_2 = new DumbSlave(
        "slave-2",
        "/var/jenkins_home/worker_2",
        launcher)

Jenkins.instance.addNode(slave_1)
Jenkins.instance.addNode(slave_2)

for (aSlave in hudson.model.Hudson.instance.slaves)
{ println aSlave.name + "," + aSlave.getComputer().getJnlpMac() }