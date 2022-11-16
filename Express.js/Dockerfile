FROM node:latest

# copy all files into workdir
WORKDIR /Express.js
COPY / /Express.js/

# get typescript configurations
RUN npm install typescript -g
RUN npm install ts-node -g
RUN npm install express
RUN npm install @types/express
RUN npm install node-fetch

# third argument passes the service name of the spring api in docker-compose
ENTRYPOINT ts-node-esm ./src/main.mts spring-boot