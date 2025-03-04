package ru.dodopizza.app.screens.documents

import com.dodopizza.auth.features.legaldocuments.presentation.LegalDocumentsFragment
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.web.KWebView
import ru.dodopizza.app.R
import ru.dodopizza.app.kaspresso.actions.childByText
import ru.dodopizza.app.models.enums.LegalDocument
import ru.dodopizza.app.screens.AbstractScreen
import ru.dodopizza.app.kaspresso.actions.hasUrl
import ru.dodopizza.app.kaspresso.assertions.isNotEmpty

/**
 * Экран для отображения правовых документов компании
 */
object LegalDocumentsScreen : AbstractScreen<LegalDocumentsScreen>(
    layoutId = R.layout.fragment_legal_documents,
    viewClass = LegalDocumentsFragment::class.java,
) {

  private val webView: KWebView
    get() = KWebView { withId(R.id.web_info_fragment_web_view) }
  private val documents: KRecyclerView
    get() = bind(R.id.recycler) { itemType { KRecyclerItem<Any>(it) } }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран с документами успешно загружен"
  ) { documents { isNotEmpty() } }

  fun openDocument(document: LegalDocument) = step(
      description = "Открыть документ '$document'"
  ) { documents { childByText<KRecyclerItem<Any>>(document) { click() } } }

  fun verifyDocumentIsLoaded(document: LegalDocument) = flakySafely(
      description = "Проверить, что документ '$document' успешно загружен",
      timeoutMs = 60_000,
      allowedExceptions = setOf(RuntimeException::class.java)
  ) { webView.hasUrl(document.url) }
}
