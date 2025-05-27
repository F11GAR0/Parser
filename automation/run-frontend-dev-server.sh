#!/bin/bash

docker build -t parser-frontend-dev:latest -f ./frontend/Dockerfile.dev frontend
docker run -it --rm --volume ./frontend/src:/app/src -p 5173:5173 parser-frontend-dev:latest