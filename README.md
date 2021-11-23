# okfluent
A "fluent" OkHTTP library for Kotlin based on string extensions. Do not take this project seriously, I just wanted to show that this kind of thing could be done.

The concept was originally derived from [Flurl](https://flurl.dev), a C# library that achieves a similar purpose.

## Getting the dependency
### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
```xml
<dependency>
    <groupId>com.github.wale</groupId>
    <artifactId>okfluent</artifactId>
    <version>0.1.0</version>
</dependency>
```
### Gradle
#### (Groovy DSL)
```groovy
repositories {
    maven { url "https://jitpack.io" }
}
dependencies {
    implementation "com.github.wale:okfluent:0.1.0"
}
```
#### (Kotlin DSL)
```kotlin
repositories {
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("com.github.wale:okfluent:0.1.0")
}
```

## How to use
A simple request to httpbin.org:
```kotlin
fun main() {
    val req = "https://httpbin.org/get"
        .get()
        .header { "User-Agent" to "OkFluent/1.0" }
        .build()
    if(req.isSuccessful) {
        print(req.body!!.string())
    }
}
```
...and a POST request example:
```kotlin
fun main() {
    val req = "https://httpbin.org/get"
        .post()
        .header { "User-Agent" to "OkFluent/1.0" }
        .body("text/plain".toMediaType(), "hello")
        .build()
}
```

## Known issues
- Chaining multiple headers into one `.header` call will not work, you must use multiple `.header` calls for each header.
## License
This is licensed under the [MIT license](https://github.com/wale/okfluent/blob/master/LICENSE)