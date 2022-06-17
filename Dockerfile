FROM airhacks/glassfish
COPY ./target/dragons.war ${DEPLOYMENT_DIR}
