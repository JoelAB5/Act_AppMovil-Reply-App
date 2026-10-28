# Reply: navegación y contenido adaptables

Aplicación de práctica en Kotlin y Jetpack Compose. Adapta la navegación y el contenido al ancho de la ventana:

| Ancho de ventana | Navegación | Contenido |
| --- | --- | --- |
| Compacto, menos de 600 dp | Barra inferior | Lista o detalle en pantallas separadas |
| Mediano, de 600 a menos de 840 dp | Riel lateral | Lista o detalle en pantallas separadas |
| Expandido, desde 840 dp | Panel lateral permanente | Lista y detalle al mismo tiempo |

Los correos se abren mediante el estado del ViewModel. En tamaños compacto y mediano, el botón Atrás de la pantalla y el del sistema regresan a la lista. En tamaño expandido, el correo se selecciona sin abandonar la lista y Atrás cierra la actividad, como indica la segunda práctica.

## Ejecutar

Abre esta carpeta en Android Studio, configura **Gradle JDK** con Java 21, sincroniza y ejecuta el módulo `app`. El proyecto usa Gradle 8.12 para ser compatible con Java 21; mantiene las dependencias de la aplicación del ejercicio original. Para ejecutar las pruebas en el emulador moderno instalado, se actualizaron Espresso a 3.7.0 y AndroidX Test JUnit a 1.3.0, versiones estables documentadas por [AndroidX Test](https://developer.android.com/jetpack/androidx/releases/test).

En este equipo, Java 21 está instalado en `C:\Users\JOEL BARRERA\.jdks\jbr-21.0.11` y ya está configurado localmente para este proyecto. Desde PowerShell:

```powershell
$env:JAVA_HOME = "C:\Users\JOEL BARRERA\.jdks\jbr-21.0.11"
.\gradlew.bat assembleDebug lintDebug
```

En `MainActivity.kt` están las vistas previas `ReplyAppCompactPreview`, `ReplyAppMediumPreview` y `ReplyAppExpandedPreview`. En el emulador, cambia el tamaño disponible o gira el dispositivo para comprobar que el diseño se adapta.

## Pruebas de interfaz

Los archivos están en `app/src/androidTest/java/com/example/reply/test`:

- `ReplyAppTest.kt`: comprueba la barra inferior, el riel lateral y el panel permanente según la clase de tamaño. También comprueba que los controles de otros tamaños no aparezcan.
- `ReplyAppStateRestorationTest.kt`: selecciona el tercer correo, simula la restauración del estado con `StateRestorationTester` y comprueba la selección en tamaños compacto y expandido. Las verificaciones del cuerpo del correo se limitan al panel de detalle para no confundirlo con el texto de la lista.
- `TestAnnotations.kt`: agrupa las pruebas por tamaño con `TestCompactWidth`, `TestMediumWidth` y `TestExpandedWidth`.
- `ComposeTestRuleExtensions.kt`: funciones auxiliares para localizar nodos mediante recursos de cadenas.

Conecta un emulador del tamaño adecuado y ejecuta el grupo correspondiente:

```powershell
# Telefono compacto: dos pruebas
.\gradlew.bat connectedDebugAndroidTest "-Pandroid.testInstrumentationRunnerArguments.annotation=com.example.reply.test.TestCompactWidth"

# Ventana mediana: una prueba
.\gradlew.bat connectedDebugAndroidTest "-Pandroid.testInstrumentationRunnerArguments.annotation=com.example.reply.test.TestMediumWidth"

# Tablet expandida: dos pruebas
.\gradlew.bat connectedDebugAndroidTest "-Pandroid.testInstrumentationRunnerArguments.annotation=com.example.reply.test.TestExpandedWidth"
```

En Android Studio también puedes usar **Run > Edit Configurations > Android Instrumented Tests**, ejecutar todas las pruebas del paquete `com.example.reply.test` y agregar el argumento `annotation` con el nombre completo de una de esas anotaciones. Debes cambiar el dispositivo o tamaño al cambiar de grupo; la anotación filtra las pruebas, no redimensiona el emulador.

## Historial de las dos actividades

La compilación y Android Lint terminaron correctamente. Las cinco pruebas instrumentadas pasaron y se comprobó el cambio real de tamaño en el emulador. El detalle, los registros y cinco capturas están en [VERIFICACION.md](VERIFICACION.md).

Las actividades se realizaron en el orden solicitado:

1. `codelab-1`: navegación adaptable, clases de tamaño y control del botón Atrás.
2. `codelab-2`: contenido lista-detalle, tres vistas previas y pruebas automatizadas.

La rama `starter` contiene el resultado final. Las etiquetas permiten consultar cada etapa. El codigo base se descargo de la rama `starter` oficial, no de la solucion `main` del codelab. El historial de entrega parte de un commit nuevo de la primera actividad; no contiene los commits antiguos de Google.

## Subir a tu propio GitHub

El trabajo se guarda localmente. Crea un repositorio vacío en tu cuenta de GitHub, sin README, licencia ni `.gitignore` iniciales. Abre PowerShell en esta carpeta, reemplaza la URL por la de tu repositorio y ejecuta:

```powershell
git status
git remote add origin https://github.com/TU_USUARIO/TU_REPOSITORIO.git
git push -u origin starter
git push origin --tags
```

Los commits ya están creados con la identidad que tienes configurada en Git. La subida y el inicio de sesión en GitHub los realizas tú. No se realizó ningún push ni se configuró un remoto de Google. Las carpetas de compilación, cachés y la configuración local del SDK están excluidas mediante `.gitignore`.

## Procedencia

Basado en el código inicial `starter` de [Reply de Android Developers](https://github.com/google-developer-training/basic-android-kotlin-compose-training-reply-app), conservando la licencia Apache 2.0 y sus avisos de autoría. Este repositorio local tiene un historial nuevo y no tiene ningún remoto configurado.

Primera actividad: [navegación adaptable](https://developer.android.com/codelabs/basic-android-kotlin-compose-adaptive-navigation-for-large-screens?hl=es-419).

Segunda actividad: [contenido adaptable](https://developer.android.com/codelabs/basic-android-kotlin-compose-adaptive-content-for-large-screens?hl=es-419).
