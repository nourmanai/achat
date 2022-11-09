  pipeline{
    agent any
 
    stages{
       stage('Git'){
            steps{
                git 'https://github.com/nourmanai/achat.git'
                
            }
        }
       stage('maven build'){
            steps{
                sh 'mvn clean package'
                
            }
        }   
      stage('maven test'){
            steps{
               
                                sh 'mvn clean package'

            }
        }   
       
       
    }
}
