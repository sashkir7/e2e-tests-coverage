package ru.dodopizza.app.screens.chat

import android.view.View
import com.dodopizza.activeorder.R
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.screens.AbstractRecyclerItem

/**
 * Карточка единичного сообщения в чате
 */
class ChatMessageItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<ChatMessageItem>(
    parent = parent,
    layoutId = R.layout.ecc_item_user_text_with_file,
    viewHolderClass = null
) {

  private val text: KTextView
    get() = bind(parent) { withId(R.id.text) }

  fun verifyText(expected: String) = step(
      description = "Проверить, что текст сообщения '$expected'"
  ) { text { containsText(expected) } }
}
