call docker image prune -f
call docker-compose up -d rabbitMq
timeout /t 10
call docker-compose up -d payments tokens accounts reports
timeout /t 4
call mvn test
