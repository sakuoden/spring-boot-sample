package com.jtn.springbootsample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootSampleApplication

fun main(args: Array<String>) {
    runApplication<SpringBootSampleApplication>(*args)
}
