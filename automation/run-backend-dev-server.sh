#!/bin/bash

docker build -t parser-backend-dev:latest -f ./backend/Dockerfile.dev backend
docker run -it --rm --volume ./backend/src:/app/src -p 8080:8080 parser-backend-dev:latest