pipeline {
  agent {
    node {
      label 'test'
    }

  }
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
      steps {
        sh 'mvn clean test'
      }
    }

  }
}