# postgres
FROM postgres

EXPOSE 5432

ENV POSTGRES_USER dragon
ENV POSTGRES_PASSWORD dragon
ENV POSTGRES_DB dragon_base
# copy sql script for creating tables
COPY CreateTables.sql /docker-entrypoint-initdb.d/

