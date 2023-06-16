FROM python:3.11-slim

ENV PYTHONUNBUFFERED True

WORKDIR /app

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
COPY foto_profile/ /app/foto_profile/

COPY app.py .

COPY current_model.h5 .

CMD exec gunicorn --bind :$PORT --workers 1 --threads 8 --timeout 0 app:app
