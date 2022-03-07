def TEST_CASE_VARIABLE

import groovy.json.JsonSlurper

pipeline {
  agent any
  stages {
    stage("Package") {
/*       when {
        branch 'master'
      } */
      steps {
        bat 'mvn clean package'
      }
    }

    stage(TCs) {
    /*   when {
        branch 'master'
      } */
      options {
        timeout(time: 60, unit: 'SECONDS')
      }
      input {
        message "Ingresar Test Case"
        ok "Next"
        parameters {
            string(name: 'TCs', defaultValue: '@prueba1', description: 'Ingresa aca:')
        }
      }
      steps {
        echo "Hello, ${TCs}, nice to meet you."
        script {
          TEST_CASE_VARIABLE = "${TCs}"
        }
      }
    }
    stage(Test) {
   /*    when {
        branch 'master'
      } */
      steps {
        echo "Hello, ${TEST_CASE_VARIABLE}, nice to meet you again."
        script {
          if("${TEST_CASE_VARIABLE}".trim().toUpperCase() == 'all'.toUpperCase()) {
            bat "mvn clean verify"
          } else {
            bat "mvn clean verify -Dcucumber.filter.tags=\"${TEST_CASE_VARIABLE}\""
          }
        }
      }
    }
  }
  post{
     always{
         archiveArtifacts artifacts: "target/site/serenity/", fingerprint: true
          }
       }
}