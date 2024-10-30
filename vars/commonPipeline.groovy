def call() {
    echo "Running common steps..."
    // Common steps here, for example:
    //checkout scm
    sh 'echo Running unit tests'
    sh 'echo Building the application'
    // Add any other common logic here
}
