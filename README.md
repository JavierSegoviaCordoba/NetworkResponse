[![Download](https://api.bintray.com/packages/javiersegoviacordoba/Resources/NetworkResponse/images/download.svg)](https://bintray.com/javiersegoviacordoba/Resources/NetworkResponse/_latestVersion)
[![Build](https://github.com/JavierSegoviaCordoba/NetworkResponse/workflows/Build/badge.svg)](https://github.com/JavierSegoviaCordoba/NetworkResponse/actions)
[![coverage](https://img.shields.io/codecov/c/github/javiersegoviacordoba/networkResponse)](https://codecov.io/gh/JavierSegoviaCordoba/NetworkResponse)

# [NetworkResponse](/networkResponse/src/main/kotlin/com/javiersc/resources/networkResponse/NetworkResponse.kt)

`NetworkResponse` is a sealed class which has all standard status codes and let you use custom 
codes too. I recommend you check the class directly [here](networkResponse/src/main/kotlin/com/javiersc/resources/networkResponse/NetworkResponse.kt).

This library works very good if you use it together 
[`Resource`](https://github.com/JavierSegoviaCordoba/Resource) which is very similar
to `NetworkResponse` but thought to use with another architecture layer, for example domain objects.

## Features
- Retrofit
- Kotlin Serialization
- Gson

## Download
```groovy
Groovy
implementation "com.javiersc.resources:network-response:$version"
```

```kotlin
Kotlin DSL
implementation("com.javiersc.resources:network-response:$version")
```
     
## NetworkResponseCallAdapterFactory

This adapter for `Retrofit` wrap automatically the `Retrofit` responses with a `NetworkResponse:

```kotlin
@GET("users")
suspend fun getUsers(): NetworkResponse<List<UserDTO>, ErrorDTO>
// UserDTO and ErrorDTO should have your data classes
```
If the server doesn't return an error body, or it is irrelevant:
```kotlin
@GET("users")
suspend fun getUsers(): NetworkResponse<List<UserDTO>, Unit>
```
Add the NetworkResponseCallAdapterFactory to the Retrofit builder:
```kotlin
private val retrofit = Retrofit.Builder().apply {
    //...
    addCallAdapterFactory(NetworkResponseCallAdapterFactory())
    //...
}.build()
```
It is possible to use Deferred too:
```kotlin
@GET("users")
fun getUsers(): Deferred<NetworkResponse<List<UserDTO>, ErrorDTO>>
```

## Mappers

Map any NetworkResponse to Resource easily with this
[extension function](/networkResponse/src/main/kotlin/com/javiersc/resources/networkResponse/extensions/NetworkResponse.kt):
```kotlin
val resource: Resource<UserDTO, Error> = networkResponse.toResource(
    success = { userDto: UserDTO -> userDTO.toUser() },
    error = { errorDTO: ErrorDTO? -> errorDTO.toError() }
)
// UserDTO and ErrorDTO are your network objects, User and Error are your domain objects.
 
// UserDTO.toUser() and ErrorDTO?.toError() mappers should be created by youself There are more 
// maps, not only success and error, for more customization.
```
Both, `success` and `error` are needed.

This map is really huge, it lets you map every possibility, for example a custom NotFound error:
```kotlin
val resource: Resource<UserDTO, Error> = networkResponse.toResource(
    success = { userDto: UserDTO -> userDTO.toUser() },
    error = { errorDTO: ErrorDTO? -> errorDTO.toError() },
    notFound = { errorDTO: ErrorDTO? -> Error("Here I have a custom error") }
)
```

## Credits
Based on [NetworkResponseAdapter](https://github.com/haroldadmin/NetworkResponseAdapter)
by [Kshitij Chauhan](https://github.com/haroldadmin)