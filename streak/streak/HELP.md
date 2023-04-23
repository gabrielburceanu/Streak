# Getting Started

#redis 🌶️
cd /c/code/kube_demo/streak/streak
docker compose up
-> this starts the redis server
Redis:
    winpty docker exec -it <redisContainerHash> 'redis-cli'
    127.0.0.1:6379[2]> config get *

kubectl exec -it redis-5f9d69ccdb-hn9qk -- sh

#reactUI ⚛️
cd react-ui
npm start

npm run build
docker build -t gabidockerid/react-ui .
docker push gabidockerid/react-ui:latest

kubectl exec react-ui-6496df94fc-72h8v -- printenv

kubectl exec react-ui-6496df94fc-q6vqp -- curl streak-web:80

#kubernetes 🎄
0. delete C:\Users\gabriel\AppData\Local\Docker\pki
1. install docker desktop + enable kubernetes
2. Add into C:\Windows\System32\drivers\etc\hosts 
#    # Added by Docker Desktop
#    192.168.0.106 host.docker.internal
#    192.168.0.106 gateway.docker.internal
#    # To allow the same kube context to work on the host and the container:
#    127.0.0.1 kubernetes.docker.internal


#streak
run gradle build task
cd /c/code/kube_demo/streak/streak/steak-web
docker build -t gabidockerid/streak-web-image:1.0.2 .
docker push gabidockerid/streak-web-image:1.0.2
push the image to the docker repository "gabidockerid"

cd /c/code/kube_demo/streak/streak
kubectl apply -f kube
kubectl delete -f kube

#streak-client
run gradle build task
cd /c/code/kube_demo/streak/streak/streak-client
docker build -t gabidockerid/streak-client-image:1.0.0 .
docker push gabidockerid/streak-client-image:1.0.0
push the image to the docker repository "gabidockerid"

#custom nginx
docker build -t gabidockerid/custom-nginx-image:1.0.0 .
docker push gabidockerid/custom-nginx-image:1.0.0

kubectl exec -i -t custom-nginx-6fd5cb9d9c-jvthj -- /bin/bash

nginx -s reload


#install ingress
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.0.4/deploy/static/provider/cloud/deploy.yaml
###kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v0.41.2/deploy/static/provider/cloud/deploy.yaml
kubectl get all -n ingress-nginx

http://kubernetes.docker.internal/streak
http://kubernetes.docker.internal/streak/nonreactive


kubectl describe pod --namespace ingress-nginx ingress-nginx-controller-84c58fcb4b-4sbrs

kubectl logs -v3 ingress-nginx-controller-84c58fcb4b-4sbrs -n ingress-nginx

#logs
kubectl logs streak-5986467d4-zvlmk


#kafka
kubernetes:
    echo "hello world!" | kafkacat -P -b 10.109.19.33:9092 -t test

local:
    $ docker exec -t kafka-docker-kafka-1 kafka-topics.sh --bootstrap-server :9092 --list
    $ docker exec -t kafka-docker-kafka-1 kafka-console-consumer.sh --topic=streak-orders --bootstrap-server="host.docker.internal:53732"


#istio
kubectl label namespace default istio-injection=enabled


#mongo
local with docker:
    docker compose up
    mongosh --host localhost --port 27017 -u root -p
