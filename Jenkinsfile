pipeline {
  agent any 
      environment {
	 EMAIL_TO = 'ayadimahdi04@gmail.com'
        registry='mahdi988/spring'
        registryCredential=''
        dockerImage=''
    }
	post{
         failure {
            emailext body: 'Check console output at $BUILD_URL to view the results. \n\n ${CHANGES} \n\n -------------------------------------------------- \n${BUILD_LOG, maxLines=100, escapeHtml=false}', 
                    to: "${EMAIL_TO}", 
                    subject: 'Build failed in Jenkins: $PROJECT_NAME - #$BUILD_NUMBER'
        }
        
    }
  stages {
    stage('git checkout') {
      steps {
        git branch : 'MahdiAyadii',
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
