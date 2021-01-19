#!/bin/bash
set -e

docker image prune -f

docker-compose up -d rabbitMq

sleep 25s

docker-compose up -d payments tokens accounts reports

sleep 20s

mvn test
