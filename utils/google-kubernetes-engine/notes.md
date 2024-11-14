## INSTALL APP
1. Redis
```
helm install redis redis --set replica.replicaCount=0 --set master.persistence.size=2Gi
```

2. PROMETHEUS
```
helm install prometheus kube-prometheus
```

3. KEYCLOAK
```
helm install keycloak keycloak
```

4. KAFKA
```
helm install kafka kafka --set controller.persistence.size=2Gi
```

5. TEMPO
```
helm install tempo grafana-tempo --set ingester.persistence.size=4Gi
```

6. LOKI
```
helm install loki grafana-loki --set ingester.persistence.size=2Gi --set querier.persistence.size=2Gi --set compactor.persistence.size=2Gi
```

7. GRAFANA
```
helm install grafana grafana --set persistence.size=2Gi
```

## PORT FORWARDING

1. Prometheus:
```
kubectl port-forward --namespace default svc/prometheus-kube-prometheus-prometheus 9090:9090
```

2. Grafana:
```
kubectl port-forward svc/grafana 3000:3000
```
 
Grafana password:
```
kubectl get secret grafana-admin --namespace default -o jsonpath="{.data.GF_SECURITY_ADMIN_PASSWORD}" | base64 -d
```

3. authentication_db
```
kubectl port-forward svc/db-authentication 30618:30618
```

4. instructor_db
```
kubectl port-forward svc/db-instructor 30619:30619
```

5. course_db
```
kubectl port-forward svc/db-course 30620:30620
```

6. order_db
```
kubectl port-forward svc/db-order 30621:30621
```

7. payment_db
```
kubectl port-forward svc/db-payment 30622:30622
```

8. enrollment_db
```
kubectl port-forward svc/db-enrollment 30623:30623
```

9. finance_db
```
kubectl port-forward svc/db-finance 30624:30624
```

10. qna_db
```
kubectl port-forward svc/db-qna 30625:30625
```

11. catalog_db
```
kubectl port-forward svc/db-catalog 30627:30627
```

12. vocasia_db
```
kubectl port-forward svc/db-vocasia 30626:30626
```

13. redis
```
kubectl port-forward --namespace default svc/redis-master 6379:6379
```
