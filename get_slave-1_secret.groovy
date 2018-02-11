import hudson.model.Node.Mode
import hudson.slaves.*
import jenkins.model.Jenkins

println hudson.model.Hudson.instance.slaves.find { slave -> slave.name.equals("slave-1") }.getComputer().getJnlpMac()