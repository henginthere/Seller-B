FROM node:lts-alpine3.15

WORKDIR /workspace/sellerB/FE/sellerb_fe

# copy dependencies
COPY package.json /workspace/sellerB/FE/sellerb_fe
COPY package-lock.json /workspace/sellerB/FE/sellerb_fe

RUN npm install

EXPOSE 3000

CMD npm start --port 3000
# CMD HTTPS=true npm start --port 3000