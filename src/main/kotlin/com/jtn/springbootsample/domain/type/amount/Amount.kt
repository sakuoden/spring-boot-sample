package com.jtn.springbootsample.domain.type.amount

import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.ParseException

/**
 * 金額
 */
class Amount(
  val value: BigDecimal
) {
  companion object {
    private const val MIN_AMOUNT = 0
    private const val MAX_AMOUNT = 999999

    fun valueOf(value: Int): Amount = Amount(BigDecimal(value))

    fun valueOf(value: String): Amount {
      try {
        val decimalFormat = DecimalFormat("#,##0")
        val number = decimalFormat.parse(value)
        return valueOf(number.toInt())
      } catch (exception: ParseException) {
        throw NumberFormatException(value)
      }
    }
  }

  init {
    if (isUnderMin(value))
      throw IllegalArgumentException("Amount value must be $MIN_AMOUNT or more. Assigned value is $value")

    if (isAboveMax(value))
      throw IllegalArgumentException("Amount value must be $MAX_AMOUNT or less. Assigned value is $value")
  }

  fun canAdd(other: Amount): Boolean {
    val result: BigDecimal = addValue(other)

    if (isAboveMax(result)) return false

    return true
  }

  fun add(other: Amount): Amount {
    val result: BigDecimal = addValue(other)

    return Amount(result)
  }

  fun value(): BigDecimal = this.value

  fun sameValue(other: Amount): Boolean {
    val result: Int = this.value.compareTo(other.value)

    if (result == 0) return true

    return false
  }

  private fun addValue(other: Amount): BigDecimal = this.value.add(other.value)

  private fun isUnderMin(value: BigDecimal): Boolean {
    val result: Int = value.compareTo(BigDecimal(MIN_AMOUNT))

    if (result == -1) return true

    return false
  }

  fun isAboveMax(value: BigDecimal): Boolean {
    val result: Int = value.compareTo(BigDecimal(MAX_AMOUNT))

    if (result == 1) return true

    return false
  }
}
