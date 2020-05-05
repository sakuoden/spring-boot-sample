package com.jtn.springbootsample.presentation.validation.notblank

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class NotBlankValidator : ConstraintValidator<NotBlank, String> {
  companion object {
    private const val CONSTRAINT_PATTERN = "^[\\s　]+$"
  }

  override fun initialize(constraintAnnotation: NotBlank?) {}

  override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
    value?.let {
      if (match(value)) return false
    }

    return true
  }

  private fun match(value: String): Boolean {
    val regex = Regex(CONSTRAINT_PATTERN)

    return regex.matches(value)
  }
}
