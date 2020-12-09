# [Resource](https://github.com/JavierSegoviaCordoba/Resource)

Map any `NetworkResponse` to `Resource` easily with this
[extension function](/networkResponse/src/commonMain/kotlin/NetworkResponse.kt):

### Download

```kotlin
implementation("com.javiersc.network-response:resource-extensions:$version")
```

### Usage

```kotlin
val resource: Resource<User, Error> = networkResponse.toResource(
    success = { userDTO: UserDTO, code: HttpStatusCode, headers: Headers -> userDTO.toUser() },
    error = { errorDTO: ErrorDTO, code: HttpStatusCode, headers: Headers -> errorDTO.toError() },
    unknownError = { throwable: Throwable -> throwable.toError() },
    remoteNotAvailable = { errorMessage: String -> errorMessage.toError() },
    internetNotAvailable = { errorMessage: String -> errorMessage.toError() },
)
// UserDTO and ErrorDTO are your network objects, User and Error are your domain objects.
// UserDTO.toUser(), ErrorDTO.toError(), String.toError() and Throwable.toError() mappers should 
// be created by yourself.
```