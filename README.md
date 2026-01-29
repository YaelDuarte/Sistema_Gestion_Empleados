# Sistema de Gestión de Empleados

Aplicación de escritorio desarrollada en **Java** que permite gestionar empleados de una empresa utilizando los principios de **Programación Orientada a Objetos**, **DAO**, **Service Layer** y conexión a **Base de Datos MySQL** mediante **JDBC**.

## Funcionalidades

- ✅ Agregar empleados
  - Empleado de Tiempo Completo
  - Empleado por Horas
- ✏️ Editar empleados existentes
- 🗑️ Eliminar empleados
- 📋 Listar empleados en una tabla
- 🔄 Actualización dinámica de la interfaz
- 💰 Cálculo de salario según tipo de empleado
- 🧩 Interfaz gráfica con **Swing**

---

## Tecnologías utilizadas

- **Java SE**
- **Swing (GUI)**
- **JDBC**
- **MySQL**
- **Patrones de diseño**
  - DAO (Data Access Object)
  - Service Layer
- **Programación Orientada a Objetos**
  - Herencia
  - Polimorfismo
  - Clases abstractas
  - Interfaces

---

## Arquitectura del proyecto

El proyecto está organizado por capas:

- **Modelos**  
  Clases `Empleado`, `EmpleadoTiempoCompleto`, `EmpleadoPorHoras`.

- **DAO**  
  Acceso a datos y operaciones CRUD sobre la base de datos.

- **Service**  
  Lógica de negocio y validaciones.

- **UI (Swing)**  
  Interfaz gráfica para interacción con el usuario.

---

