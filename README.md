# Service Base

Servicio encargado de recibir los comandos, los ejecuto y genera eventos de dominio que son enviados al proyecto Process y materializados.

## Commando creación de job

Este comando se encarga de ejecutar el proceso de creación de una tarea.

url: http://localhost:8080/api/createJob

Método: POST

Ejemplo Body

```
{
   "jobId": "000-000-000",
   "name": "Ejecutar prueba",
   "url": "https://prueba.com.co",
   "httpMethod": "GET",
   "requestBody": "{id:1}",
   "interval": "* */10 * * * *",
   "timezone": "America/Bogota",
   "email": "manefran@outlook.com"
}
```

## Commando creación de tarea

Este comando ejecuta una tarea

Url: http://localhost:8080/api/addTask
Método: POST

```
{
   "jobId": "000-000-000",
   "id": "1234579",
   "executionTimeSecond": "10",
   "httpCode": "500",
   "status": "Error"
}
```