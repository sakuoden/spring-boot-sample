package com.jtn.springbootsample.domain.model.product

import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * 商品名
 */
class ProductName(
  @field:NotBlank(message = "商品名を入力してください")
  @field:Size(min =  MIN_SIZE , max = MAX_SIZE, message = "1文字以上、50文字以下で入力してください")
  val value: String
) {
  companion object {
    private const val MIN_SIZE = 1
    private const val MAX_SIZE = 50
    private const val FULL_WIDTH_BLANK = "　"
  }

  fun value(): String = this.value

  fun sameValue(other: ProductName): Boolean = this.value == other.value

  @AssertTrue(message = "商品名を入力してください")
  private fun isNotFullWidthBlank(): Boolean {
    if (this.value == FULL_WIDTH_BLANK) {
      return false
    }

    return true
  }
}
