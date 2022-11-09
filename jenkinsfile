  pipeline{
    agent any
    stages{
       stage('Get git'){
            steps{
                git 'https://github.com/nourmanai/achat.git'
                sh '''
            # Content omitted
            git checkout chaima_branch
            # Content omitted
            '''
            }
        }    
       
       
    }
}
