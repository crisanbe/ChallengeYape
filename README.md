#Challenge Yape
# Qué es este proyecto?
Proyecto
[https://github.com/crisanbe/RetoYape.git](https://github.com/crisanbe/ChallengeYape).

<img src="https://github.com/crisanbe/ChallengeYape/blob/dev/app/src/main/res/drawable/gif.gif" alt=""/>

![](https://i.imgur.com/jEnP0jC.png)![](https://i.imgur.com/P4IuAbP.png)

# Características principales
1. Kotlin
1. MVVM
1. Jetpack Compose
1. MutableStateOf
1. MutableSharedFlow
1. UIEvent
	- Simpler
1. Hilt
1. Room - Para persistir los datos en el Dispositivo al realizar una consulta
1. Navigation Compose
1. Retrofit
1. Custom Fonts

# Funciones de composición
3. Theming /Modo Oscuro
4. Fonts
5. Colors
	- creating
7. LazyGrip
8. Rows
9. Columns
10. Scaffold
11. AppBar
12. Circular Progress Indicator

# Pruebas
1. Junit
2. Mockito
3. Jacoco coverage.

# JaCoCo (Java Code Coverage):
1. JaCoCo es una herramienta de análisis de cobertura de código para proyectos Java. Te permite evaluar qué porcentaje de tu código está siendo ejecutado durante las pruebas, lo que es útil para asegurarse de que las pruebas cubran adecuadamente tu código.
   Integración en Android Studio:
   JaCoCo se integra fácilmente con Gradle (el sistema de construcción de Android).
   Puedes configurar tu proyecto para que las pruebas generen informes de cobertura de código y luego ver los resultados en Android Studio.
	1. Comando ./gradlew koverMergedReport  

![](https://github.com/crisanbe/ChallengeYape/blob/dev/app/src/main/res/drawable/homeviewmodel.png)
![](https://github.com/crisanbe/ChallengeYape/blob/dev/app/src/main/res/drawable/detailviemodel.png)

#  Flow?
1. Flow
	1. Flow es genial. es una característica de Kotlin coroutines que proporciona una forma asincrónica y reactiva de trabajar con secuencias de datos.
	   El método getListRecipeUseCase devuelve un Flow ya que se realiza una operación collect en el resultado. Esto indica que probablemente getListRecipeUseCase retorna un flujo de datos asincrónico que el ViewModel está consumiendo.
	   1.(https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/).
1. StateFlow Semántica de Estado:
	1. StateFlow  está diseñado específicamente para representar un estado mutable y proporcionar un flujo de eventos que notifica a los suscriptores cuando el estado cambia. Esto es útil cuando necesitas mantener y compartir un estado mutable en toda tu aplicación. (https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState) in viewmodels.
1. StateFlow Semántica de Estado:
	1. SharedFlow  está diseñado para emitir eventos o notificaciones a sus suscriptores. Es adecuado cuando necesitas comunicar eventos que no necesariamente están relacionados con un estado mutable. (https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState) in viewmodels.


# References
1. https://github.com/android/compose-samples
1. https://developer.android.com/jetpack/compose
1. https://developer.android.com/jetpack/compose/state
