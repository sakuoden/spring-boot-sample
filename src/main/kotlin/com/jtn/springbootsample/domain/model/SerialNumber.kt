package com.jtn.springbootsample.domain.model

import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

/**
 * シリアルナンバー
 */
class SerialNumber(
  @field:NotBlank(message = "シリアルナンバーを入力してください")
  @field:Pattern(regexp = "^\\d{12}$", message = "半角数字12桁で入力してください")
  private val value: String
) {
  companion object {
    private const val PROHIBITED_NUMBER: String = "222222222222"
  }

  fun value(): String = this.value

  fun sameValue(other: SerialNumber): Boolean = this.value == other.value

  @AssertTrue(message = "このシリアルナンバーは禁止されています")
  private fun isNotProhibited(): Boolean {
    if (this.value == PROHIBITED_NUMBER) {
      return false
    }

    return true
  }
}
