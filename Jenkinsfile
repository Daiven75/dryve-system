pipeline {
  agent any
  stages {
    stage('sleep') {
      parallel {
        stage('sleep') {
          steps {
            sleep 5
          }
        }

        stage('message') {
          steps {
            echo 'Hello World'
          }
        }

      }
    }

    stage('tests') {
      parallel {
        stage('tests') {
          steps {
            sh 'mvn clean test'
          }
        }

        stage('Deploy') {
          steps {
            warnError(catchInterruptions: true, message: 'N�o foi poss�vel realizar a integra��o') {
              sh 'mvn clean tests'
            }

          }
        }

      }
    }

  }
}