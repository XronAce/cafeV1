pipeline {
    agent any

    environment {
        REGISTRY = 'user18.azurecr.io'
        IMAGE_NAME_GATEWAY = 'gateway'
        IMAGE_NAME_ORDER = 'order'
        IMAGE_NAME_INVENTORY = 'inventory'
        IMAGE_NAME_BEVERAGE = 'beverage'
        IMAGE_NAME_NOTIFICATION = 'notification'
        AKS_CLUSTER = 'user18-aks'
        RESOURCE_GROUP = 'user18-rsrcgrp'
        AKS_NAMESPACE = 'cafe'
        AZURE_CREDENTIALS_ID = 'Azure-Cred'
        TENANT_ID = 'f46af6a3-e73f-4ab2-a1f7-f33919eda5ac'
    }

    stages {
        stage('Clone Repository') {
            steps {
                checkout scm
            }
        }
        
        stage('Maven Build') {
            steps {
                withMaven(maven: 'Maven') {
                    script {
                        parallel(
                            'gateway': {
                                dir('gateway') {
                                    sh 'mvn clean package -DskipTests'
                                }
                            },
                            'order': {
                                dir('order') {
                                    sh 'mvn clean package -DskipTests'
                                }
                            },
                            'inventory': {
                                dir('inventory') {
                                    sh 'mvn clean package -DskipTests'
                                }
                            },
                            'notification': {
                                dir('notification') {
                                    sh 'mvn clean package -DskipTests'
                                }
                            },
                            'beverage': {
                                dir('beverage') {
                                    sh 'mvn clean package -DskipTests'
                                }
                            }
                        )
                    }
                }
            }
        }
        
        stage('Docker Build') {
            steps {
                script {
                    parallel(
                        'gateway': {
                            dir('gateway') {
                                gatewayImage = docker.build("${REGISTRY}/${IMAGE_NAME_GATEWAY}:v${env.BUILD_NUMBER}")
                            }
                        },
                        'order': {
                            dir('order') {
                                orderImage = docker.build("${REGISTRY}/${IMAGE_NAME_ORDER}:v${env.BUILD_NUMBER}")
                            }
                        },
                        'inventory': {
                            dir('inventory') {
                                inventoryImage = docker.build("${REGISTRY}/${IMAGE_NAME_INVENTORY}:v${env.BUILD_NUMBER}")
                            }
                        },
                        'notification': {
                            dir('notification') {
                                notificationImage = docker.build("${REGISTRY}/${IMAGE_NAME_NOTIFICATION}:v${env.BUILD_NUMBER}")
                            }
                        },
                        'beverage': {
                            dir('beverage') {
                                beverageImage = docker.build("${REGISTRY}/${IMAGE_NAME_BEVERAGE}:v${env.BUILD_NUMBER}")
                            }
                        }
                    )
                }
            }
        }
        
        stage('Azure Login') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: env.AZURE_CREDENTIALS_ID, usernameVariable: 'AZURE_CLIENT_ID', passwordVariable: 'AZURE_CLIENT_SECRET')]) {
                        sh 'az login --service-principal -u $AZURE_CLIENT_ID -p $AZURE_CLIENT_SECRET --tenant ${TENANT_ID}'
                    }
                }
            }
        }
        
        stage('Push to ACR') {
            steps {
                script {
                    sh "az acr login --name ${REGISTRY.split('\\.')[0]}"
                    sh "docker push ${REGISTRY}/${IMAGE_NAME_GATEWAY}:v${env.BUILD_NUMBER}"
                    sh "docker push ${REGISTRY}/${IMAGE_NAME_ORDER}:v${env.BUILD_NUMBER}"
                    sh "docker push ${REGISTRY}/${IMAGE_NAME_BEVERAGE}:v${env.BUILD_NUMBER}"
                    sh "docker push ${REGISTRY}/${IMAGE_NAME_INVENTORY}:v${env.BUILD_NUMBER}"
                    sh "docker push ${REGISTRY}/${IMAGE_NAME_NOTIFICATION}:v${env.BUILD_NUMBER}"
                }
            }
        }
        
        stage('CleanUp Images') {
            steps {
                sh """
                docker rmi ${REGISTRY}/${IMAGE_NAME_GATEWAY}:v$BUILD_NUMBER
                docker rmi ${REGISTRY}/${IMAGE_NAME_ORDER}:v$BUILD_NUMBER
                docker rmi ${REGISTRY}/${IMAGE_NAME_BEVERAGE}:v$BUILD_NUMBER
                docker rmi ${REGISTRY}/${IMAGE_NAME_INVENTORY}:v$BUILD_NUMBER
                docker rmi ${REGISTRY}/${IMAGE_NAME_NOTIFICATION}:v$BUILD_NUMBER
                """
            }
        }
        
        stage('Deploy to AKS') {
            steps {
                script {
                    sh "az aks get-credentials --resource-group ${RESOURCE_GROUP} --name ${AKS_CLUSTER}"
                    parallel(
                        'gateway': {
                            dir('gateway') {
                                sh """
                                sed 's/latest/v${env.BUILD_ID}/g' kubernetes/deployment.yaml > output.yaml
                                cat output.yaml
                                kubectl apply -f (istioctl kube-inject -f output.yaml) -n ${AKS_NAMESPACE}
                                kubectl apply -f kubernetes/service.yaml -n ${AKS_NAMESPACE}
                                rm output.yaml
                                """
                            }
                        },
                        'order': {
                            dir('order') {
                                sh """
                                sed 's/latest/v${env.BUILD_ID}/g' kubernetes/deployment.yaml > output.yaml
                                cat output.yaml
                                kubectl apply -f (istioctl kube-inject -f output.yaml) -n ${AKS_NAMESPACE}
                                kubectl apply -f kubernetes/service.yaml -n ${AKS_NAMESPACE}
                                rm output.yaml
                                """
                            }
                        },
                        'inventory': {
                            dir('inventory') {
                                sh """
                                sed 's/latest/v${env.BUILD_ID}/g' kubernetes/deployment.yaml > output.yaml
                                cat output.yaml
                                kubectl apply -f (istioctl kube-inject -f output.yaml) -n ${AKS_NAMESPACE}
                                kubectl apply -f kubernetes/service.yaml -n ${AKS_NAMESPACE}
                                rm output.yaml
                                """
                            }
                        },
                        'notification': {
                            dir('notification') {
                                sh """
                                sed 's/latest/v${env.BUILD_ID}/g' kubernetes/deployment.yaml > output.yaml
                                cat output.yaml
                                kubectl apply -f (istioctl kube-inject -f output.yaml) -n ${AKS_NAMESPACE}
                                kubectl apply -f kubernetes/service.yaml -n ${AKS_NAMESPACE}
                                rm output.yaml
                                """
                            }
                        },
                        'beverage': {
                            dir('beverage') {
                                sh """
                                sed 's/latest/v${env.BUILD_ID}/g' kubernetes/deployment.yaml > output.yaml
                                cat output.yaml
                                kubectl apply -f (istioctl kube-inject -f output.yaml) -n ${AKS_NAMESPACE}
                                kubectl apply -f kubernetes/service.yaml -n ${AKS_NAMESPACE}
                                rm output.yaml
                                """
                            }
                        }
                    )
                }
            }
        }
    }
}