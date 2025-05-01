# 🚀 Setup de Contenedor Docker con Nginx + RTMP + Usuario con Sudo

## 🐳 1. Crear y arrancar el contenedor Docker con Ubuntu y red de host

```bash
docker run -it --name streaming-server --network=host ubuntu:22.04 bash
```

> Esto abre una terminal directamente dentro del contenedor.

---

## 🧰 2. Dentro del contenedor como root

### Actualizar e instalar paquetes básicos

```bash
apt update && apt upgrade -y
apt install sudo nano nginx libnginx-mod-rtmp -y
```

---

## 👤 3. Crear usuario `nicodalessandro` y asignarle permisos sudo

### Crear el usuario

```bash
adduser nicodalessandro
```

(Sigue los pasos de contraseña, o pulsa `ENTER` en todos si quieres dejarlo en blanco)

### Darle permisos de superusuario (añadir al grupo sudo)

```bash
usermod -aG sudo nicodalessandro
```

---

## 🔁 4. Cambiar a ese usuario

```bash
su - nicodalessandro
```

---

## 🧪 5. Verificar que puede usar `sudo`

```bash
sudo apt update
```

(Debería pedirte la contraseña que pusiste al crear el usuario)

---

## ⚙️ 6. Comandos para controlar Nginx desde el usuario `nicodalessandro`

### 🔄 Reiniciar Nginx

```bash
sudo service nginx restart
```

### 🟢 Iniciar Nginx

```bash
sudo service nginx start
```

### 🔴 Detener Nginx

```bash
sudo service nginx stop
```

### 📋 Ver estado de Nginx

```bash
sudo service nginx status
```

---

## ✅ Comprobación opcional de grupos

```bash
groups
```

Debe mostrar que `nicodalessandro` está en el grupo `sudo`.
