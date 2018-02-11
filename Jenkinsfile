pipeline {

    stages {
        stage('Build') {
            agent {
                label 'slave-1'
            }
            steps {
                echo 'Building..'
                sleep 10
            }
        }
        stage('Test') {
            agent {
                label 'slave-2'
            }
            steps {
                echo 'Testing..'
                sleep 10
            }
        }
    }
}