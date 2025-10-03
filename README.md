### AMAPI Test Endpoints
This project provides test endpoints for the Android Management API (AMAPI).
🚀 Setup Instructions
### 1. Clone the repository
First, clone the repository to your local machine and navigate into the project directory.
git clone [https://github.com/CHHORNSeyha88/amapi-test-endpoints.git](https://github.com/CHHORNSeyha88/amapi-test-endpoints.git)
cd amapi-test-endpoints


### 2. Add Google Cloud Credentials
This project requires a Google Cloud service account key to authenticate and function correctly. 

Because credentials are sensitive information, the key file is not included in the repository and is specified in the .gitignore file.

### Steps:
Navigate to the [Google Cloud Console](https://console.cloud.google.com/projectselector2/iam-admin/serviceaccounts?supportedpurview=project) to create or select a project.

Create a new service account and download the corresponding JSON private key.
Place the downloaded JSON file in the project directory and name it 

gcp-credentials.json at the following path:

```bash
src/main/resources/gcp-credentials.json
```

### ⚠️ Important: Do not commit the g-credentials.json file to version control.
### ▶️ Build & Run
Once the setup is complete, you can build and run the application using the included Gradle wrapper.

# Build the project
```bash
./gradlew build
```

# Run the Spring Boot application
```bash
./gradlew bootRun
```


💡 Optional: IDE Tips
If you are using IntelliJ IDEA, ensure that the src/main/resources directory is marked as a Resources Root. This is typically configured automatically, but you can set it manually by right-clicking the directory if needed.
