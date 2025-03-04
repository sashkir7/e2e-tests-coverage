package ru.dodopizza.app.screens.profile.mission.detail

import com.dodopizza.loyalty.R
import com.dodopizza.loyalty.missions.LoyaltyMissionDetailsFragment
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.text.KTextView
import ru.dodopizza.app.screens.AbstractScreen
import ru.dodopizza.app.screens.common.DComposeButton

/**
 * Экран с подробностями миссии программы лояльности
 */
object LoyaltyMissionDetailsScreen : AbstractScreen<LoyaltyMissionDetailsScreen>(
    layoutId = R.layout.fragment_loyalty_mission_details,
    viewClass = LoyaltyMissionDetailsFragment::class.java
) {

  private val input: KEditText
    get() = bind(R.id.loyalty_mission_detail_input)
  private val answerCounter: KTextView
    get() = bind(R.id.loyalty_mission_detail_answer_counter)
  private val rewardText: KTextView
    get() = bind(R.id.loyalty_mission_detail_reward)
  private val image: KImageView
    get() = bind(R.id.loyalty_mission_detail_image)
  private val title: KTextView
    get() = bind(R.id.loyalty_mission_detail_title)
  private val conditions: KTextView
    get() = bind(R.id.loyalty_mission_detail_conditions)
  private val progressView: KView
    get() = bind(R.id.loyalty_mission_detail_progress_container)
  private val expireTitle: KTextView
    get() = bind(R.id.loyalty_mission_detail_expire_title)
  private val doneTitle: KTextView
    get() = bind(R.id.loyalty_mission_detail_done_title_button)
  private val statusDesc: KTextView
    get() = bind(R.id.loyalty_mission_detail_status_description)

  private val sendButton = DComposeButton(R.id.loyalty_mission_detail_send_button)

  fun setAnswer(answer: String) = step(
      description = "Ввести ответ '$answer'"
  ) { input { replaceText(answer) } }

  fun verifyAnswerInputIsDisabled() = step(
      description = "Проверить, что поле ввода ответа заблокировано"
  ) { input { isDisabled() } }

  fun verifyAnswerCounterText(expected: String) = step(
      description = "Проверить, что значение счетчика символов '$expected'"
  ) { answerCounter { hasText(expected) } }

  fun clickOnSendButton() = step(
      description = "Нажать на кнопку 'Отправить'"
  ) { sendButton { performClick() } }

  fun verifySendButtonText(expected: String) = step(
      description = "Проверить, что текст кнопки отправки '$expected'"
  ) { sendButton { verifyText(expected) } }

  fun verifySendButtonIsDisabled() = step(
      description = "Проверить, что кнопка 'Отправить' неактивна"
  ) { sendButton { assertIsNotEnabled() } }

  fun verifySendButtonIsEnabled() = step(
      description = "Проверить, что кнопка 'Отправить' активна"
  ) { sendButton { assertIsEnabled() } }

  fun verifyStatusDescription(expected: String) = step(
      description = "Проверить, что статус миссии '$expected'"
  ) { statusDesc { hasText(expected) } }

  fun verifyProgressBarIsDisplayed() = step(
      description = "Проверить, что отображается прогресс-бар"
  ) { progressView { isDisplayed() } }

  fun verifyMissionReward(expected: String) = step(
      description = "Проверить, что награда миссии '$expected'"
  ) { rewardText { hasText(expected) } }

  fun verifyMissionImageIsDisplayed() = step(
      description = "Проверить, что изображение миссии отображается"
  ) { image { isDisplayed() } }

  fun verifyMissionTitle(expected: String) = step(
      description = "Проверить, что заголовок миссии '$expected'"
  ) { title { hasText(expected) } }

  fun verifyMissionConditions(expected: String) = step(
      description = "Проверить, что условия миссии '$expected'"
  ) { conditions { hasText(expected) } }

  fun verifyMissionExpireTitle(expected: String) = step(
      description = "Проверить, что заголовок об истечении миссии '$expected'"
  ) { expireTitle { hasText(expected) } }

  fun verifyMissionDoneTitle(expected: String) = step(
      description = "Проверить, что сообщение о завершении миссии '$expected'"
  ) { doneTitle { hasText(expected) } }
}
