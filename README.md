# Prodemics Backend with Spring

Online school system backend with Spring.


## Deploy to Heroku

1. Create system.properties file with lock in JRE major version.
2. Push to Github.
3. Create new app in Heroku.
4. Connect the Github repo.
5. Deploy main branch.

Heroku finds out using pom.xml that it's a Spring boot app and so uses the JAR file run command with environment variable port passed in the command. Reference: <a href="https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku">https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku</a> for details.