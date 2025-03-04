package ru.dodopizza.app.screens.profile.self

import android.view.View
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.dodopizza.app.R
import com.dodopizza.profile.feature.profile.presentation.adapter.loyaltymissions.LoyaltyMissionViewHolder
import ru.dodopizza.app.screens.AbstractRecyclerItem
import ru.dodopizza.app.screens.common.DComposeButton

/**
 * Карточка миссии лояльности с виджетами для заполенения
 */
class LoyaltyMissionItem(
  parent: Matcher<View>,
) : AbstractRecyclerItem<LoyaltyMissionItem>(
    parent = parent,
    layoutId = R.layout.item_loyalty_mission,
    viewHolderClass = LoyaltyMissionViewHolder::class.java
) {

  private val missionImage: KImageView
    get() = bind(parent) { withId(R.id.loyalty_mission_image) }
  private val missionReward: KTextView
    get() = bind(parent) { withId(R.id.loyalty_mission_reward) }
  private val missionTitle: KTextView
    get() = bind(parent) { withId(R.id.loyalty_mission_title) }
  private val missionButton = DComposeButton(R.id.loyalty_mission_button)

  fun open() = step(
      description = "Открыть детали миссии"
  ) { missionImage { click() } }

  fun verifyReward(expected: String) =
    step("Проверить, что награда за миссию '$expected'") {
      missionReward { hasText(expected) }
    }

  fun verifyTitle(expected: String) = step(
      description = "Проверить, что название миссии '$expected'"
  ) { missionTitle { hasText(expected) } }

  fun verifyActionButtonText(expected: String) = step(
      description = "Проверить, что текст кнопки действия '$expected'"
  ) { missionButton { verifyText(expected) } }
}
