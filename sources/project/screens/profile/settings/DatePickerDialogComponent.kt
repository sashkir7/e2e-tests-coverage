package ru.dodopizza.app.screens.profile.settings

import com.dodopizza.profile.feature.profilesettings.presentation.DatePickerDialogFragment
import io.github.kakaocup.kakao.text.KButton
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractComponent

/**
 * Компонент выбора даты рождения пользователя
 */
object DatePickerDialogComponent : AbstractComponent<DatePickerDialogComponent>(
    layoutId = R.layout.dialog_fragment_date_picker,
    viewClass = DatePickerDialogFragment::class.java
) {

  private val okButton: KButton
    get() = bind(R.id.ok_button)
  private val cancelButton: KButton
    get() = bind(R.id.cancel_button)

  fun clickOnOkButton() = flakyStep(
      description = "На компоненте выбора даты рождения нажать на кнопку 'Ок'",
      expected = { okButton { doesNotExist() } }
  ) { okButton { click() } }

  fun clickOnCancelButton() = flakyStep(
      description = "На компоненте выбора даты рождения нажать на кнопку 'Отмена'",
      expected = { cancelButton { doesNotExist() } }
  ) { cancelButton { click() } }
}
