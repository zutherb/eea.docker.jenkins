println 'Configuring Kubernetes plugin'

import org.csanchez.jenkins.plugins.kubernetes.*
import jenkins.model.*

def jenkins = Jenkins.getInstance()

def kubernetes = new KubernetesCloud('kubernetes')
kubernetes.setSkipTlsVerify(true)
kubernetes.setCredentialsId('none')
kubernetes.setNamespace('jenkins')
kubernetes.setJenkinsUrl('http://jenkins-ui.jenkins.svc.cluster.local:8080')
kubernetes.setCredentialsId('none')
kubernetes.setContainerCapStr('10')
kubernetes.setRetentionTimeout(0)
kubernetes.setConnectTimeout(0)
kubernetes.setServerUrl('https://kubernetes.default.svc.cluster.local')
kubernetes.setJenkinsTunnel('jenkins-discovery.jenkins.svc.cluster.local:50000')
kubernetes.setMaxRequestsPerHostStr('32')

def container = new ContainerTemplate('jnlp')
container.setName('jnlp')
container.setImage('jenkinsci/jnlp-slave')
container.setCommand('')
container.setArgs('')
container.setTtyEnabled(true)

def slave_1 = new PodTemplate()
slave_1.setName('jenkins-slave')
slave_1.setNamespace('jenkins')
slave_1.setLabel('slave-1')
slave_1.setContainers([container])

def slave_2 = new PodTemplate()
slave_2.setLabel('slave-2')

kubernetes.addTemplate(slave_1)
kubernetes.addTemplate(slave_2)

jenkins.clouds.replace(kubernetes)
jenkins.save()