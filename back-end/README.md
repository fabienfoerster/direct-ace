#Postgresql database
```
cd database
sudo -u postgres psql postgres < ./SensorsData.sql
cd ..
```

#Launching everything
```
mvn clean install

cd message-queue
mvn activemq:run

cd ..

cd data-processor
mvn exec:java

cd ..

cd dataacessor
mvn jetty:run

cd ..
```
