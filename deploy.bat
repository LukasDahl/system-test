call docker image prune -f
call docker-compose up -d rabbitMq
timeout /t 30
call docker-compose up -d payments tokens accounts reports
timeout /t 20
call mvn test
