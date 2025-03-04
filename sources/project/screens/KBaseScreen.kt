package ru.dodopizza.app.screens

import android.view.View
import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.info.StepInfo
import io.github.kakaocup.kakao.common.builders.ViewBuilder
import io.github.kakaocup.kakao.common.views.KBaseView
import io.github.kakaocup.kakao.recycler.KRecyclerItemTypeBuilder
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KSnackbar
import org.hamcrest.Matcher
import ru.dodopizza.app.kaspresso.actions.flakyStep
import ru.dodopizza.app.kaspresso.actions.softDownSwipe
import ru.dodopizza.app.kaspresso.actions.softRightSwipe
import ru.dodopizza.app.kaspresso.actions.swipeUp
import ru.dodopizza.app.models.ContextStorage
import ru.dodopizza.app.models.publisher.Publisher
import ru.dodopizza.app.utils.StackTraceUtils.parseVariableNameFromGetter

@Suppress("UnnecessaryAbstractClass")
abstract class KBaseScreen<T : KBaseScreen<T>>(
  override val layoutId: Int?,
  override val viewClass: Class<*>?,
) : KScreen<T>() {

  protected val selfClass = this::class.java
  protected val context: TestContext<Unit> by lazy { ContextStorage.get() }

  private val snackbar = KSnackbar()

  fun verifyToastTitle(expected: String) =
    step(description = "Проверить, что заголовок toast-сообщения '$expected'") {
      snackbar {
        isDisplayed()
        text { hasText(expected) }
      }
    }

  protected inline fun <reified E : KBaseView<E>> bindNamed(
    name: String,
    noinline builder: ViewBuilder.() -> Unit,
  ): E = registerElement(
      name = name,
      element = E::class.java.getDeclaredConstructor(Function1::class.java)
          .newInstance(builder)
  )

  protected inline fun <reified E : KBaseView<E>> bind(
    noinline builder: ViewBuilder.() -> Unit,
  ): E = bindNamed(parseVariableNameFromGetter(selfClass), builder)

  protected inline fun <reified E : KBaseView<E>> bind(
    parent: Matcher<View>,
    noinline builder: ViewBuilder.() -> Unit,
  ): E = bindNamed(
      name = parseVariableNameFromGetter(selfClass),
      builder = {
        builder()
        isDescendantOfA { withMatcher(parent) }
      }
  )

  protected inline fun <reified E : KBaseView<E>> bind(id: Int): E = bind { withId(id) }

  protected fun bindNamed(
    name: String,
    builder: ViewBuilder.() -> Unit,
    itemTypeBuilder: KRecyclerItemTypeBuilder.() -> Unit,
  ): KRecyclerView = registerElement(name, KRecyclerView(builder, itemTypeBuilder))

  protected fun bind(
    builder: ViewBuilder.() -> Unit,
    itemTypeBuilder: KRecyclerItemTypeBuilder.() -> Unit,
  ): KRecyclerView = bindNamed(parseVariableNameFromGetter(selfClass), builder, itemTypeBuilder)

  protected fun bind(
    id: Int,
    itemTypeBuilder: KRecyclerItemTypeBuilder.() -> Unit,
  ): KRecyclerView = bind(builder = { withId(id) }, itemTypeBuilder)

  protected fun step(
    description: String,
    action: (StepInfo) -> Unit,
  ) = context.step(description, action)

  @Suppress("UNCHECKED_CAST")
  protected fun flakyStep(
    description: String,
    timeoutMs: Long = 25_000,
    expectedTimeoutMs: Long = 1_000,
    expected: T.() -> Unit,
    action: T.() -> Unit,
  ) = step(description) {
    context.flakyStep(
        self = this as T,
        timeoutMs = timeoutMs,
        expectedTimeoutMs = expectedTimeoutMs,
        expected = expected,
        action = action
    )
  }

  protected fun <T> flakySafely(
    timeoutMs: Long? = null,
    intervalMs: Long? = null,
    allowedExceptions: Set<Class<out Throwable>>? = null,
    failureMessage: String? = null,
    action: () -> T,
  ) = context.flakySafely(timeoutMs, intervalMs, allowedExceptions, failureMessage, action)

  @Suppress("LongParameterList")
  protected fun <T> flakySafely(
    description: String,
    timeoutMs: Long? = null,
    intervalMs: Long? = null,
    allowedExceptions: Set<Class<out Throwable>>? = null,
    failureMessage: String? = null,
    action: () -> T,
  ) = step(description) {
    flakySafely(timeoutMs, intervalMs, allowedExceptions, failureMessage, action)
  }

  protected fun <T> continuously(
    timeoutMs: Long? = null,
    intervalMs: Long? = null,
    failureMessage: String? = null,
    action: () -> T,
  ) = context.continuously(timeoutMs, intervalMs, failureMessage, action)

  protected fun swipeUp() = context.swipeUp()
  protected fun softRightSwipe() = context.softRightSwipe()
  protected fun softDownSwipe() = context.softDownSwipe()

  protected fun <E> registerElement(
    name: String,
    element: E,
  ): E = element.apply { Publisher.instance.publish(selfClass, name) }
}
