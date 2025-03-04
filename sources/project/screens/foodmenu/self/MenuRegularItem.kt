package ru.dodopizza.app.screens.foodmenu.self

import android.view.View
import com.dodopizza.order.R
import com.dodopizza.order.feature.menu.adapters.menuitem.MenuItemVH
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Карточка обычного (регулярного) продукта в меню
 */
class MenuRegularItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<MenuRegularItem>(
    parent = parent,
    layoutId = R.layout.menu_item_product_list,
    viewHolderClass = MenuItemVH.MenuItemRegularVH::class.java
) {

  private val rawPrice: KTextView
    get() = bind(parent) { withId(R.id.menu_item_raw_price) }
  private val title: KTextView
    get() = bind(parent) { withId(R.id.menu_item_product_title) }
  private val selectButton: KButton
    get() = bind(parent) { withId(R.id.menu_item_select_button) }
  private val timeRemain: KTextView
    get() = bind(parent) { withId(R.id.personal_price_time_remain) }

  fun open() = step(description = "Открыть карточку") { click() }

  fun clickOnSelectButton() = step(
      description = "Нажать на кнопку выбора товара"
  ) { selectButton { click() } }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что заголовок '$expected'"
  ) { title { hasText(expected) } }

  fun verifyTitleWithTag(expected: MetaProduct) = verifyTitle("$expected TAG_ID")

  fun verifyTitle(expected: MetaProduct) = verifyTitle(expected.toString())

  fun verifyRawPrice(expected: String) = step(
      description = "Проверить, что изначальная цена '$expected'"
  ) { rawPrice { hasText(expected) } }

  fun verifyFinalPrice(expected: String) = step(
      description = "Проверить, что финальная цена '$expected'"
  ) { selectButton { hasText(expected) } }

  fun verifyTimeRemain(expected: String) = step(
      description = "Проверить, что время действия скидки '$expected'"
  ) { timeRemain { hasText(expected) } }

  fun verifyIsUnavailable() =
    step("Проверить, что продукт недоступен") {
      selectButton {
        isDisabled()
        hasText("Раскупили")
      }
    }
}
