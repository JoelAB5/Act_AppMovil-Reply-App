# Verificación de Reply

Se completaron los dos codelabs en orden, partiendo del código inicial de `starter`:

1. Navegación adaptable, guardada en la etiqueta `codelab-1`.
2. Contenido adaptable y pruebas, guardados en `codelab-2` e incluidos en la rama de entrega `main` del repositorio personal. El código de partida de ambas actividades fue `starter`.

## Compilación

- Primera etapa: `assembleDebug`, resultado **BUILD SUCCESSFUL**.
- Segunda etapa: `assembleDebug assembleDebugAndroidTest lintDebug`, resultado **BUILD SUCCESSFUL**.
- Tras actualizar las bibliotecas de pruebas: `assembleDebugAndroidTest` y `lintDebug`, ambos **BUILD SUCCESSFUL**.
- Android Lint: **0 errores y 21 advertencias**. Las advertencias incluyen actualización de herramientas y dependencias y recomendaciones del código base.

Se usa Gradle 8.12 con Java 21. Para las pruebas se actualizaron Espresso a 3.7.0 y AndroidX Test JUnit a 1.3.0: las versiones antiguas del codelab fallaban al acceder a `InputManager` en el emulador moderno instalado.

## Pruebas automatizadas

Se ejecutó el APK de pruebas con AndroidJUnitRunner en el emulador `Small_Phone`, cambiando su resolución a los tamaños indicados. Se restauraron el tamaño y la densidad originales al finalizar.

| Grupo | Resolución | Densidad | Ancho lógico | Resultado |
| --- | --- | --- | --- | --- |
| Compacto | 720 × 1280 px | 320 dpi | 360 dp | 2 pruebas aprobadas |
| Mediano | 1400 × 1800 px | 320 dpi | 700 dp | 1 prueba aprobada |
| Expandido | 2200 × 1600 px | 320 dpi | 1100 dp | 2 pruebas aprobadas |

Las cinco pruebas verifican el tipo de navegación y la conservación del correo seleccionado al simular la restauración del estado de Compose. Esto no es una prueba de recuperación después de que Android elimine el proceso.

Registros originales del ejecutor:

- [Compacto](evidencias/pruebas/compacto/resultado.txt)
- [Mediano](evidencias/pruebas/mediano/resultado.txt)
- [Expandido](evidencias/pruebas/expandido/resultado.txt)

La tarea `connectedDebugAndroidTest` no llegó a ejecutar las pruebas porque no pudo descargar una dependencia auxiliar de Google (`com.android.tools.emulator:proto:31.3.0`). Por eso se ejecutaron directamente los APK ya compilados mediante `adb shell am instrument`, usando el mismo AndroidJUnitRunner y filtrando por las anotaciones de tamaño. Los resultados aprobados corresponden a esa ejecución directa.

Ejemplo para repetir el grupo compacto con un emulador compacto conectado, después de compilar los APK:

```powershell
.\gradlew.bat assembleDebug assembleDebugAndroidTest
$adb = "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe"
& $adb install -r app/build/outputs/apk/debug/app-debug.apk
& $adb install -r app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk
& $adb shell am instrument -w -e annotation com.example.reply.test.TestCompactWidth com.example.reply.test/androidx.test.runner.AndroidJUnitRunner
```

## Comprobaciones sobre la app real

También se verificaron el regreso desde un correo, el filtro de Enviados, la posición de los controles según el tamaño, la selección en la vista de dos paneles y la continuidad al reducir la ventana de tablet a teléfono. En este último caso se conservó el correo y apareció el botón Atrás de la pantalla de detalle individual. Atrás en la vista expandida cerró la actividad.

El registro completo está en [comprobaciones-manuales.txt](evidencias/comprobaciones-manuales.txt).

## Capturas para la entrega

- [Teléfono: lista y navegación inferior](evidencias/01-telefono-lista.png)
- [Teléfono: detalle del correo](evidencias/02-telefono-detalle.png)
- [Ventana mediana: riel lateral](evidencias/03-ventana-mediana.png)
- [Tablet: panel permanente, lista y detalle](evidencias/04-tablet-lista-detalle.png)
- [Cambio de tamaño conservando el correo](evidencias/05-cambio-tamano-conserva-correo.png)

Se revisaron visualmente las capturas de la aplicación ejecutada. El APK final está también disponible junto a la carpeta del proyecto como `Reply-debug.apk`.
