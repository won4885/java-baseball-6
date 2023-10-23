package baseball.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    private GameResult gameResult;

    @Test
    @DisplayName("스트라이크와 볼이 없을 때 isNothing()은 true를 반환해야 한다.")
    void isNothingWithNoStrikesAndBalls() {
        gameResult = new GameResult(0, 0);
        assertThat(gameResult.isNothing()).isTrue();
    }

    @Test
    @DisplayName("스트라이크가 있을 때 isNothing()은 false를 반환해야 한다.")
    void isNothingWithStrikes() {
        gameResult = new GameResult(1, 0);
        assertThat(gameResult.isNothing()).isFalse();
    }

    @Test
    @DisplayName("볼이 있을 때 isNothing()은 false를 반환해야 한다.")
    void isNothingWithBalls() {
        gameResult = new GameResult(0, 1);
        assertThat(gameResult.isNothing()).isFalse();
    }

    @Test
    @DisplayName("스트라이크가 게임 종료 조건과 일치하면 strikeCountIsNotGameEndCondition()은 false를 반환해야 한다.")
    void strikeCountIsNotGameEndConditionWithEndCondition() {
        gameResult = new GameResult(3, 0);
        assertThat(gameResult.strikeCountIsNotGameEndCondition()).isFalse();
    }

    @Test
    @DisplayName("스트라이크가 게임 종료 조건과 일치하지 않으면 strikeCountIsNotGameEndCondition()은 true를 반환해야 한다.")
    void strikeCountIsNotGameEndConditionWithNotEndCondition() {
        gameResult = new GameResult(2, 0);
        assertThat(gameResult.strikeCountIsNotGameEndCondition()).isTrue();
    }

    @Test
    @DisplayName("볼이 있을 때 hasBalls()는 true를 반환해야 한다.")
    void hasBallsWithBalls() {
        gameResult = new GameResult(0, 2);
        assertThat(gameResult.hasBalls()).isTrue();
    }

    @Test
    @DisplayName("볼이 없을 때 hasBalls()는 false를 반환해야 한다.")
    void hasBallsWithoutBalls() {
        gameResult = new GameResult(0, 0);
        assertThat(gameResult.hasBalls()).isFalse();
    }

    @Test
    @DisplayName("스트라이크가 있을 때 hasStrikes()는 true를 반환해야 한다.")
    void hasStrikesWithStrikes() {
        gameResult = new GameResult(1, 0);
        assertThat(gameResult.hasStrikes()).isTrue();
    }

    @Test
    @DisplayName("스트라이크가 없을 때 hasStrikes()는 false를 반환해야 한다.")
    void hasStrikesWithoutStrikes() {
        gameResult = new GameResult(0, 0);
        assertThat(gameResult.hasStrikes()).isFalse();
    }

}
