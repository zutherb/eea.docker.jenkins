import jenkins.model.*

def plugins = ['authentication-tokens',
               'build-monitor-plugin',
               'build-name-setter',
               'blue-ocean',
               'build-with-parameters',
               'cloudbees-folder',
               'credentials',
               'docker-workflow',
               'durable-task',
               'git',
               'github',
               'gravatar',
               'greenballs',
               'groovy',
               'groovy-postbuild',
               'job-dsl',
               'ldap',
               'matrix-auth',
               'matrix-project',
               'parameterized-trigger',
               'pipeline-utility-steps',
               'project-description-setter',
               'rebuild',
               'ssh-agent',
               'ssh-credentials',
               'timestamper',
               'workflow-aggregator',
               'workflow-cps',
               'workflow-multibranch']

def instance = Jenkins.getInstance()
def pm = instance.getPluginManager()
def uc = instance.getUpdateCenter()
def installed = false

println("Installing plugins: " + plugins)
        plugins . each {
    if (!pm.getPlugin(it)) {
        def plugin = uc.getPlugin(it)
        if (plugin) {
            plugin.deploy()
            installed = true
        }
    }
}

        instance . save()
if (installed)
    instance.doSafeRestart()