pipeline {
    agent any
     tools {
        maven 'maven'
    }
 stages {
         stage("cleaning code") {
            steps {
                script {
                   sh 'mvn -B -DskipTests clean '
                }
            }
        }
         stage("building") {
            steps {
                script {
                   sh 'mvn -B -DskipTests clean package'
                }
            }
         }
            stage("testing") {
            steps {
                script {
                   sh 'mvn test'
                }
            }
        }
        stage("code QualityCheck Sonar") {
            steps {
                script {
             sh " mvn sonar:sonar -Dsonar.projectKey=anis -Dsonar.host.url=http://192.168.80.205:9000   -Dsonar.login=d63a5f2e115c070867959b12523092045c70959a"

                }
            }
        }
 }
   
}

