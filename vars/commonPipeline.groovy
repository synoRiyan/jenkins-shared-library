def call() {
    echo "Running common steps..."
    // Common steps here, for example:
    //checkout scm
    sh 'echo Running unit tests'
    sh 'echo Building the application'
    // Add any other common logic here
}

def sendTeamsNotification(String message) {
    office365ConnectorSend message: message,
        webhookUrl: 'https://synoverge.webhook.office.com/webhookb2/840c24c2-ce9f-479f-84be-33e0360b5af3@9afce432-20b4-4624-8400-1858002e5ae2/IncomingWebhook/0fe880ff23284ff3890c13519ca626b5/7e27b15c-b5c6-4e03-8118-3523b8984747/V2Yw-OhGJ3YQ4P5Ji2kz6FdqsURpWEfP0mOqxGFxQFJnw1'
}

