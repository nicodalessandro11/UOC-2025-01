# Variables
IMAGE_NAME=my-nginx-rtmp
CONTAINER_NAME=streaming-server

# Build the Docker image
build:
	docker build -t $(IMAGE_NAME) .

# Run the container with port mappings
run:
	docker run -dit --name $(CONTAINER_NAME) -p 8080:80 -p 1935:1935 $(IMAGE_NAME)

# Stop the container
stop:
	docker stop $(CONTAINER_NAME)

# Remove the container
clean:
	docker rm $(CONTAINER_NAME)

# Connect to the container via bash
exec:
	docker exec -it $(CONTAINER_NAME) bash
