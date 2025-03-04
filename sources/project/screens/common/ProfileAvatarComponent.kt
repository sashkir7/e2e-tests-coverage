package ru.dodopizza.app.screens.common

import com.dodopizza.components.ProfileAvatarTags.PROFILE_AVATAR_BALANCE_TAG
import com.dodopizza.components.ProfileAvatarTags.PROFILE_AVATAR_DODOCOINS_COUNT_TAG
import com.dodopizza.components.ProfileAvatarTags.PROFILE_AVATAR_SELF
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.screens.BaseComposeScreen

/**
 * Компонент аватара пользователя на экране профиля (compose)
 */
object ProfileAvatarComponent : BaseComposeScreen<ProfileAvatarComponent>(
    viewBuilderAction = { hasTestTag(PROFILE_AVATAR_SELF) }
) {

  private val dodocoinsBadge: KNode = child { hasTestTag(PROFILE_AVATAR_BALANCE_TAG) }
  private val dodocoinsCount: KNode = child {
    hasTestTag(PROFILE_AVATAR_DODOCOINS_COUNT_TAG)
    useUnmergedTree = true
  }

  fun verifyDodocoinsBadgeIsNotDisplayed() = step(
      description = "Проверить, что значок додокоинов не отображается"
  ) { dodocoinsBadge { assertIsNotDisplayed() } }

  fun clickOnSelf() = step(
      description = "Нажать на аватарку пользователя"
  ) { performClick() }

  fun verifyDodocoinsCount(expected: String) = step(
      description = "Проверить, что количество додокоинов '$expected'"
  ) { dodocoinsCount { assertTextEquals(expected) } }
}
