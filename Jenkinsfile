pipeline {
    agent any

    tools {
        maven 'maven-3'
    }

    environment {
        SLACK_TOKEN = 'slack-token'
        JMETER_HOME = 'C:\\apache-jmeter-5.6.3\\bin'
    }

    stages {
        stage('Descargar proyecto') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/CardenasAlonso/03_PSW_JMeter.git'
            }
        }

        stage('Compilar') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Pruebas Unitarias') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Análisis con SonarQube Cloud') {
            steps {
                withCredentials([string(credentialsId: 'SONAR_TOKEN', variable: 'SONAR_TOKEN')]) {
                    bat """
                        mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar ^
                        -Dsonar.host.url=https://sonarcloud.io ^
                        -Dsonar.organization=cardenasalonso ^
                        -Dsonar.projectKey=CardenasAlonso_03_PSW_JMeter ^
                        -Dsonar.token=%SONAR_TOKEN%
                    """
                }
            }
        }

        stage('Pruebas de Carga (JMeter)') {
            steps {
                bat '%JMETER_HOME%\\jmeter.bat -n -t test-plan.jmx -l resultados.jtl -e -o reporte-jmeter'
            }
        }
    }

    post {
        success {
            slackSend(
                channel: '#notificaciones-jmeter',
                color: 'good',
                message: "Build SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER} - El Pipeline finalizó exitosamente todas las etapas. (<${env.BUILD_URL}|Ver build>)"
            )
        }
        failure {
            slackSend(
                channel: '#notificaciones-jmeter',
                color: 'danger',
                message: "Build FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER} - El Pipeline presentó errores durante la ejecución. Revisar logs: ${env.BUILD_URL}console"
            )
        }
    }
}