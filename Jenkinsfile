pipeline {
    agent none
    stages {
        stage('Build') {
            agent {
                label 'slave-1'
            }
            steps {
                echo 'Building..'
                writeFile file: "test.txt", text: "Test"
                sleep 10
                stash name: 'app'
            }
        }
        stage('Test') {
            parallel slave2: {
                node('slave-2') {
                    unstash 'app'
                    sh '''
                        echo 'Testing..'
                        ls -lsah
                        cat test.txt
                        grep Test test.txt
                    '''
                    sleep 15
                }
            },
            slave1: {
                node('slave-1') {
                    unstash 'app'
                    sh '''
                        echo 'Testing..'
                        ls -lsah
                        cat test.txt
                        grep Test test.txt
                        '''
                    sleep 10
                }
            }
        }
    }
}