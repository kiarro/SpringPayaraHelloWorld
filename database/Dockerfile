# postgres
FROM postgres

EXPOSE 5432

ENV POSTGRES_USER dragon
ENV POSTGRES_PASSWORD dragon
ENV POSTGRES_DB dragon
# copy sql script for creating tables
COPY CreateTables.sql /docker-entrypoint-initdb.d/
COPY CreateTables.sql /opt
