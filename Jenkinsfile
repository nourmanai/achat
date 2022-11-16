pipeline {
    agent any
     tools {
        maven 'maven'
    }
 stages {
         stage("get code from git"){
            steps{
                script{
                    checkout([$class: 'GitSCM', branches: [[name: '*/anis_branch']], extensions: [], userRemoteConfigs: [[credentialsId: 'git', url: 'https://github.com/nourmanai/achat.git']]])
                }
            }
         }
                
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
                   sh 'mvn -B -DskipTests package'
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
             sh " mvn sonar:sonar -Dsonar.projectKey=anis -Dsonar.host.url=http://192.168.250.205:9000   -Dsonar.login=b7a1aeb30ea7455271629bb919ba14f7dd7ce678"

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
                 stage("Create Docker Image") {
            steps {
                script {
                   sh 'docker build -t hajali98/spring:latest .'
                }
            }
        }
        stage("pushing image to docker hub") { 
             steps { 
                 script { 
                    withCredentials([usernamePassword(credentialsId: 'Docker_hub', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh "echo $PASS | docker login -u $USER --password-stdin"
                    sh 'docker push hajali98/spring:latest'
                        
                    }
                 } 
             } 
         }
                  stage("Docker compose") {
            steps {
                script {
                   sh 'docker compose up -d'
                }
            }
         }
		 
     
 }
   
}

