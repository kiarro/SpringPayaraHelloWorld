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

docker build -t kiarro/dragon:latest .

docker run -p 1000:5432 kiarro/dragon

### 

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

`npm run-script build`

from frontend folder

then move files from 'build' to 'src\main\webapp\WEB-INF\views'


## launch at web

### connection

ssh vm-user@itmo-lab.cosm-lab.science -p 4208

### pull docker

docker pull airhacks/glassfish

docker pull kiarro/dragon-payara

### run docker

docker run -p 1000:5432 -d kiarro/dragon

docker run -d kiarro/dragon-payara

docker run -p 9091:4848 -d airhacks/glassfish

### change pool

create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype javax.sql.DataSource --property user=dragon:password=dragon:DatabaseName=dragon_base:ServerName=localhost:port=1000 postgresql_pool

create-jdbc-resource --connectionpoolid postgresql_pool jdbc/postgrespool

ping-connection-pool postgresql_pool