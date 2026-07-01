#### 2. `CONTRIBUTING.md`
# Guía de Contribución a Sentinelle 🚀

¡Gracias por tu interés en contribuir a Sentinelle! Queremos que este proyecto sea una herramienta robusta, limpia y segura para la protección de la salud digital. Como proyecto Local-First y de código abierto, dependemos del rigor técnico de nuestra comunidad.

---

## 🗺️ Flujo de Trabajo Git (Git Flow Adaptado)

Para mantener la estabilidad en producción, aplicamos una política estricta de ramificación:

1. **Haz un Fork** del repositorio a tu cuenta personal.
2. Crea una rama descriptiva a partir de `main` siguiendo la nomenclatura ágil:
   - `feature/HU-XXX-descripcion-corta` (Para nuevas funcionalidades)
   - `bugfix/issue-XXX-descripcion-corta` (Para corrección de errores)
   - `refactor/descripcion-corta` (Para mejoras de código sin cambio funcional)
3. Asegúrate de actualizar los tests asociados antes de proponer cambios.

---

## 📝 Estilo de Código Kotlin

Nos regimos estrictamente por las guías oficiales de Kotlin y las reglas impuestas por **ktlint**. Requisitos obligatorios:

- **Estructura de Composición:** En Jetpack Compose, las funciones `@Composable` deben ser puras siempre que sea posible, abstrayendo la lógica de estado hacia el `ViewModel` mediante `StateFlow`.
- **Inmutabilidad:** Haz uso prioritario de variables inmutables (`val`) y colecciones de solo lectura.
- **Asincronía:** Todo proceso de cómputo intensivo (análisis heurístico de cadenas de texto) debe ejecutarse de forma explícita bajo `Dispatchers.Default` o `Dispatchers.IO` a través de Coroutines de Kotlin.

---

## 💬 Estructura de Commits (Conventional Commits)

Los mensajes de commit deben seguir la especificación de **Conventional Commits** para permitir la generación automatizada de Changelogs:

```text
<tipo>(<alcance u hoja de ruta>): <descripción corta en imperativo y minúsculas>

[Cuerpo opcional detallando el motivo técnico del cambio]

[Pie de página opcional para referenciar Issues, ej: Closes #42]