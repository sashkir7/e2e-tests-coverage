package ru.dodopizza.app.screens.foryou

import com.dodopizza.order.feature.foryou.presentation.compose.promotions.ForYouPromotionsTags
import io.github.kakaocup.compose.node.element.KNode
import ru.dodopizza.app.screens.BaseComposeScreen
import ru.dodopizza.app.screens.foryou.component.OfferEmptyComponent
import ru.dodopizza.app.screens.foryou.component.PersonalOffersComponent
import ru.dodopizza.app.screens.profile.self.components.OfferDialogComponent

object ForYouPromotionsScreen : BaseComposeScreen<ForYouPromotionsScreen>(
    viewBuilderAction = { hasTestTag(ForYouPromotionsTags.SELF) }
) {

  val offers = PersonalOffersComponent
  val emptyOffersInfo = OfferEmptyComponent
  val offerDialog = OfferDialogComponent

  private val title: KNode = child { hasTestTag(ForYouPromotionsTags.TITLE) }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что отображается заголовок '$expected'"
  ) { title { assertTextEquals(expected) } }
}
