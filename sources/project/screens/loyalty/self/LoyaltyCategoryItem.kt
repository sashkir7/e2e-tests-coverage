package ru.dodopizza.app.screens.loyalty.self

import android.view.View
import com.dodopizza.loyalty.R
import com.dodopizza.loyalty.menu.adapter.LoyaltyCategoryViewHolder
import org.hamcrest.Matcher
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Позиция категории меню лояльности (RecyclerViewItem)
 * Например, категория 'Соусы'
 */
class LoyaltyCategoryItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<LoyaltyCategoryItem>(
    parent = parent,
    layoutId = R.layout.item_loyalty_category,
    viewHolderClass = LoyaltyCategoryViewHolder::class.java
)
