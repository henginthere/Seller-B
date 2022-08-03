FROM node:lts-alpine3.15

WORKDIR /workspace

# copy dependencies
COPY package.json /workspace/
COPY package-lock.json /workspace/

RUN npm install

EXPOSE 3000

CMD npm start --port 3000
# CMD HTTPS=true npm start --port 3000