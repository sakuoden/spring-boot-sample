package com.jtn.springbootsample.domain.model.product

import java.lang.IllegalArgumentException

/**
 * 商品名
 */
class ProductName(
  val value: String
) {
  companion object {
    private const val MIN_SIZE = 1
    private const val MAX_SIZE = 50
  }

  init {
    if (isUnderMin(value))
      throw IllegalArgumentException("ProductName value must be $MIN_SIZE or more. Assigned value is $value")

    if (isAboveMax(value))
      throw IllegalArgumentException("ProductName value must be $MAX_SIZE or less. Assigned value is $value")
  }

  fun value(): String = this.value

  fun sameValue(other: ProductName): Boolean = this.value == other.value

  private fun isUnderMin(value: String): Boolean = value.length < MIN_SIZE

  private fun isAboveMax(value: String): Boolean = value.length < MAX_SIZE
}
