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
