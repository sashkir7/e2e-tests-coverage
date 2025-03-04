package ru.dodopizza.app.screens.foryou.component

import com.dodopizza.order.feature.foryou.presentation.compose.promotions.ForYouOfferEmptyTags
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.screens.BaseComposeScreen

object OfferEmptyComponent : BaseComposeScreen<OfferEmptyComponent>(
    viewBuilderAction = { hasTestTag(ForYouOfferEmptyTags.SELF) }
) {

  private val title: KNode = child { hasTestTag(ForYouOfferEmptyTags.TITLE) }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что заголовок '$expected'"
  ) { title { assertTextEquals(expected) } }
}
