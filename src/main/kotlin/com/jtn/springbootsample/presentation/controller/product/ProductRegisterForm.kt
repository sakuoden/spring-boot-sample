package com.jtn.springbootsample.presentation.controller.product

import com.jtn.springbootsample.domain.model.product.ProductName
import com.jtn.springbootsample.domain.type.amount.Amount
import com.jtn.springbootsample.presentation.validation.group.Groups
import com.jtn.springbootsample.presentation.validation.notblank.NotBlank
import java.lang.IllegalStateException
import javax.validation.GroupSequence
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Size

/**
 * 商品登録フォーム
 */
class ProductRegisterForm(
  @field:Size(
    min = MIN_LENGTH,
    max = MAX_LENGTH,
    message = "${MIN_LENGTH}文字以上、${MAX_LENGTH}文字以下で商品名を入力してください",
    groups = [Groups.Group1::class]
  )
  @field:NotBlank(message = "商品名を入力してください", groups = [Groups.Group2::class])
  val productName: String? = null,

  @field:Max(value = MAX_AMOUNT, message = "${MIN_AMOUNT}円以上、${MAX_AMOUNT}円以下の数字を入力してください", groups = [Groups.Group1::class])
  @field:Min(value = MIN_AMOUNT, message = "${MIN_AMOUNT}円以上、${MAX_AMOUNT}円以下の数字を入力してください", groups = [Groups.Group2::class])
  val amount: String? = null
) {
  companion object {
    private const val MIN_LENGTH = 1
    private const val MAX_LENGTH = 50

    private const val MIN_AMOUNT = 0L
    private const val MAX_AMOUNT = 999999L
  }

  fun productName(): ProductName =
    this.productName?.let {
      ProductName(it)
    } ?: throw IllegalStateException("productName property is null")

  fun amount(): Amount =
    this.amount?.let {
      Amount.valueOf(it)
    } ?: throw IllegalStateException("amount property is null")


  @GroupSequence(Groups.Group1::class, Groups.Group2::class, Groups.Group3::class)
  interface All {}
}
