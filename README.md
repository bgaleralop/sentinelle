# Sentinelle 🛡️

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.x-purple.svg)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Compose-2024.x-green.svg)](https://developer.android.com/jetpack/compose)
[![Platform](https://img.shields.io/badge/Platform-Android-brightgreen.svg)](https://developer.android.com)

**Sentinelle** es una solución avanzada de software libre diseñada para la moderación, filtrado y limpieza automatizada de comentarios y contenido nocivo en redes sociales (con soporte inicial para Instagram y TikTok). El proyecto nace con un propósito claro: democratizar las herramientas de protección digital y salvaguardar la salud mental de creadores de contenido y usuarios activos mediante el uso de inteligencia local.

---

## 🔒 Filosofía de Privacidad Absoluta: Local-First

A diferencia de las herramientas convencionales basadas en SaaS, **Sentinelle opera bajo una arquitectura estrictamente "Local-First"**.
* **Procesamiento en Dispositivo (On-Device):** El análisis heurístico e inteligente de los flujos de comentarios se ejecuta de forma nativa en el hardware de tu smartphone.
* **Soberanía de Datos:** No disponemos de servidores centrales de recolección de datos. Tus credenciales de acceso, tokens analíticos y registros de comentarios jamás se transmiten, almacenan ni procesan fuera de tu dispositivo.
* **Aislamiento Criptográfico:** Los datos sensibles se almacenan de manera local utilizando cifrado avanzado a nivel de persistencia de datos.

---

## 🛠️ Stack Tecnológico y Arquitectura

La aplicación ha sido desarrollada siguiendo los estándares más rigurosos del desarrollo nativo en Android:

- **Lenguaje:** [Kotlin](https://kotlinlang.org/) (Type-safe, asincronía mediante Coroutines y Flows).
- **Interfaz de Usuario:** [Jetpack Compose](https://developer.android.com/jetpack/compose) bajo arquitectura puramente declarativa y reactiva.
- **Arquitectura:** Arquitectura Limpia (*Clean Architecture*) acoplada con el patrón arquitectónico **MVVM (Model-View-ViewModel)**, garantizando el desacoplamiento total entre las reglas de negocio, los casos de uso y la infraestructura.
- **Persistencia Local:** [Room Database](https://developer.android.com/training/data-storage/room) con soporte nativo de cifrado.
- **Monetización:** Integración desacoplada y segura con la **API de Google Play Billing** mediante flujos reactivos de verificación de estado de suscripción.

### Estructura de Módulos (Clean Architecture)
```text
⚙️ app/
└── src/
    └── main/
        └── java/com/sentinelle/app/
            ├── core/          # Inyección de dependencias, utilidades globales
            ├── data/          # Implementación de Repositorios, Room DAO, APIs de Redes
            ├── domain/        # Modelos de Negocio, Casos de Uso (Use Cases) e Interfaces
            └── presentation/  # UI Components, ViewModels y State de Jetpack Compose