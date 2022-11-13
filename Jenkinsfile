pipeline {
  agent any 
  stages {
    stage('git checkout') {
      steps {
        git branch : 'MahdiAyadi',
        url: 'https://github.com/nourmanai/achat.git',
        credentialsId: '34978384-1ef1-4c5e-acc9-ef928a954625';
        echo 'checkout stage'
           }
  }
    
  
    stage ('MNV BUILD') {
      steps {
        sh 'mvn clean package'
        echo 'Build stage done'
      }
    }
  
    stage ('MVN COMPILE') {
      steps {
        sh 'mvn compile'
        echo 'compile stage done'

      }
    }
     stage ('JUNIT+MOCK') {
      steps {
        sh 'mvn test'
      }
    }
    
     stage ('STATIC TEST WITH SONAR') {
       steps {
       withSonarQubeEnv('sonarqube-8.9.7-community') { 
                sh 'mvn sonar:sonar'
        }
      }
    }
  
   stage('DeployInNexus') {
            steps {
            bat 'mvn clean package deploy:deploy-file -DgroupId=com.esprit.examen -DartifactId=tpAchatProject -Dversion=1.0 -DgeneratePom=true-Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-releases/ -Dfile=target/timesheet-1.0.jar'
            }
            
        }
    
  
 }
}
