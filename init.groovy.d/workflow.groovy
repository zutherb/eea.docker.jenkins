import jenkins.model.Jenkins
import org.jenkinsci.plugins.workflow.job.WorkflowJob

import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition
import hudson.plugins.git.GitSCM

WorkflowJob job = Jenkins.instance.createProject(WorkflowJob, 'Jenkins Pipeline')

def definition = new CpsScmFlowDefinition(new GitSCM('https://github.com/zutherb/eea.docker.jenkins.git'), 'Jenkinsfile')
job.definition = definition