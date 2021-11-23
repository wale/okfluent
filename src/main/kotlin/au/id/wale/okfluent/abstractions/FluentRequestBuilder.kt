package au.id.wale.okfluent.abstractions

import au.id.wale.okfluent.async.AsyncCallback
import au.id.wale.okfluent.enums.RequestType
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import org.apache.commons.validator.routines.UrlValidator
import java.util.concurrent.CompletableFuture

class FluentRequestBuilder {
    private var headers: Headers? = null
    private var url: String? = null
    private var body: RequestBody? = null
    private var reqType: RequestType? = null

    private var client = OkHttpClient()

    private var validator = UrlValidator(arrayOf("http", "https"))


    fun header(pair: () -> Pair<String, String>): FluentRequestBuilder {
        val list = pair.invoke().toList().toTypedArray()
        val head = Headers.headersOf(*list)
        this.headers = head
        return this
    }

    fun url(url: String): FluentRequestBuilder {
        if(UrlValidator.getInstance().isValid(url)) {
            this.url = url
            return this
        } else {
            throw Exception("$url is not a valid URL.")
        }
    }

    fun body(mediaType: MediaType, body: String): FluentRequestBuilder {
        this.body = body.toRequestBody(mediaType)
        return this
    }

    fun request(type: RequestType): FluentRequestBuilder {
        this.reqType = type
        return this
    }

    fun build(): Response {
        val req = Request.Builder()
        req.url(this.url!!)
        when (this.reqType) {
            RequestType.POST -> {
                if(this.headers != null) {
                    req.headers(this.headers!!).post(this.body!!)
                } else {
                    req.post(this.body!!)
                }
            }
            RequestType.PATCH -> {
                if(this.headers != null) {
                    req.headers(this.headers!!).patch(this.body!!)
                } else {
                    req.patch(this.body!!)
                }
            }
            RequestType.DELETE -> {
                if(this.headers != null) {
                    req.headers(this.headers!!).delete(this.body!!)
                } else {
                    req.delete(this.body!!)
                }
            }
            RequestType.PUT -> {
                if(this.headers != null) {
                    req.headers(this.headers!!).put(this.body!!)
                } else {
                    req.post(this.body!!)
                }
            }
            RequestType.HEAD -> {
                if(this.headers != null) {
                    req.headers(this.headers!!).head()
                } else {
                    req.head()
                }
            }
            RequestType.GET -> {
                if(this.headers != null) {
                    req.headers(this.headers!!).get()
                } else {
                    req.get()
                }
            }
        }
        val r = req.build()
        return client.newCall(r).execute()
    }

    fun buildAsync(): CompletableFuture<Response> {
        val req = Request.Builder()
        val call = AsyncCallback()
        req.url(this.url!!)
        when (this.reqType) {
            RequestType.POST -> {
                req.post(this.body!!)
            }
            RequestType.PATCH -> {
                req.patch(this.body!!)
            }
            RequestType.DELETE -> {
                req.post(this.body!!)
            }
            RequestType.PUT -> {
                req.put(this.body!!)
            }
            RequestType.HEAD -> {
                req.head()
            }
            RequestType.GET -> {
                req.get()
            }
        }
        val r = req.build()
        val c = client.newCall(r).enqueue(call)
        return call.future
    }
}