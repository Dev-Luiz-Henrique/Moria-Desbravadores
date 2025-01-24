# Use uma imagem do Node.js para construir o app React
FROM node:18-alpine AS build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

# Use o servidor Nginx para servir os arquivos estáticos
FROM nginx:1.23.3-alpine
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
