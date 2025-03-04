package ru.dodopizza.app.screens.order

import android.view.View
import com.dodopizza.activeorder.R
import com.dodopizza.activeorder.feature.ordersummary.presentation.OrderSummaryMapFragment
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.check.KCheckBox
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.kaspresso.actions.childByText
import ru.dodopizza.app.models.enums.OrderStatus
import ru.dodopizza.app.models.enums.MetaProduct
import ru.dodopizza.app.screens.AbstractScreen
import ru.dodopizza.app.models.enums.StarsFeedback
import ru.dodopizza.app.screens.external.AlertDialogComponent
import ru.dodopizza.app.screens.foodmenu.components.ActiveOrderWidgetComponent

object OrderSummaryMapScreen : AbstractScreen<OrderSummaryMapScreen>(
    layoutId = R.layout.fragment_order_map,
    viewClass = OrderSummaryMapFragment::class.java,
) {

  val alertDialog = AlertDialogComponent
  val activeOrder = ActiveOrderWidgetComponent

  private val self: KView
    get() = bind(R.id.order_map)
  private val detailsInfoContainer: KView
    get() = bind(R.id.info_container)

  private val cancelButton: KButton
    get() = bind(R.id.cancel_button)
  private val cameraSection: KView
    get() = bind(R.id.camera_section)
  private val deliveryTableInfo: KTextView
    get() = bind(R.id.delivery_to_table_info)

  private val supportButton: KTextView
    get() = bind(R.id.contact_support_button)
  private val goodsSummary: KTextView
    get() = bind(R.id.order_summary_goods_summary)
  private val chatButton: KButton
    get() = bind(R.id.contact_via_chat_button)

  private val goodsList: KRecyclerView
    get() = bind(R.id.goods_list) { itemType(::GoodsItem) }

  fun verifyIsLoaded() = step(
      description = "Проверить, что экран заказа успешно загружен"
  ) { self { isDisplayed() } }

  fun expandDetails() = step(description = "Развернуть информацию о заказе") {
    verifyIsLoaded()
    swipeUp()
  }

  fun goToOrderDetails() = flakyStep(
      description = "Перейти на экран детализации заказа",
      expected = { detailsInfoContainer { doesNotExist() } }
  ) { detailsInfoContainer { click() } }

  fun clickOnCancelButton() = step(
      description = "Нажать на кнопку 'Отменить заказ'"
  ) { cancelButton { click() } }

  fun verifyCameraSection(expected: OrderStatus) {
    if (expected.isShowVideo) {
      verifyCameraSectionIsDisplayed()
    } else {
      verifyCameraSectionIsGone()
    }
  }

  fun verifyCameraSectionIsDisplayed() = step(
      description = "Проверить, что отображается блок 'Камера на кухне'"
  ) { cameraSection { isDisplayed() } }

  fun verifyCameraSectionIsGone() = step(
      description = "Проверить, что блок 'Камера на кухне' не отображается"
  ) { cameraSection { isGone() } }

  fun verifyProductsSize(expected: Int) = step(
      description = "Проверить, что в заказе '$expected' позиций"
  ) { goodsList { hasSize(expected) } }

  fun verifyCancelButtonIsDisplayed() = step(
      description = "Проверить, что кнопка 'Отменить заказ' отображается"
  ) { cancelButton { isDisplayed() } }

  fun verifyCancelButtonIsGone() = step(
      description = "Проверить, что кнопка 'Отменить заказ' не отображается"
  ) { cancelButton { isGone() } }

  fun verifyCancelButtonState(isDisplayed: Boolean) {
    if (isDisplayed) {
      verifyCancelButtonIsDisplayed()
    } else {
      verifyCancelButtonIsGone()
    }
  }

  fun verifyProducts(
    expected: List<MetaProduct>,
    ignoreOrder: Boolean = false,
  ) = step("Проверить содержимое заказа") {
    verifyProductsSize(expected.size)
    expected.forEachIndexed { index, product ->
      step("Проверить наличие продукта '$product'") {
        goodsList {
          if (ignoreOrder) {
            childByText<GoodsItem>(product) { isVisible() }
          } else {
            childAt<GoodsItem>(index) { title { hasText(product.toString()) } }
          }
        }
      }
    }
  }

  fun verifyProducts(vararg expected: MetaProduct) = verifyProducts(expected.toList())

  fun verifyDeliveryTableInfo(expected: String) = step(
      description = "Проверить, что заголовок доставки до столика '$expected'"
  ) { deliveryTableInfo { hasText(expected) } }

  fun verifyOrderSummary(expected: String) = step(
      description = "Проверить, что сумма заказа '$expected'"
  ) { goodsSummary { hasText(expected) } }

  fun clickOnContactSupportButton() = step(
      description = "Нажать на кнопку 'Связаться с поддержкой'"
  ) { supportButton { click() } }

  fun clickOnContactViaChatButton() = step(
      description = "Нажать на кнопку 'Чат'"
  ) { chatButton { click() } }

  fun clickOnStars(stars: StarsFeedback) =
    step("Поставить '${stars.value}' звезд") {
      expandDetails()
      KCheckBox { withId(stars.viewId) }.click()
    }

  fun verifyStarsFeedbackDoesNotExist() =
    step("Проверить, что звезды для оценки заказа не отображаются") {
      StarsFeedback.entries.forEach { star ->
        KCheckBox { withId(star.viewId) }.doesNotExist()
      }
    }
}

private class GoodsItem(
  parent: Matcher<View>,
) : KRecyclerItem<GoodsItem>(parent) {
  val title = KTextView(parent) { withId(R.id.label_title) }
}
