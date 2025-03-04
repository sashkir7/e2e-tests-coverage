package ru.dodopizza.app.screens.foryou.component

import com.dodopizza.activeorder.R
import com.dodopizza.order.feature.menu.adapters.menuitem.ForYouPromotionsMenuItemVH
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.models.enums.PersonalOffer
import ru.dodopizza.app.screens.AbstractComponent
import ru.dodopizza.app.screens.common.DComposeButton

object PersonalOffersComponent : AbstractComponent<PersonalOffersComponent>(
    layoutId = R.layout.item_personal_offer,
    viewClass = ForYouPromotionsMenuItemVH::class.java
) {

  private val showMoreButton: KButton
    get() = bind(R.id.personal_offer_show_more_button)

  private val applyButton = DComposeButton(R.id.personal_offer_apply_button)

  fun verifyTitle(offer: PersonalOffer) = step(
      description = "Проверить, что отображается заголовок '$offer'"
  ) {
    bindNamed<KTextView>(
        name = "title",
        builder = {
          withId(R.id.personal_offer_name)
          withText(offer.toString())
        }
    ).isDisplayed()
  }

  fun clickOnApplyButton() = step(
      description = "Нажать на кнопку 'Применить' в акции"
  ) { applyButton { performClick() } }

  fun openOfferDetails() = step(
      description = "Открыть детали акции"
  ) { showMoreButton { click() } }

  fun verifyButtonText(expected: String) = step(
      description = "Проверить текст на кнопке '$expected'"
  ) { applyButton { verifyText(expected) } }
}
