build the image
```
sudo docker build -t kona-backend .
```

run locally
```
sudo docker run --net=host -p 8080:8080 kona-backend
```
