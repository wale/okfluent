package au.id.wale.okfluent.test

import au.id.wale.okfluent.get
import au.id.wale.okfluent.post
import okhttp3.MediaType.Companion.toMediaType

fun main() {
    val req = "https://httpbin.org/get"
        .post()
        .header { "User-Agent" to "OkFluent/1.0" }
        .body("text/plain".toMediaType(), "hello")
        .build()
    if(req.isSuccessful) {
        print(req.body!!.string())
    }
}