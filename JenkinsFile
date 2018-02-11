pipeline {

    stages {
        stage('Build') {
            agent 'slave-1'
            steps {
                sleep 10
                echo 'Building..'
            }
        }
        stage('Test') {
            agent 'slave-2'
            steps {
                sleep 10
                echo 'Testing..'
            }
        }
    }
}