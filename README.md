| [![Master Download](https://img.shields.io/maven-central/v/com.javiersc.network-response/network-response?label=Master)](https://repo1.maven.org/maven2/com/javiersc/resources/network-response/)                                                                          | [![Coverage Master](https://img.shields.io/codecov/c/github/JavierSegoviaCordoba/NetworkResponse/master?label=Coverage&logo=codecov&logoColor=white)](https://codecov.io/gh/JavierSegoviaCordoba/NetworkResponse/branch/master)    | [![Master Build](https://img.shields.io/github/workflow/status/JavierSegoviaCordoba/NetworkResponse/Master/master?label=Build&logo=GitHub)](https://github.com/JavierSegoviaCordoba/NetworkResponse/actions?query=workflow%3AMaster/master)      | [![Quality Master](https://img.shields.io/codacy/grade/be9b2f773c72435a87809cc31bae3df9/master?label=Code%20quality&logo=codacy&logoColor=white)](https://app.codacy.com/manual/JavierSegoviaCordoba/NetworkResponse/dashboard?bid=17394400)   |
| :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [![Develop Download](https://img.shields.io/nexus/s/com.javiersc.network-response/network-response?server=https%3A%2F%2Foss.sonatype.org%2F&label=Develop&color=orange)](https://oss.sonatype.org/content/repositories/snapshots/com/javiersc/resources/network-response/) | [![Coverage Develop](https://img.shields.io/codecov/c/github/JavierSegoviaCordoba/NetworkResponse/develop?label=Coverage&logo=codecov&logoColor=white)](https://codecov.io/gh/JavierSegoviaCordoba/NetworkResponse/branch/develop) | [![Develop Build](https://img.shields.io/github/workflow/status/JavierSegoviaCordoba/NetworkResponse/Develop/develop?label=Build&logo=GitHub)](https://github.com/JavierSegoviaCordoba/NetworkResponse/actions?query=workflow%3ADevelop/develop) | [![Quality Develop](https://img.shields.io/codacy/grade/be9b2f773c72435a87809cc31bae3df9/develop?label=Code%20quality&logo=codacy&logoColor=white)](https://app.codacy.com/manual/JavierSegoviaCordoba/NetworkResponse/dashboard?bid=17394399) |

# [NetworkResponse](/networkResponse/src/commonMain/kotlin/NetworkResponse.kt)

`NetworkResponse` is a `sealed class` to wrap responses from network requests:
  - `Success` [`data`, `code` and `headers`]
  - `Error` [`error`, `code` and `headers`]
  - `UnknownError` [`throwable`]
  - `RemoteNotAvailable`
  - `InternetNotAvailable`

This library works very well when used in conjunction with 
[`Resource`](https://github.com/JavierSegoviaCordoba/Resource) which is very similar
to `NetworkResponse` but thought to use with another architecture layer, for example domain objects.
`NetworkResponse` has mappers to transform it to a `Resource`.

## Features
  -  Multiplatform (NetworkResponse and Resource support)
  -  Retrofit support (jvm)
  -  Ktor support

## Download

This library is Kotlin Multiplatform but at this moment jvm is the only artifact generated. It is 
available at Maven Central.

```kotlin
implementation("com.javiersc.network-response:network-response:$version")
```
     
## Retrofit

This adapter for `Retrofit` wraps automatically the `Retrofit` responses with a `NetworkResponse`:

```kotlin
@GET("users")
suspend fun getUsers(): NetworkResponse<List<UserDTO>, ErrorDTO>
// UserDTO and ErrorDTO should be your data classes
```

If the server doesn't return an error body, or it is irrelevant you can mark it as `Unit`:

```kotlin
@GET("users")
suspend fun getUsers(): NetworkResponse<List<UserDTO>, Unit>
```

Add the `NetworkResponseCallAdapterFactory` to the `Retrofit` builder:

```kotlin
private val retrofit = Retrofit.Builder().apply {
    //...
    addCallAdapterFactory(NetworkResponseCallAdapterFactory())
    //...
}.build()
```

It is possible to use `Deferred` too:

```kotlin
@GET("users")
fun getUsers(): Deferred<NetworkResponse<List<UserDTO>, ErrorDTO>>
```

## Ktor

You only need to wrap the request and do not indicate the type in the client method because it is 
inferred automatically

```kotlin
val usersNetworkResponse = NetworkResponse<List<UsersDTO>, ErrorDTO> { client.get("https://example.com/users") }
```


## More artifacts

- [Resource extensions](/resource-extensions)
- [Logger extensions](/logger-extensions)

## Credits
Based on [NetworkResponseAdapter](https://github.com/haroldadmin/NetworkResponseAdapter)
by [Kshitij Chauhan](https://github.com/haroldadmin)
