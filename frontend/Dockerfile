# Stage 1: Build Angular app
FROM node:24-alpine as angular

WORKDIR /app

COPY package*.json ./
RUN npm install -g @angular/cli && npm install

COPY . .
RUN ng build --configuration=production --output-path=dist/frontend

# Stage 2: Serve with Nginx
FROM nginx:alpine

# Remove default Nginx static files
RUN rm -rf /usr/share/nginx/html/*

# Copy Angular build
#COPY --from=angular /app/dist/frontend/browser/* /usr/share/nginx/html/
COPY --from=angular /app/dist/frontend/browser/ /usr/share/nginx/html/


# Copy simplified Nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
