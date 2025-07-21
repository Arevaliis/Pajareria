# 🐦 ***Sistema de Gestión de una Pajarería*** 🐦

## 📋 Descripción
Programa desarrollado en Java para facilitar la gestión de una pajarería. Permite llevar a cabo diferentes funciones organizadas por módulos:

### 🧾 Gestión de clientes:
- Añadir nuevos clientes.
- Modificar la información de un cliente existente.
- Dar de baja a un cliente.
- Listar todos los clientes registrados en la tienda.

### 📦 Gestión del inventario de la tienda:
- Agregar nuevas especies de pájaros al catálogo.
- Visualizar el inventario completo de la tienda.
- Buscar pájaros por especie.

### 💰 Gestión de ventas:
- Registrar ventas de pájaros a clientes.

### 📊 Consulta del historial de ventas:
- Visualizar todas las ventas realizadas por un cliente específico.
- Consultar el histórico completo de ventas.
- Calcular el gasto total de un cliente en la tienda.
- Ver el importe económico de cada venta registrada.

---

## 🚦 Estado del Proyecto

| Versión | Estado |
|--------:|:-------|
|     1.0 | ✅      |
|     2.0 | ✅      |
|     3.0 | ✅     |
|     4.0 | 🚧     | 

---

## ⚙️ Requisitos para compilar y ejecutar

### 📁 Clonar el repositorio

```bash
# Ubícate en la carpeta donde guardarás el proyecto
git clone https://github.com/Arevaliis/Pajareria.git
cd Pajareria
```

### 💻 Ejecución desde Consola
```
1. Compilar el programa: javac src/main/java/main.Main.java
2. Ejecutar programa: java -cp src/main/java main.Main
```

### 🧠 Ejecución desde IntelliJ
```
1. Abre el proyecto
2. Ejecuta Run en main.Main.java
```
---

## 🛠️ Instrucciones de uso

Al ejecutar el archivo `main.Main.java`, se iniciará el programa. Lo primero que veremos es el menú principal:


======= MENÚ PRINCIPAL =======
1. Gestión de clientes
2. Gestión de pájaros
3. Realizar venta
4. Mostrar ventas
5. Salir

El programa nos pedirá que selecciones una opción.
Si seleccionamos una opción fuera de rango o ingresamos un valor no numérico, saltará un error y nos pedirá nuevamente que ingresemos una opción. 

### ⚠️ Ejemplo Alta de un Nuevo Cliente ⚠️

Si ingresamos un  **1**, accederemos al menu de gestión de clientes:

======= MENÚ CLIENTES ========
1. Alta de clientes
2. Baja de clientes
3. Modificación cliente
4. Búsqueda por DNI
5. Listado
6. Volver

Todos los menus funcionan igual, si ingresamos algo que no deberíamos se mostrará un mensaje con el error. Pero si ingresamos un **1**, comenzaremos el proceso de alta de un cliente;

1. Ingresamos DNI
2. Ingresamos nombre
3. Ingresamos teléfono
4. Ingresamos email

Al finalizar el proceso, el programa nos preguntará:

- Si deseas dar de alta a otro cliente.
    - Si respondes "sí", el proceso se repite.
    - Si respondes "no", preguntará si deseas volver al menú de clientes.
        - Si respondes "sí", regresarás al submenú de clientes.
        - Si respondes "no", preguntará si deseas volver al menú principal.
            - Si respondes "sí", volverás al menú principal.
            - Si respondes "no", el programa finalizará.

> 🧠 Esta estructura se repite en todos los módulos: inventario, ventas y consultas.

---

## 👤 Autoría
 * [Jose Iglesias Arévalo](https://arevaliis.github.io/Portafolio) 

---
## 📄 Licencia
Este proyecto no tiene licencia.

---
## 📫 Contacto

- ✉️ [joseiglesiasarevalo@gmail.com](mailto:joseiglesiasarevalo@gmail.com)
- 💼 [LinkedIn](https://www.linkedin.com/in/jose-iglesias-ar%C3%A9valo-812860206/)
- 🐙 [GitHub](https://github.com/Arevaliis)