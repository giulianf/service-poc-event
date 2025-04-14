# POC-EVENT

```shell
podman compose up -d 
```
```shell
podman compose down 
```
# DeferredResult
![Github MS communication Flow - DeferredResult.png](docs/Github%20MS%20communication%20Flow%20-%20DeferredResult.png)
# only event
![Github MS communication Flow - Event-driven.png](docs/Github%20MS%20communication%20Flow%20-%20Event-driven.png)


docker exec -it kafka kafka-console-producer --broker-list localhost:9092 --topic person-response
