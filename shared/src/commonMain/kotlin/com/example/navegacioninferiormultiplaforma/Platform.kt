package com.example.navegacioninferiormultiplaforma

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform