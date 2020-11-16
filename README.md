# spring_session
In Order to run this application a run.bat has been provided.
This run.bat will maven install dependancies and run.

For the run.bat to work, you must add a variable to your system env:
JAVA_HOME must point to your java jdk directory.

If the run.bat autocloses on start up, the JAVA_HOME system env is not configured.

Once the run.bat has successfully started spring: navigate to localhost:8080

Alternativly, this project can be ran from an IDE by running SpringSessionApplication as main.
Ensure that the provided pom.xml is imported properly

Accounts: 
admin@admin.com password: qwerty role: admin
manager@manager.com password:qwerty role: manager
worker@worker.com password:qwerty role: worker
worker2@worker.com password:qwerty role: worker
