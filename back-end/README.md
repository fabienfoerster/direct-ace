#Postgresql database
```
cd database
sudo -u postgres psql postgres < ./SensorsData.sql
cd ..
```

#Launching everything
```
mvn clean install

cd data-processor
mvn exec:java

cd ..

cd message-queue
mvn activemq:run

cd ..
```
