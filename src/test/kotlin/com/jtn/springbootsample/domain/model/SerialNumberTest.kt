package com.jtn.springbootsample.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator


internal class SerialNumberTest {
  private lateinit var validator: Validator

  @BeforeEach
  fun setUp() {
    validator = Validation.buildDefaultValidatorFactory().validator
  }

  @Nested
  inner class NotBlankTest {

    /**
     * Blank = " "
     * 半角空白のときに @NotBlank が適用される
     */
    @Test
    fun whenBlankValue_thenNotBlankAnnotationApplies() {
      val serialNumber = SerialNumber(" ")

      val violations: Set<ConstraintViolation<SerialNumber>> = validator.validateProperty(serialNumber, "value")

      val appliedAnnotations: Set<String?> =
        violations.map {
          it.constraintDescriptor.annotation.annotationClass.simpleName
        }.toSet()

      assertThat(appliedAnnotations).contains("NotBlank")
    }

    /**
     * Empty = ""
     * 空文字のときに @NotBlank が適用される
     */
    @Test
    fun whenEmpty_thenNotBlankAnnotationApplies() {
      val serialNumber = SerialNumber("")

      val violations: Set<ConstraintViolation<SerialNumber>> = validator.validateProperty(serialNumber, "value")

      val appliedAnnotations: Set<String?> =
        violations.map {
          it.constraintDescriptor.annotation.annotationClass.simpleName
        }.toSet()

      assertThat(appliedAnnotations).contains("NotBlank")
    }

    /**
     * Full-Width Blank = "　"
     * 全角の空白のときには @NotBlank が適用されない
     */
    @Test
    fun whenFullWidthEmpty_thenNotBlankDoesNotAnnotationApplies() {
      val serialNumber = SerialNumber("　")

      val violations: Set<ConstraintViolation<SerialNumber>> = validator.validateProperty(serialNumber, "value")

      val appliedAnnotations: Set<String?> =
        violations.map {
          it.constraintDescriptor.annotation.annotationClass.simpleName
        }.toSet()

      assertThat(appliedAnnotations).doesNotContain("NotBlank")
    }
  }


  @Nested
  inner class PatternTest {
    /**
     * 半角英数字12文字のときに @Pattern が適用されない
     */
    @Test
    fun when12HalfWidthNumbers_thenPatternAnnotationApplies() {
      val serialNumber = SerialNumber("123456789012")

      val violations: Set<ConstraintViolation<SerialNumber>> = validator.validateProperty(serialNumber, "value")

      val appliedAnnotations: Set<String?> =
        violations.map {
          it.constraintDescriptor.annotation.annotationClass.simpleName
        }.toSet()

      assertThat(appliedAnnotations).doesNotContain("Pattern")
    }

    /**
     * 半角英数字13文字のときに @Pattern が適用される
     */
    @Test
    fun when13HalfWidthNumbers_thenPatternAnnotationApplies() {
      val serialNumber = SerialNumber("1234567890123")

      val violations: Set<ConstraintViolation<SerialNumber>> = validator.validateProperty(serialNumber, "value")

      val appliedAnnotations: Set<String?> =
        violations.map {
          it.constraintDescriptor.annotation.annotationClass.simpleName
        }.toSet()

      assertThat(appliedAnnotations).contains("Pattern")
    }

    /**
     * 全角英数字12文字のときに @Pattern が適用される
     */
    @Test
    fun when12FullWidthNumbers_thenPatternAnnotationApplies() {
      val serialNumber = SerialNumber("１２３４５６７８９０１２")

      val violations: Set<ConstraintViolation<SerialNumber>> = validator.validateProperty(serialNumber, "value")

      val appliedAnnotations: Set<String?> =
        violations.map {
          it.constraintDescriptor.annotation.annotationClass.simpleName
        }.toSet()

      assertThat(appliedAnnotations).contains("Pattern")
    }
  }


  @Nested
  inner class AssertTrueTest {
    /**
     * 禁止されている番号(222222222222)のときに @AssertTrue が適用される
     */
    @Test
    fun whenProhibitedNumber_thenAssertTrueAnnotationApplies() {
      val serialNumber = SerialNumber("222222222222")

      val violations: Set<ConstraintViolation<SerialNumber>> = validator.validate(serialNumber)

      val appliedAnnotations: Set<String?> =
        violations.map {
          it.constraintDescriptor.annotation.annotationClass.simpleName
        }.toSet()

      assertThat(appliedAnnotations).contains("AssertTrue")
    }
  }
}