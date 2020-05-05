package com.jtn.springbootsample.presentation.validation.notblank

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [NotBlankValidator::class])
annotation class NotBlank(
  val message: String,
  val groups: Array<KClass<out Any>> = [],
  val payload: Array<KClass<out Payload>> = []
)
