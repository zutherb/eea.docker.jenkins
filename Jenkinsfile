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
        stage('Run test') {
            parallel {
                stage('Run on Slave 1') {
                    agent {
                        label 'slave-1'
                    }
                    steps {
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
                stage('Run on Slave 2') {
                    agent {
                        label 'slave-2'
                    }
                    steps {
                        unstash 'app'
                        sh '''
                            echo 'Testing..'
                            ls -lsah
                            cat test.txt
                            grep Test test.txt
                        '''
                        sleep 5
                    }
                }
            }
        }
    }
}