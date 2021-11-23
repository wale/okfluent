package au.id.wale.okfluent

import au.id.wale.okfluent.abstractions.FluentRequestBuilder
import au.id.wale.okfluent.enums.RequestType

fun String.get(): FluentRequestBuilder {
    val builder = FluentRequestBuilder()
        .url(this)
        .request(RequestType.GET)
    return builder
}

fun String.post(): FluentRequestBuilder {
    val builder = FluentRequestBuilder()
        .url(this)
        .request(RequestType.POST)
    return builder
}

fun String.put(): FluentRequestBuilder {
    val builder = FluentRequestBuilder()
        .url(this)
        .request(RequestType.PUT)
    return builder
}

fun String.patch(): FluentRequestBuilder {
    val builder = FluentRequestBuilder()
        .url(this)
        .request(RequestType.PATCH)
    return builder
}

fun String.head(): FluentRequestBuilder {
    val builder = FluentRequestBuilder()
        .url(this)
        .request(RequestType.HEAD)
    return builder
}

fun String.delete(): FluentRequestBuilder {
    val builder = FluentRequestBuilder()
        .url(this)
        .request(RequestType.HEAD)
    return builder
}