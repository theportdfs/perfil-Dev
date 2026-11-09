# Taller: Operaciones y Conversiones con Interfaz Gráfica (Swing)

**Institución:** Tecnológico de Antioquia - Institución Universitaria  
**Asignatura:** Construcción de Software I  
**Docente:** Marvin Montagut Martinez  
**Fecha:** Noviembre 2026  

---

## 📌 Descripción del Proyecto
Aplicación de escritorio desarrollada en Java utilizando **Swing** (`JFrame`, `JLabel`, `JTextField`, `JButton`, `JOptionPane`, `ActionListener`) y siguiendo una arquitectura modular que separa la lógica de negocio (`logica`) de la interfaz gráfica (`vista`).

---

## 🚀 Funcionalidades

### 1. Operaciones Matemáticas
- **Suma** ($a + b$)
- **Resta** ($a - b$)
- **Multiplicación** ($a \times b$)
- **División** ($a / b$) con validación para evitar la división por cero (`ArithmeticException`).

### 2. Conversión de Temperatura
- **Celsius a Fahrenheit:** $°F = (°C \times 9/5) + 32$
- **Fahrenheit a Celsius:** $°C = (°F - 32) \times 5/9$

### 3. Conversión de Moneda
- **Tasa fija establecida:** $1 \text{ USD} = \$3.200 \text{ COP}$
- **USD a COP:** Convierte dólares a pesos colombianos.
- **COP a USD:** Convierte pesos colombianos a dólares.

---

## 🛡️ Validaciones Implementadas
- **Campos Vacíos:** Se bloquea la operación y se notifica al usuario mediante `JOptionPane`.
- **Datos Numéricos:** Se validan mediante expresiones regulares; no se permiten letras ni caracteres especiales.
- **División por Cero:** Se detecta y previene, mostrando un mensaje de advertencia.
- **Claridad de Entradas:** La interfaz está segmentada visualmente con títulos y campos descriptivos para que el usuario identifique sin ambigüedad dónde ingresar cada dato.

---

## 📂 Estructura del Código

```text
src/
├── Main.java                 # Punto de entrada principal (ejecuta la GUI)
├── PruebaLogica.java         # Suite de pruebas de consola de la lógica
├── logica/                   # Capa de negocio
│   ├── OperacionesMatematicas.java
│   ├── ConversorTemperatura.java
│   ├── ConversorMoneda.java
│   └── ValidadorEntrada.java
└── vista/                    # Capa de presentación (Swing)
    └── VentanaPrincipal.java
```

---

## 💻 Instrucciones de Compilación y Ejecución

### Desde la Terminal (PowerShell / CMD):

1. **Compilar el proyecto:**
   ```powershell
   javac -encoding UTF-8 -d bin src/logica/*.java src/vista/*.java src/*.java
   ```

2. **Ejecutar la interfaz gráfica:**
   ```powershell
   java -cp bin Main
   ```

3. **Ejecutar las pruebas unitarias por consola (opcional):**
   ```powershell
   java -cp bin PruebaLogica
   ```
