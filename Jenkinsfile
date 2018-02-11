pipeline {
    agent none
    stages {
        stage('Build') {
            agent {
                label 'slave-1'
            }
            steps {
                echo 'Building..'
                echo 'Test' &> test.txt
                sleep 10
                stash name: 'app'
            }
        }
        stage('Test') {
            agent {
                label 'slave-2'
            }
            steps {
                unstash 'app'
                echo 'Testing..'
                grep Test test.txt
                sleep 10
            }
        }
    }
}