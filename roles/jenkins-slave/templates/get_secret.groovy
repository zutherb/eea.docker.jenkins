import hudson.slaves.*

println hudson.model.Hudson.instance.slaves.find { slave -> slave.name.equals("{{ slave_name }}") }.getComputer().getJnlpMac()