node {
    
    stage('Checkout'){
        
        echo "Checking out Source Code Management"
        checkout scm
        echo "Check out successful"
    }
    
    stage('Making a Directory inside the folder'){
        sshagent(['QA_server_key']){
       sh("ssh -oStrictHostKeyChecking=no ubuntu@35.183.0.6")
        sh 'ssh ubuntu@35.183.0.6 sudo mkdir -p /var/www/html/QualityAssurance/DEMO'
}
        
    }

stage('changing the permission '){

    sshagent(['QA_server_key']){
   sh("ssh -oStrictHostKeyChecking=no ubuntu@35.183.0.6")
    sh 'ssh ubuntu@35.183.0.6 sudo chown -R ubuntu:ubuntu /var/www/html/QualityAssurance/*'
}
    
}

stage('Deploying the project to the QA Server'){

    sshagent(['QA_server_key']){
    sh("ssh -oStrictHostKeyChecking=no ubuntu@35.183.0.6")
    sh 'rsync -avzhe ssh . ubuntu@35.183.0.6:/var/www/html/QualityAssurance/DEMO/'
}
}

stage ('Running the project'){
    sshagent(['QA_server_key']){
    sh("ssh -oStrictHostKeyChecking=no ubuntu@35.183.0.6")
    sh 'ssh ubuntu@35.183.0.6 cd /var/www/html/QualityAssurance/DEMO'
   

}
}



stage('Building the docker image'){
                   sshagent(['QA_server_key']){
            sh("ssh -oStrictHostKeyChecking=no ubuntu@35.183.0.6")
    sh 'ssh ubuntu@35.183.0.6 sudo docker build /var/www/html/QualityAssurance/DEMO -t demoqaimage'
}     
                        
 }
stage('Running the docker image'){
                  sshagent(['QA_server_key']){
                 sh("ssh -oStrictHostKeyChecking=no ubuntu@35.183.0.6")
    sh 'ssh ubuntu@35.183.0.6 sudo docker run demoqaimage'
}           
                       
                   }



}