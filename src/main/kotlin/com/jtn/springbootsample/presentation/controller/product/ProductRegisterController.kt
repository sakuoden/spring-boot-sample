package com.jtn.springbootsample.presentation.controller.product

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

/**
 * 販売品登録
 */
@Controller
@RequestMapping("product/register")
class ProductRegisterController {
  @GetMapping
  fun init(model: Model): String {
    model.addAttribute("productRegisterForm", ProductRegisterForm())

    return "product/form"
  }

  @PostMapping
  fun confirm(
    @Valid @ModelAttribute("productRegisterForm") productRegisterForm: ProductRegisterForm,
    bindingResult: BindingResult
  ): String {
    if (bindingResult.hasErrors()) {
      return "product/form"
    }

    return "product/confirm"
  }

  @InitBinder
  fun initBinder(binder: WebDataBinder) {
    binder.setAllowedFields(
      "productName.value"
    )
  }
}
