# Reply: navegación adaptable

Aplicación de práctica en Kotlin y Jetpack Compose. Esta primera etapa implementa navegación según el ancho de la ventana:

| Ancho de ventana | Navegación |
| --- | --- |
| Compacto, menos de 600 dp | Barra inferior |
| Mediano, de 600 a menos de 840 dp | Riel lateral |
| Expandido, desde 840 dp | Panel lateral permanente |

Los correos se abren mediante el estado del ViewModel. El botón Atrás de la pantalla y el del sistema regresan a la lista.

## Ejecutar

Abre esta carpeta en Android Studio, configura **Gradle JDK** con Java 21, sincroniza y ejecuta el módulo `app`. El proyecto usa Gradle 8.12 para ser compatible con Java 21; mantiene las dependencias del ejercicio original.

## Procedencia

Basado en el código inicial `starter` de [Reply de Android Developers](https://github.com/google-developer-training/basic-android-kotlin-compose-training-reply-app), conservando la licencia Apache 2.0 y sus avisos de autoría. Este repositorio local tiene un historial nuevo y no tiene ningún remoto configurado.

Primera actividad: [navegación adaptable](https://developer.android.com/codelabs/basic-android-kotlin-compose-adaptive-navigation-for-large-screens?hl=es-419).
