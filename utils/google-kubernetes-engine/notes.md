## INSTALL APP
### RABBITMQ
```
helm install rabbitmq rabbitmq --set persistence.size=2Gi
```

### KEYCLOAK
```
helm install keycloak keycloak
```

### REDIS
```
helm install redis redis --set replica.replicaCount=0 --set master.persistence.size=2Gi
```

### PROMETHEUS
```
helm install prometheus kube-prometheus
```

### KAFKA
```
helm install kafka kafka --set controller.persistence.size=2Gi
```

### TEMPO
```
helm install tempo grafana-tempo --set ingester.persistence.size=4Gi --set distributor.replicaCount=2 --set ingester.replicaCount=2
```

### LOKI
```
helm install loki grafana-loki --set ingester.persistence.size=2Gi --set querier.persistence.size=2Gi --set compactor.persistence.size=2Gi
```

### GRAFANA
```
helm install grafana grafana --set persistence.size=2Gi
```

## PORT FORWARDING
### RABBITMQ
```
kubectl port-forward --namespace default svc/rabbitmq 5672:5672
```

```
kubectl port-forward --namespace default svc/rabbitmq 15672:15672
```

```
kubectl get secret --namespace default rabbitmq -o jsonpath="{.data.rabbitmq-password}" | base64 -d
```

### REDIS
```
kubectl port-forward --namespace default svc/redis-master 6379:6379
```

```
kubectl port-forward --namespace default svc/redis-master 6379:6379 &
    redis-cli -h 127.0.0.1 -p 6379
```

### PROMETHEUS
```
kubectl port-forward --namespace default svc/prometheus-kube-prometheus-prometheus 9090:9090
```

### GRAFANA
```
kubectl port-forward svc/grafana 3000:3000
```
 
```
kubectl get secret grafana-admin --namespace default -o jsonpath="{.data.GF_SECURITY_ADMIN_PASSWORD}" | base64 -d
```

### authentication_db
```
kubectl port-forward svc/db-authentication 3307:3307
```

### instructor_db
```
kubectl port-forward svc/db-instructor 3312:3312
```

### course_db
```
kubectl port-forward svc/db-course 3309:3309
```

### order_db
```
kubectl port-forward svc/db-order 3313:3313
```

### payment_db
```
kubectl port-forward svc/db-payment 3314:3314
```

### enrollment_db
```
kubectl port-forward svc/db-enrollment 3310:3310
```

### finance_db
```
kubectl port-forward svc/db-finance 3311:3311
```

### catalog_db
```
kubectl port-forward svc/db-catalog 3308:3308
```

### vocasia_db
```
kubectl port-forward svc/db-vocasia 3306:3306
```

## GRAFANA DASHBOARD
```
https://grafana.com/grafana/dashboards/4701-jvm-micrometer/
```

```
https://grafana.com/grafana/dashboards/6417-kubernetes-cluster-prometheus/
```

```
https://grafana.com/grafana/dashboards/747-pod-metrics/
```

```
https://grafana.com/grafana/dashboards/13332-kube-state-metrics-v2/
```