package ru.dodopizza.app.screens.product.constructor.combo

import android.view.View
import com.dodopizza.order.feature.product.card.combo.customize.presentation.CustomizeToppingViewHolder
import org.hamcrest.Matcher
import ru.dodopizza.app.R
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Карточка дополнительного ингредиента в конструкторе комбо по шаблонам (RecyclerViewItem)
 */
class CustomizeToppingItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<CustomizeToppingItem>(
    parent = parent,
    layoutId = R.layout.item_combo_customize_builder,
    viewHolderClass = CustomizeToppingViewHolder::class.java
)
