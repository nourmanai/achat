pipeline {
  agent any 
      environment {
        registry='mahdi988/spring'
        registryCredential=''
        dockerImage=''
    }
  stages {
    stage('git checkout') {
      steps {
        checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/wissembhk/devopsSpring']]])
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
  
 stage('Nexus') {
		 steps {
			sh 'mvn deploy -DskipTests'
			  }
			  }
  stage('building docker image')
        {
            steps
            {
                script
                {
                    dockerImage=docker.build registry+":$BUILD_NUMBER"
                  // sh 'docker build -t wissembhk/project .'
                }
            }
        }
        stage("Login to DockerHub") {
                steps{

                    sh 'docker login -u mahdi988 -p Mahdi1234@'
                }
        }
        stage('docker hub')
         {
             steps{
                 script{
                                      dockerImage.push()

                 }
             }
         }
       
        
        stage('image up')
        {
            steps{
                sh 'docker-compose up -d'
            }
        }
    
  
 }
}
