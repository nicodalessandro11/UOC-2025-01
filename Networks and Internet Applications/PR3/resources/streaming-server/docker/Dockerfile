FROM ubuntu:22.04

# Evitar prompts de zona horaria y otros durante instalación
ENV DEBIAN_FRONTEND=noninteractive

# Actualizar sistema y paquetes
RUN apt update && apt upgrade -y && \
    apt install -y nginx libnginx-mod-rtmp net-tools sudo curl nano tzdata

# Crear el usuario 'nicodalessandro' y darle permisos de superusuario sin contraseña
RUN useradd -ms /bin/bash nicodalessandro && \
    echo 'nicodalessandro ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers

# Configurar el prompt bonito: (base) user@host fecha ~ $
RUN echo 'export PS1="(base) \u@\h \D{%Y-%m-%d} \w \$ "' >> /home/nicodalessandro/.bashrc

# Reemplazar la configuración de nginx por la configuración RTMP mejorada
COPY default_nginx.conf /etc/nginx/nginx.conf

# Abrir los puertos necesarios
EXPOSE 80 1935

# Setear el usuario por defecto
USER nicodalessandro

# Arrancar en bash
CMD ["/bin/bash"]
