/*
 *
 *  Copyright (C) 2026 Sentinelle Team <bgaleralop@gmail.com>
 *
 *  This source code is property of Sentinelle Team.
 *  It is made available publicly for portfolio evaluation and educational purposes only.
 *  Commercial use, reproduction, or distribution in any digital storefront is
 *  strictly prohibited under the PolyForm Noncommercial License 1.0.0.
 *
 *  For full license details, see the LICENSE.md file in the root directory.
 *
 */

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.serialization)
}

android {
    namespace = "es.bgaleralop.sentinelle"
    compileSdk = 37

    defaultConfig {
        applicationId = "es.bgaleralop.sentinelle"
        minSdk = 29
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    // Androidx Security (Cifrado de datos locan en hardware)
    implementation(libs.androidx.security.crypto)
    // Icons
    implementation(libs.androidx.compose.material.icons.extended)
    // Navegacion
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    // Testing
    testImplementation(libs.junit)
    // Soporte para testear funciones suspendidas y cambiar el Main Dispatcher en tests
    testImplementation(libs.kotlinx.coroutines.test)
    // MockK: La librería estándar de facto para hacer Mocks en Kotlin puro (ViewModels/UseCases)
    testImplementation(libs.mockk)
    // Turbine: Imprescindible para testear StateFlow y Flows de forma síncrona sin bloquear hilos
    testImplementation(libs.turbine)
    // Google Truth: Para escribir aserciones mucho más legibles que el assertEquals clásico
    testImplementation(libs.truth)
    // Si necesitas testear migraciones o DAOs de Room en la JVM (con arquitectura local-first)
    testImplementation(libs.androidx.room.testing)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)
    // Soporte de JUnit 4 para testing de interfaces con Jetpack Compose
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.navigation.testing)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
    // Tooling de Compose obligatorio para poder inflar y testear el árbol semántico de la UI
    debugImplementation(libs.androidx.ui.test.manifest)


}