# Используем образ Python в качестве базового образа
FROM python:3.9-slim

# Установка зависимостей (HTTP-сервера Flask)
RUN pip install flask

# Копирование HTML-файла внутрь контейнера
COPY src/main/webapp/only.html /app/index.html

# Копирование скрипта запуска сервера внутрь контейнера
COPY server.py /app/server.py

# Определение рабочей директории
WORKDIR /app

# Указание порта, на котором будет запущен сервер (по умолчанию 5000)
EXPOSE 5000

# Запуск HTTP-сервера Flask при запуске контейнера
CMD ["python", "server.py"]
