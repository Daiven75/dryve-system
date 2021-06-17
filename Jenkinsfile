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
            tool 'maven-jenkins'
            sh 'mvn clean test'
          }
        }

        stage('Deploy') {
          steps {
            warnError(catchInterruptions: true, message: 'Não foi possível realizar a integração') {
              sh 'mvn clean tests'
            }

          }
        }

      }
    }

  }
}