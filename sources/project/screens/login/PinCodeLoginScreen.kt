package ru.dodopizza.app.screens.login

import com.dodopizza.auth.features.pincodelogin.presentation.PinCodeLoginFragment
import io.github.kakaocup.kakao.edit.KEditText
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractScreen

/**
 *  Экран для ввода пин-кода (авторизация)
 */
object PinCodeLoginScreen : AbstractScreen<PinCodeLoginScreen>(
    layoutId = R.layout.fragment_pincodelogin,
    viewClass = PinCodeLoginFragment::class.java,
) {

  const val DEFAULT_SMS_CODE = "1234"

  private val pinCodeInput: KEditText
    get() = bind(R.id.pin_entry)
  private val wrongPin: KEditText
    get() = bind(R.id.wrong_pin_label)

  fun typePinCode(pinCode: String) = step(
      description = "Ввести пин-код '$pinCode'"
  ) { pinCodeInput { typeText(pinCode) } }

  fun verifyWrongPinCodeMessageIsDisplayed() = step(
      description = "Проверить, что отображается ошибка 'Опечаточка! Проверьте код'"
  ) { wrongPin { hasText("Опечаточка! Проверьте код") } }
}
