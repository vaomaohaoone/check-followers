## Мониторинг подписчиков твиттер средствами elasticsearch-kibana
#### Запуск:  
docker-compose up -d  
#### Запуск индексирования:  
POST -x http://localhost:8080/run/{twitter-account-name}  
#### Просмотр результатов:  
В браузере по адресу http://0.0.0.0:5601  
