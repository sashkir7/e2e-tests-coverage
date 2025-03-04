package ru.dodopizza.app.screens.chat

import com.dodopizza.activeorder.R
import com.dodopizza.chat.feature.chat.presentation.NativeChatFragment
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import ru.dodopizza.app.screens.AbstractScreen

/**
 * Экран чата
 */
object ChatScreen : AbstractScreen<ChatScreen>(
    layoutId = R.layout.fragment_native_chat,
    viewClass = NativeChatFragment::class.java,
) {

  private val chatContainer: KView
    get() = bind(R.id.native_chat_container)
  private val inputMessage: KEditText
    get() = bind(R.id.input_edit_view)
  private val sendButton: KButton
    get() = bind(R.id.send_message)

  private val messages: KRecyclerView
    get() = bind(R.id.chatItemsRecycler) { itemType { ChatMessageItem(it) } }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран чата успешно загружен"
  ) { chatContainer { isDisplayed() } }

  fun sendMessage(message: String) =
    step(description = "Отправить сообщение '$message'") {
      inputMessage { replaceText(message) }
      sendButton { click() }
    }

  fun message(
    position: Int,
    action: ChatMessageItem.() -> Unit,
  ) = step("Получить сообщение на позиции '$position'") {
    messages { childAt<ChatMessageItem>(position, action) }
  }
}
