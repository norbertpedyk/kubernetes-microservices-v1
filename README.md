
# Kubernetes-microservices

This project is developed for Kubernetes training purposes

When adding/deleting a post, Users-authors are being updated with number fo published posts.




## performed deployment steps

build docker images for the services from Dockerfiles

```bash
#make sure to execute in the same directory as given Dockerfile
#, or provide a path replacing "." at the end of the command

  docker build -t post-image .
```

```bash
  docker build -t user-image .
```



Tag the images
```bash
#repeat for both images
  docker tag user-image norbertpedyk/user-image:version1
```


Push the images to remote registry 
```bash
#repeat for both images
  docker push norbertpedyk/user-image:version1
```


## docker compose

Run databases and services from docker compose

```bash
#make sure to execute in the same directory as dockercompose.yml file

  docker compose up -d
```
To stop containers use:

```bash

  docker compose down
```


## Kubernetes minikube local deployment

Run start minikube cluster - make sure to have docker running as it will servr as vm environment
If you want you can use different provider as Hyperkit

```bash

  minikube start
```
apply all manifest files

```bash
    #run in the same directory as manifest files

  kubectl apply -f .  
```

All pods can be found with following command:

```bash

  kubectl get pods --namespace=kubernetes-microservices-task2
```

Logs of specific pod can be accessed with following command:

```bash

  kubectl logs deploy/user --namespace=kubernetes-microservices-task2
```

Logs of specific pod can be accessed with example command:

```bash

  kubectl logs deploy/user --namespace=kubernetes-microservices-task2
```


To Expose NodePorts services use:

```bash
  #to get list of services
  kubectl get svc --namespace=kubernetes-microservices-task2
  
  
  #to expose specific service
  minikube service post --namespace=kubernetes-microservices-task2

```
