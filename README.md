# SpringPayaraHelloWorld

WAR file is [here](https://github.com/kiarro/SpringPayaraHelloWorld/blob/master/target/demo.war)

## Postgres database

[docker](https://hub.docker.com/r/kiarro/dragon)

## Fixes

- added jdbc driver to payara
- created jdbc connection pool in payara
- created jdbc resource in payara
- connected to created jdbc source from code

## docker

### database

docker build -t kiarro/dragon-base:latest .

docker run -p 1000:5432 kiarro/dragon-base

### server

docker build -t kiarro/dragon:latest .

## frontend

### installation

run command

```cmd
npx create-react-app frontend
cd frontend
npm install --save bootstrap@5.1 react-cookie@4.1.1 react-router-dom@5.3.0 reactstrap@8.10.0
```

then copy your project to frontend

### build

build using

`npm run-script compile`

from frontend folder


## launch at web

### connection

ssh vm-user@itmo-lab.cosm-lab.science -p 4208

RnQGUx3ye4cRy8bk

### pull docker

docker pull kiarro/dragon-base:1710

docker pull kiarro/dragon:1710

### run docker

```bash
# docker run -p 1000:5432 -d kiarro/dragon

# docker run -d kiarro/dragon-payara

# docker run -p 9091:4848 -d airhacks/glassfish

docker-compose -p <name> up 
docker-compose -p dragons up 


docker container ls
```

### go inside container

```bash
docker exec -it dragons_my-payara-project_1 /bin/bash
```

deploy app using is asadmin

```bash
deploy /opt/payara/deployments/dragons.war
```

### change pool

create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype javax.sql.DataSource --property user=dragon:password=dragon:DatabaseName=dragon_base:ServerName=localhost:port=1000 postgresql_pool

create-jdbc-resource --connectionpoolid postgresql_pool jdbc/postgrespool

ping-connection-pool postgresql_pool


### deploy

asadmin deploy --contextroot /api dragons.war
asadmin deploy --contextroot / dragon-front.war

### server url

```
http://labvm-42-08.itmo-lab.cosm-lab.science:8080
```