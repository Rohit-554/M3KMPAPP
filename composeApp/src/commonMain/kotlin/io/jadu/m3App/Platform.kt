package io.jadu.m3App

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
