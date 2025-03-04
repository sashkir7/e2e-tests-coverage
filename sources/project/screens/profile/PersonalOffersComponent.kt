package ru.dodopizza.app.screens.profile

import android.view.View
import com.dodopizza.activeorder.R
import com.dodopizza.order.feature.personaloffer.presentantion.PersonalOffersFragment
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.kaspresso.actions.childByText
import ru.dodopizza.app.models.enums.PersonalOffer
import ru.dodopizza.app.screens.AbstractComponent

object PersonalOffersComponent : AbstractComponent<PersonalOffersComponent>(
    layoutId = R.layout.fragment_personal_offers,
    viewClass = PersonalOffersFragment::class.java,
) {

  private val title: KTextView
    get() = bind(R.id.profile_personal_offers_title)
  private val offers: KRecyclerView
    get() = bind(R.id.profile_personal_offers_list) { itemType(::PersonalOffersItem) }

  fun verifyIsLoaded() = step(
      description = "Проверить, что блок персональных акций успешно загружен"
  ) { offers { isDisplayed() } }

  fun verifyIsNotLoaded() = step(
      description = "Проверить, что блок персональных акций не загружен"
  ) { offers { doesNotExist() } }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что отображается заголовок '$expected'"
  ) { title { hasText(expected) } }

  fun openOfferDetails(offer: PersonalOffer) = step(
      description = "Открыть детали акции '$offer'"
  ) { offerRecyclerItem(offer) { click() } }

  fun verifyOffer(offer: PersonalOffer) = step(
      description = "Проверить наличие акции '$offer'"
  ) { offerRecyclerItem(offer) { isVisible() } }

  fun verifyPersonalOfferOrderType(
    offer: PersonalOffer,
    expected: String,
  ) = step("Проверить тип заказа для акции $offer: $expected") {
    offerRecyclerItem(offer) {
      isVisible()
      orderTypeTextView { hasText(expected) }
    }
  }

  fun clickOnApplyButton(offer: PersonalOffer) = step(
      description = "Нажать на кнопку 'Применить' в акции '$offer'"
  ) { offerRecyclerItem(offer) { applyButton { click() } } }

  private fun offerRecyclerItem(
    offer: PersonalOffer,
    actions: PersonalOffersItem.() -> Unit,
  ) = offers { childByText<PersonalOffersItem>(offer, actions) }
}

private class PersonalOffersItem(
  parent: Matcher<View>,
) : KRecyclerItem<PersonalOffersItem>(parent) {
  val applyButton = KButton(parent) { withId(R.id.personal_offer_apply_button) }
  val orderTypeTextView = KTextView(parent) { withId(R.id.personal_offer_type_label) }
}
