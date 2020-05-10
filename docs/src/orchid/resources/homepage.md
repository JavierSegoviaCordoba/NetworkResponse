| [![Master Download](https://img.shields.io/maven-central/v/com.javiersc.resources/network-response?label=Master)](https://repo1.maven.org/maven2/com/javiersc/resources/network-response/)                                                                          | [![Coverage Master](https://img.shields.io/codecov/c/github/JavierSegoviaCordoba/NetworkResponse/master?label=Coverage&logo=codecov&logoColor=white)](https://codecov.io/gh/JavierSegoviaCordoba/NetworkResponse/branch/master)    | [![Master Build](https://img.shields.io/github/workflow/status/JavierSegoviaCordoba/NetworkResponse/Master/master?label=Build&logo=GitHub)](https://github.com/JavierSegoviaCordoba/NetworkResponse/actions?query=workflow%3AMaster/master)      | [![Quality Master](https://img.shields.io/codacy/grade/be9b2f773c72435a87809cc31bae3df9/master?label=Code%20quality&logo=codacy&logoColor=white)](https://app.codacy.com/manual/JavierSegoviaCordoba/NetworkResponse/dashboard?bid=17394400)   |
| :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [![Develop Download](https://img.shields.io/nexus/s/com.javiersc.resources/network-response?server=https%3A%2F%2Foss.sonatype.org%2F&label=Develop&color=orange)](https://oss.sonatype.org/content/repositories/snapshots/com/javiersc/resources/network-response/) | [![Coverage Develop](https://img.shields.io/codecov/c/github/JavierSegoviaCordoba/NetworkResponse/develop?label=Coverage&logo=codecov&logoColor=white)](https://codecov.io/gh/JavierSegoviaCordoba/NetworkResponse/branch/develop) | [![Develop Build](https://img.shields.io/github/workflow/status/JavierSegoviaCordoba/NetworkResponse/Develop/develop?label=Build&logo=GitHub)](https://github.com/JavierSegoviaCordoba/NetworkResponse/actions?query=workflow%3ADevelop/develop) | [![Quality Develop](https://img.shields.io/codacy/grade/be9b2f773c72435a87809cc31bae3df9/develop?label=Code%20quality&logo=codacy&logoColor=white)](https://app.codacy.com/manual/JavierSegoviaCordoba/NetworkResponse/dashboard?bid=17394399) |

# [NetworkResponse](/networkResponse/src/commonMain/kotlin/com/javiersc/resources/networkResponse/NetworkResponse.kt)

`NetworkResponse` is a `sealed class` to wrap responses from network requests:
  - `Info` [`code` and `headers`]
  - `Success` [`data` (not null), `code` and `headers`]
    - `Empty` [`code` and `headers`]
  - `ClientError` [`error` (can be null), `code` and `headers`]
  - `ServerError` [`error` (can be null), `code` and `headers`]
  - `InternetNotAvailable` [`error`]

This library works very well when used in conjunction with 
[`Resource`](https://github.com/JavierSegoviaCordoba/Resource) which is very similar
to `NetworkResponse` but thought to use with another architecture layer, for example domain objects.
`NetworkResponse` has mappers to transform it to a `Resource`.

## Features
  -  Multiplatform (NetworkResponse and Resource support)
  -  Retrofit support (jvm)
  -  Kotlin Serialization
  
## Roadmap
  - Ktor support

## Download

This library is Kotlin Multiplatform but at this moment jvm is the only artifact generated. It is 
available at Maven Central.

```run-kotlin
implementation("com.javiersc.resources:network-response:$version")
```
     
## NetworkResponseCallAdapterFactory

This adapter for `Retrofit` wraps automatically the `Retrofit` responses with a `NetworkResponse`:

```run-kotlin
@GET("users")
suspend fun getUsers(): NetworkResponse<List<UserDTO>, ErrorDTO>
// UserDTO and ErrorDTO should be your data classes
```
If the server doesn't return an error body, or it is irrelevant you can mark it as `Unit`:
```run-kotlin
@GET("users")
suspend fun getUsers(): NetworkResponse<List<UserDTO>, Unit>
```
Add the `NetworkResponseCallAdapterFactory` to the `Retrofit` builder:
```run-kotlin
private val retrofit = Retrofit.Builder().apply {
    //...
    addCallAdapterFactory(NetworkResponseCallAdapterFactory())
    //...
}.build()
```
It is possible to use `Deferred` too:
```run-kotlin
@GET("users")
fun getUsers(): Deferred<NetworkResponse<List<UserDTO>, ErrorDTO>>
```

## Mappers

Map any `NetworkResponse` to `Resource` easily with this
[extension function](/networkResponse/src/commonMain/kotlin/com/javiersc/resources/networkResponse/extensions/NetworkResponse.kt):
```run-kotlin
val resource: Resource<UserDTO, Error> = networkResponse.toResource(
    success = { userDTO: UserDTO, code: Int, headers: Headers -> userDTO.toUser() },
    error = { errorDTO: ErrorDTO?, code: Int, headers: Headers -> errorDTO.toError() },
    internetNotAvailable = { errorMessage: String -> errorMessage.toError() }
)
// UserDTO and ErrorDTO are your network objects, User and Error are your domain objects.
// UserDTO.toUser(), ErrorDTO?.toError() and String.toError() mappers should be created by yourself.
```

## Credits
Based on [NetworkResponseAdapter](https://github.com/haroldadmin/NetworkResponseAdapter)
by [Kshitij Chauhan](https://github.com/haroldadmin)