package com.jtn.springbootsample.presentation.controller.product

import com.jtn.springbootsample.domain.model.product.ProductName
import javax.validation.Valid

/**
 * 商品登録フォーム
 */
class ProductRegisterForm(
  @field:Valid
  val productName: ProductName = ProductName("")
)
