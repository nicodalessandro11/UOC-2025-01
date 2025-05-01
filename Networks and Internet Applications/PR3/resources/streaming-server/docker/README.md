# Streaming Server - NGINX + RTMP + Docker

This project provides a ready-to-use Dockerized RTMP streaming server based on Ubuntu 22.04.  
It includes:

- NGINX with RTMP module pre-installed
- A custom user `nicodalessandro` with full `sudo` access
- Networking tools like `ifconfig` and editors like `nano`
- Pre-configured ports for HTTP (8080) and RTMP (1935)
- Custom shell prompt with username, host, and date
- Pre-loaded RTMP configuration (optimized with max_connections, idle_streams off, meta copy)

---

## 🚀 How to use

### 1. Clone the repository (or copy the files locally)

Make sure you have these files:
- `Dockerfile`
- `default_nginx.conf`
- `Makefile`
- `README.md` (this file)

---

### 2. Build the Docker image

```bash
make build

docker exec -it streaming-server bash
