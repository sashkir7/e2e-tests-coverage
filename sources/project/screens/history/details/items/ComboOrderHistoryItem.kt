package ru.dodopizza.app.screens.history.details.items

import android.view.View
import com.dodopizza.orderhistory.R
import com.dodopizza.orderhistory.feature.orderhistory.presentation.orderhistorydetails.adapter.ComboOrderItemVH
import io.github.kakaocup.kakao.recycler.KRecyclerView
import org.hamcrest.Matcher
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**к
 * Карточа комбо товара в истории
 */
class ComboOrderHistoryItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<SimpleOrderHistoryItem>(
    parent = parent,
    layoutId = R.layout.history_combo_order_item,
    viewHolderClass = ComboOrderItemVH::class.java
) {

  private val products: KRecyclerView
    get() = bind(
        parent = parent,
        builder = { withId(R.id.recycler_view) },
        itemTypeBuilder = { itemType { ComboProductItem(it) } }
    )

  fun verifyProducts(vararg expected: MetaProduct) =
    step(description = "Проверить содержимое комбо-набора") {
      verifyComboSize(expected.size)
      expected.forEachIndexed { position, product ->
        verifyProductByPosition(position, product)
      }
    }

  fun verifyComboSize(expected: Int) = step(
      description = "Проверить, что в комбо '$expected' позиций"
  ) { products { hasSize(expected) } }

  private fun verifyProductByPosition(
    position: Int,
    expected: MetaProduct,
  ) = step("Проверить продукт '$expected' на позиции '$position'") {
    products { childAt<ComboProductItem>(position) { verifyTitle(expected) } }
  }
}
