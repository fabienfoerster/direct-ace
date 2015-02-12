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

//yolo
wget http://54.154.176.223:8080/dataacessor/test


cd ..
```
