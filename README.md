# Service Discoverer
Module demonstrates the functionality of [Service Registry service](http://docs.pivotal.io/spring-cloud-services/service-registry/), and [Circuit Breaker service](http://docs.pivotal.io/spring-cloud-services/circuit-breaker/index.html) in the context of PCF.

## Setup
Create an instance of PCF Circuit Breaker, Registry Service, and Configuration Service if not created.
```
$ cf create-service p-config-server standard config-server
$ cf create-service p-circuit-breaker-dashboard standard service-breaker
$ cf create-service p-service-registry standard service-registry
```
Configuration is served from [git repo](https://github.com/poprygun/training-grounds-config/)

## Usage
To apply load - run
```
$ while true; do sleep 1; curl -I -X GET http://ashumilov-sd.cfapps.pez.pivotal.io/techniques-rest; echo -e '\n\n\n\n'$(date);done
```
