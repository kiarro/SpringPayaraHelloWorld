FROM airhacks/glassfish

COPY ./target/dragons.war /start_app

COPY ./target/dragons.war $DEPLOY_DIR

COPY ./jdbc/postgresql-42.3.6.jar /opt/glassfish5/glassfish/domains/domain1/lib

# RUN echo 'asadmin create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype javax.sql.DataSource --property user=dragon:password=dragon:DatabaseName=dragon_base:ServerName=localhost:port=1000 postgresql_pool' \
#  > "${POSTBOOT_COMMANDS}"
# RUN echo 'asadmin create-jdbc-resource --connectionpoolid postgresql_pool jdbc/postgrespool' \
#  > "${POSTBOOT_COMMANDS}"

# ENTRYPOINT /opt/glassfish5/glassfish/generate_deploy_commands.sh && \
#   echo 'create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype javax.sql.DataSource --property user=dragon:password=dragon:DatabaseName=dragon_base:ServerName=localhost:port=1000 postgresql_pool' > mycommands.asadmin && \
#   echo 'create-jdbc-resource --connectionpoolid postgresql_pool jdbc/postgrespool' > mycommands.asadmin && \
#   cat ${DEPLOY_COMMANDS} >> mycommands.asadmin && \
#   /opt/glassfish5/glassfish/bin/asadmin start-domain -v --postbootcommandfile mycommands.asadmin ${PAYARA_DOMAIN}

# RUN echo 'create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype javax.sql.DataSource --property user=dragon:password=dragon:DatabaseName=dragon_base:ServerName=localhost:port=1000 postgresql_pool' > /opt/payara/config/post-boot-commands.asadmin
# RUN echo 'asadmin create-jdbc-resource --connectionpoolid postgresql_pool jdbc/postgrespool' > "$POSTBOOT_COMMANDS"

EXPOSE 4848 8000 8080 1000 5432