# AEMET UV Data Fetcher - Consumo de APIs REST en Java 

Este proyecto es una aplicación de consola desarrollada en Java enfocada en el consumo de APIs REST de acceso público. El sistema se conecta a la infraestructura de datos abiertos de la **AEMET (Agencia Estatal de Meteorología)** para extraer, procesar y mostrar por pantalla los índices de radiación ultravioleta (UVI) y las predicciones meteorológicas específicas por ciudad.

---

##  Resumen del Proyecto

* **El Desafío**: Realizar conexiones de red seguras y manejar flujos de entrada/salida (I/O) de forma nativa sin depender de frameworks HTTP de terceros.
* **Propósito**: Asimilar los conceptos fundamentales de la asignatura **Acceso a Datos**, comprendiendo el protocolo HTTP, la serialización/deserialización de formatos estándar (JSON) y la arquitectura de APIs con endpoints asíncronos.

---

##  Características Técnicas Implementadas

* **Cliente HTTP Nativo**: Uso exclusivo de `HttpURLConnection` de la librería estándar de Java para establecer la comunicación y configurar las cabeceras de las peticiones (`GET`).
* **Flujo de Peticiones en Dos Fases**: Resolución eficiente de la arquitectura de la AEMET, que requiere un primer *request* para autenticar y obtener una URL temporal de descarga (`datosUrl`), seguido de un segundo *request* para extraer el *payload* real.
* **Parsing de JSON Manual**: Integración de la librería `org.json` (`JSONObject`, `JSONArray`) para recorrer, filtrar y extraer los atributos anidados del bloque de datos meteorológicos.
* **Gestión de Excepciones y Streams**: Control de flujos de lectura mediante `BufferedReader` e `InputStreamReader` para evitar fugas de memoria durante el consumo de la red.

---

## Estructura del Código

El repositorio cuenta con scripts independientes para el testeo de diferentes endpoints:

* `ApiUV.java`: Script principal encargado de extraer la predicción específica del índice Ultravioleta (UVI), procesar el JSON de respuesta y listar los nombres de las ciudades afectadas.
* `AemetApi.java`: Módulo adicional para la consulta de métricas meteorológicas generales.

---

## 🛠️ Core Tecnológico

* **Java SE (Standard Edition)**: Lógica principal y manejo de red nativo.
* **org.json**: Librería ligera para el mapeo y manipulación de estructuras de datos en formato JSON.
* **AEMET OpenData API**: Fuente oficial de datos meteorológicos del Gobierno de España.

---

## 👥 Autor

* **Michelle Carolina Posligua Contreras**
* **Módulo:** Acceso a Datos
* **Institución:** Institut Tecnològic Barcelona (ITB)

```


