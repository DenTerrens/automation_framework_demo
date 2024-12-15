pipeline {
    agent any
    environment {
        BROWSER = 'chrome'
    }
    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your repository
                git branch: 'master', url: 'https://github.com/DenTerrens/automation_framework_demo'
            }
        }

        stage('Build') {
            steps {
                // Build project using Maven
                // this will trigger the tests automatically
                bat 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                // Run the Selenium tests using TestNG
                bat 'mvn test'
            }
        }

        stage('Allure Report') {
            steps {
                // Generate Allure test reports
                allure includeProperties: false, results: [[path: 'target/allure-results']]
            }
        }

        stage('Deploy') {
            steps {
                // (Optional) Deploy application if needed
                echo "Deployment stage"
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        success {
            echo 'Tests passed successfully!'
        }
        failure {
            echo 'Tests failed.'
        }
    }
}
