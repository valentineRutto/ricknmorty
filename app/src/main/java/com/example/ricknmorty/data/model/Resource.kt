package com.example.ricknmorty.data.model

class Resource {
    companion object {
        class Resource<out T>(
            val status: Status,
            val data: T?,
            val message: String?
        ) {

            fun <T> success(data: T?): Resource<T> {
                return Resource(status = Status.SUCCESS, data = data, message = null)
            }

            fun <T> error(msg: String, data: T?): Resource<T> {
                return Resource(status = Status.ERROR, data = data, message = msg)
            }

            fun <T> loading(data: T?): Resource<T> {
                return Resource(status = Status.LOADING, data = data, message = null)
            }
        }
    }
}