package ru.dodopizza.app.screens.update

import com.dodopizza.core.feature.stubscreen.presentation.compose.StubScreenTag
import ru.dodopizza.app.screens.BaseComposeScreen

/**
 * Экран принудительного обновления приложения
 */
object HardUpdateScreen : BaseComposeScreen<HardUpdateScreen>(
    viewBuilderAction = { hasTestTag(StubScreenTag.SELF) }
) {

  fun verifyIsLoaded() = step(
      description = "Проверить, что hard-update экран успешно загружен"
  ) { assertIsDisplayed() }
}
