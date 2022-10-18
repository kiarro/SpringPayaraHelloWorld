FROM payara/server-full

COPY ./target/dragons.war /opt/deploy/
COPY ./target/dragons.war /opt/payara/deployments/

COPY ./target/dragons.war $DEPLOY_DIR

COPY ./jdbc/postgresql-42.3.6.jar /opt
COPY ./jdbc/postgresql-42.3.6.jar /opt/payara/appserver/glassfish/domains/domain1/lib

COPY ./payara-config/domain.xml /opt/payara/appserver/glassfish/domains/domain1/config

EXPOSE 4848 8000 8080