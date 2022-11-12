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
             sh " mvn sonar:sonar -Dsonar.projectKey=anis -Dsonar.host.url=http://192.168.80.205:9000   -Dsonar.login=b7a1aeb30ea7455271629bb919ba14f7dd7ce678"

                }
            }
        }
     
     
         stage("publish to nexus") {
            steps {
                script {
                configFileProvider([configFile(fileId: 'anis', variable: 'setting')]) {
                    sh 'mvn  -B -DskipTests deploy -s $setting'

}                }
            }
        }
     
 }
   
}

