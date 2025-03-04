package ru.dodopizza.app.screens.foodmenu.self

import android.view.View
import com.dodopizza.order.R
import com.dodopizza.order.feature.menu.adapters.specialoffer.SpecialOfferViewHolder
import io.github.kakaocup.kakao.image.KImageView
import org.hamcrest.Matcher
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Карточка специального предложения (баннер) на главном экране
 */
class SpecialOfferItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<SpecialOfferItem>(
    parent = parent,
    layoutId = R.layout.promo_item,
    viewHolderClass = SpecialOfferViewHolder::class.java
) {

  private val image: KImageView
    get() = bind(parent) { withId(R.id.thumbnail) }

  fun open() = step(description = "Открыть баннер") { image { click() } }

}
