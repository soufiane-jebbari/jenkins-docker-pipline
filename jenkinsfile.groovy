pipline {
    agent any
    tools {
        maven "maven-3.8.4"
    }
    stages {
        stage("building the app ..."){ 
            steps {
                script {
                    echo "building the app"
                    sh "mvn package"
                }
            }

        }
    
        stage("building docker image"){ 
            steps {
                script {
                    echo "building the app image"
                    withCredentials([usernamePassword(credentials: "docker_hub_cred" , passwordVariable: "pass" , usernameVariable: "user")]) {
                        sh "docker build -t soufianejebbari/demo-app:jma-1.0 ."
                        sh "echo ${pass} | docker login -u ${user} --password-stdin"
                        sh "docker push"
                    }
                    sh "docker build "
                }
        stage("deploying  the app ..."){ 
            steps {
                script {
                    echo "deploying  the app"
                   
                }
            }

        }

        }
    }
    }
    
}