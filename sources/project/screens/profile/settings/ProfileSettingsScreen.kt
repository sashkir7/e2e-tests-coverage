package ru.dodopizza.app.screens.profile.settings

import com.dodopizza.profile.feature.profilesettings.presentation.ProfileSettingsFragment
import io.github.kakaocup.kakao.edit.KTextInputLayout
import io.github.kakaocup.kakao.switch.KSwitch
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.R
import ru.dodopizza.app.kaspresso.assertions.containsTexts
import ru.dodopizza.app.screens.AbstractScreen
import ru.dodopizza.app.screens.external.AlertDialogComponent

/**
 *  Экран настроек в профиле
 */
object ProfileSettingsScreen : AbstractScreen<ProfileSettingsScreen>(
    layoutId = R.layout.fragment_profile_settings,
    viewClass = ProfileSettingsFragment::class.java,
) {

  val alertDialog = AlertDialogComponent
  val datePickerDialog = DatePickerDialogComponent
  val confirmDeletionProfile = ConfirmDeletionProfileComponent

  private val logoutButton: KButton
    get() = bind(R.id.profile_settings_logout_button)
  private val legalDocumentButton: KButton
    get() = bind(R.id.profile_settings_legal_documents)
  private val deleteAccountButton: KButton
    get() = bind(R.id.profile_settings_delete_account_button)

  private val cancelButton: KButton
    get() = bind(R.id.cancel)
  private val confirmButton: KButton
    get() = bind(R.id.confirm)
  private val birthday: KTextInputLayout
    get() = bind(R.id.profile_settings_birthday_field)
  private val snackBarText: KTextView
    get() = bind(R.id.snackbar_text)

  private val allowNotifications: KSwitch
    get() = bind(R.id.profile_settings_switch_receive_sms)
  private val appVersion: KTextView
    get() = bind(R.id.profile_settings_version)

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран настроек профиля успешно загружен"
  ) { logoutButton { isDisplayed() } }

  fun clickOnLogoutButton() = flakyStep(
      description = "Нажать на кнопку 'Выйти из профиля'",
      expectedTimeoutMs = 10_000,
      expected = { logoutButton { doesNotExist() } }
  ) { logoutButton { click() } }

  fun clickOnDeleteAccountButton() = step(
      description = "Нажать на кнопку 'Удалить аккаунт'"
  ) { deleteAccountButton { click() } }

  fun clickOnLegalDocumentButton() = step(
      description = "Нажать на кнопку 'Правовые документы'"
  ) { legalDocumentButton { click() } }

  fun clickOnBirthdayInput() = step(
      description = "Нажать на поле 'День рождение'"
  ) { birthday { click() } }

  fun clickOnCancelButtonOnSelectBirthdayAlert() = step(
      description = "Нажать на кнопку 'Отмена' на алерте выбора даты рождения",
  ) {
    cancelButton { click() }
    pressBack()
  }

  fun clickOnConfirmButtonOnSelectBirthdayAlert() = step(
      description = "Нажать на кнопку 'Подтвердить' на алерте выбора даты рождения",
  ) {
    confirmButton { click() }
    pressBack()
  }

  fun verifyBirthday(expected: String) = step(
      description = "Проверить, что день рождения '$expected'"
  ) { birthday { edit { hasText(expected) } } }

  fun verifyNotificationIsAllowed() = step(
      description = "Проверить, что переключатель 'Разрешить уведомления' активирован"
  ) { allowNotifications { isChecked() } }

  fun verifyNotificationIsNotAllowed() = step(
      description = "Проверить, что переключатель 'Разрешить уведомления' не активирован"
  ) { allowNotifications { isNotChecked() } }

  fun verifyApplicationVersion() = step(
      description = "Проверить, что отображается версия приложения"
  ) { appVersion { containsTexts("Версия", "сборка") } }

  fun verifyCannotChangeBirthdayBannerIsDisplayed() = step(
      description = "Проверить, что отображается уведомление о невозможности смены дня рождения"
  ) { snackBarText { hasText(R.string.birthday_already_set) } }
}
