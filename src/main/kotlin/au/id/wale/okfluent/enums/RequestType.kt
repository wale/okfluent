package au.id.wale.okfluent.enums


enum class RequestType(readable: String) {
    GET("get"),
    POST("post"),
    PUT("put"),
    HEAD("head"),
    DELETE("delete"),
    PATCH("patch")
}