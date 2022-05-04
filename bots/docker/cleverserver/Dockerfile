
FROM node:16
ENV NODE_ENV=production

WORKDIR /cleverserver
COPY ./ /cleverserver

RUN npm install --production


EXPOSE 8090
CMD [ "node", "server.js" ]
