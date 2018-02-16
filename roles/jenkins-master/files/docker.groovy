import com.nirima.jenkins.plugins.docker.DockerCloud
import com.nirima.jenkins.plugins.docker.DockerTemplate
import com.nirima.jenkins.plugins.docker.DockerTemplateBase
import com.nirima.jenkins.plugins.docker.launcher.AttachedDockerComputerLauncher
import io.jenkins.docker.connector.DockerComputerAttachConnector
import jenkins.model.Jenkins

// parameters
def dockerTemplateBaseParameters = [
        bindAllPorts:       false,
        bindPorts:          '',
        cpuShares:          null,
        dnsString:          '',
        dockerCommand:      '',
        environmentsString: '',
        extraHostsString:   '',
        hostname:           '',
        image:              'jenkinsci/slave:latest',
        macAddress:         '',
        memoryLimit:        null,
        memorySwap:         null,
        network:            '',
        privileged:         false,
        pullCredentialsId:  '',
        tty:                true,
        volumesFromString:  '',
        volumesString:      ''
]

def DockerTemplateParameters = [
        instanceCapStr: '4',
        labelString:    'docker.local.jenkins.slave',
        remoteFs:       ''
]

def dockerCloudParameters = [
        connectTimeout:   3,
        containerCapStr:  '4',
        credentialsId:    '',
        dockerHostname:   '',
        name:             'docker.local',
        readTimeout:      60,
        serverUrl:        'tcp://10.0.0.2:2375',
        version:          ''
]

// https://github.com/jenkinsci/docker-plugin/blob/docker-plugin-1.1.2/src/main/java/com/nirima/jenkins/plugins/docker/DockerTemplateBase.java
DockerTemplateBase dockerTemplateBase = new DockerTemplateBase(
        dockerTemplateBaseParameters.image,
        dockerTemplateBaseParameters.pullCredentialsId,
        dockerTemplateBaseParameters.dnsString,
        dockerTemplateBaseParameters.network,
        dockerTemplateBaseParameters.dockerCommand,
        dockerTemplateBaseParameters.volumesString,
        dockerTemplateBaseParameters.volumesFromString,
        dockerTemplateBaseParameters.environmentsString,
        dockerTemplateBaseParameters.hostname,
        dockerTemplateBaseParameters.memoryLimit,
        dockerTemplateBaseParameters.memorySwap,
        dockerTemplateBaseParameters.cpuShares,
        dockerTemplateBaseParameters.bindPorts,
        dockerTemplateBaseParameters.bindAllPorts,
        dockerTemplateBaseParameters.privileged,
        dockerTemplateBaseParameters.tty,
        dockerTemplateBaseParameters.macAddress,
        dockerTemplateBaseParameters.extraHostsString
)

// get Jenkins instance
Jenkins jenkins = Jenkins.getInstance()

// add cloud configuration to Jenkins
jenkins.clouds.add(new DockerCloud(
        dockerCloudParameters.name,
        [new DockerTemplate(
                dockerTemplateBase,
                new DockerComputerAttachConnector(),
                "slave-1",
                DockerTemplateParameters.remoteFs,
                DockerTemplateParameters.instanceCapStr
        ),
         new DockerTemplate(
                 dockerTemplateBase,
                 new DockerComputerAttachConnector(),
                 "slave-2",
                 DockerTemplateParameters.remoteFs,
                 DockerTemplateParameters.instanceCapStr
         )],
        dockerCloudParameters.serverUrl,
        dockerCloudParameters.containerCapStr,
        dockerCloudParameters.connectTimeout,
        dockerCloudParameters.readTimeout,
        dockerCloudParameters.credentialsId,
        dockerCloudParameters.version,
        dockerCloudParameters.dockerHostname
))

// save current Jenkins state to disk
jenkins.save()