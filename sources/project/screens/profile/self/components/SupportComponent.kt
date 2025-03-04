package ru.dodopizza.app.screens.profile.self.components

import com.dodopizza.profile.R
import com.dodopizza.profile.feature.profile.presentation.SupportDialog
import io.github.kakaocup.kakao.common.views.KView
import ru.dodopizza.app.screens.AbstractComponent

/**
 * Компонент поддержки (номер телефона, чат)
 */
object SupportComponent : AbstractComponent<SupportComponent>(
    layoutId = R.layout.support_bottom_sheet_dialog,
    viewClass = SupportDialog::class.java
) {

  private val chatButton: KView
    get() = bind(R.id.support_bottom_sheet_container_chat)

  fun clickOnChatButton() = step(
      description = "Нажать на кнопку чата"
  ) { chatButton.click() }

}
