#!/bin/bash

curl -X POST http://localhost:8080/api/parser -H "Content-Type: application/json" -d '{"urlEncoded": "https://www.google.com"}'