package ru.dodopizza.app.screens

import android.view.View
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.info.StepInfo
import io.github.kakaocup.kakao.common.builders.ViewBuilder
import io.github.kakaocup.kakao.common.views.KBaseView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerItemTypeBuilder
import io.github.kakaocup.kakao.recycler.KRecyclerView
import org.hamcrest.Matcher
import ru.dodopizza.app.kaspresso.actions.flakyStep
import ru.dodopizza.app.models.ContextStorage
import ru.dodopizza.app.models.publisher.Publisher
import ru.dodopizza.app.utils.StackTraceUtils

@Suppress("UnnecessaryAbstractClass", "UnusedPrivateProperty")
abstract class AbstractRecyclerItem<T : AbstractRecyclerItem<T>>(
  protected val parent: Matcher<View>,
  @Suppress("UnusedPrivateProperty")
  private val layoutId: Int?,
  @Suppress("UnusedPrivateProperty")
  private val viewHolderClass: Class<*>?,
) : KRecyclerItem<T>(parent) {

  protected val context: TestContext<Unit> by lazy { ContextStorage.get() }

  protected inline fun <reified E : KBaseView<E>> bind(
    parent: Matcher<View>,
    noinline builder: ViewBuilder.() -> Unit,
  ): E = registerElement(
      E::class.java.getDeclaredConstructor(
          Matcher::class.java, Function1::class.java
      ).newInstance(parent, builder)
  )

  protected fun bind(
    parent: Matcher<View>,
    builder: ViewBuilder.() -> Unit,
    itemTypeBuilder: KRecyclerItemTypeBuilder.() -> Unit,
  ): KRecyclerView = registerElement(
      KRecyclerView(parent, builder, itemTypeBuilder)
  )

  protected fun <E> registerElement(element: E): E = element.apply {
    val selfClass = this@AbstractRecyclerItem::class.java
    val elementName = StackTraceUtils.parseVariableNameFromGetter(selfClass)
    Publisher.instance.publish(selfClass, elementName)
  }

  protected fun step(
    description: String,
    action: (StepInfo) -> Unit,
  ) = context.step(description, action)

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

  fun isChecked() = perform {
    view.check(ViewAssertions.matches(ViewMatchers.isChecked()))
  }

  fun isNotChecked() = perform {
    view.check(ViewAssertions.matches(ViewMatchers.isNotChecked()))
  }
}
