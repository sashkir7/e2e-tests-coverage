package ru.dodopizza.app.screens.login

import com.dodopizza.auth.features.phonenumberlogin.presentation.PhoneNumberLoginFragment
import io.github.kakaocup.kakao.check.KCheckBox
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractScreen
import ru.dodopizza.app.screens.common.DComposeButton

/**
 *  Экран для ввода номера телефона (авторизация)
 */
object PhoneNumberLoginScreen : AbstractScreen<PhoneNumberLoginScreen>(
    layoutId = R.layout.fragment_phonenumberlogin,
    viewClass = PhoneNumberLoginFragment::class.java,
) {

  const val DEFAULT_PHONE_NUMBER = "9999999999"
  const val SIXTH_PHONE_NUMBER = "6666666666"

  private val phoneNumberInput: KEditText
    get() = bind(R.id.phone_number_login_input)
  private val countryPrefix: KTextView
    get() = bind(R.id.phone_number_country_prefix)
  private val termsCheckbox: KCheckBox
    get() = bind(R.id.terms_checkbox)

  private val continueButton = DComposeButton(R.id.phone_number_login_next_button)

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран ввода номера телефона успешно загружен"
  ) { phoneNumberInput { isDisplayed() } }

  fun typePhoneNumber(phoneNumber: String) = step(
      description = "Ввести номер телефона '$phoneNumber'"
  ) { phoneNumberInput { typeText(phoneNumber) } }

  fun clickOnContinueButton() = step(
      description = "Нажать на кнопку 'Продолжить'"
  ) { continueButton { performClick() } }

  fun clickOnCountryPrefixView() = flakyStep(
      description = "Нажать на область с префиксом страны",
      expected = { countryPrefix { doesNotExist() } }
  ) { countryPrefix { click() } }

  fun verifyTermsIsChecked() = step(
      description = "Проверить, что условия приняты"
  ) { termsCheckbox { isChecked() } }

  fun verifyTermsIsNotChecked() = step(
      description = "Проверить, что условия не приняты"
  ) { termsCheckbox { isNotChecked() } }

  fun selectTermsAgreement() = step(
      description = "Выбрать согласие с условиями"
  ) { termsCheckbox { setChecked(true) } }

  fun verifyContinueButtonIsDisabled() = step(
      description = "Проверить, что кнопка 'Продолжить' неактивна"
  ) { continueButton { assertIsNotEnabled() } }

  fun verifyContinueButtonIsEnabled() = step(
      description = "Проверить, что кнопка 'Продолжить' активна"
  ) { continueButton { assertIsEnabled() } }
}
