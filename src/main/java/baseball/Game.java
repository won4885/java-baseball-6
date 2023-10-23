package baseball;

import static baseball.constant.NumberConstants.COUNT_ZERO;
import static baseball.constant.NumberConstants.GAME_END_CONDITION;
import static baseball.output.GameOutput.printCorrectBallCount;
import static baseball.output.GameOutput.printCorrectStrikeCount;
import static baseball.output.GameOutput.printNewLine;
import static baseball.output.GameOutput.printStrikeThree;
import static baseball.output.GameOutput.printlnEndMessage;
import static baseball.output.GameOutput.printlnNothing;
import static baseball.output.GameOutput.printlnStartMessage;

import baseball.computer.RandomComputerNumberGenerator;
import baseball.user.UserInput;

public class Game {

    private final UserInput userInput = new UserInput();

    public Game() {
        printlnStartMessage();
    }

    public void start() {
        GameStatus gameStatus = GameStatus.RESTART;
        while (gameStatus == GameStatus.RESTART) {
            playGameRound(generateComputerNumberString());
            gameStatus = determineRestart();
        }
    }

    private String generateComputerNumberString() {
        return new RandomComputerNumberGenerator().toString();
    }

    private void playGameRound(String computerNumberString) {
        Result result = new Result(COUNT_ZERO, COUNT_ZERO);
        while (result.strikeCount != GAME_END_CONDITION) {
            result = makeResultCount(userInput.readNumberString(), computerNumberString);
            displayResult(result);
        }

        printStrikeThree(result.strikeCount);
    }

    private Result makeResultCount(String userNumberString, String computerNumberString) {
        int strikeCount = countStrikes(userNumberString, computerNumberString);
        int ballCount = countAllBalls(userNumberString, computerNumberString) - strikeCount;

        return new Result(strikeCount, ballCount);
    }

    private int countStrikes(String userNumberString, String computerNumberString) {
        int count = 0;
        for (int i = 0; i < userNumberString.length(); i++) {
            if (userNumberString.charAt(i) == computerNumberString.charAt(i)) {
                count++;
            }
        }

        return count;
    }

    private int countAllBalls(String userNumberString, String computerNumberString) {
        int count = 0;
        for (char c : userNumberString.toCharArray()) {
            if (computerNumberString.contains(Character.toString(c))) {
                count++;
            }
        }

        return count;
    }

    private void displayResult(Result result) {
        if (result.isNothing()) {
            printlnNothing();
            return;
        }

        if (result.ballCount > COUNT_ZERO) {
            printCorrectBallCount(result.ballCount);
        }

        if (result.strikeCount > COUNT_ZERO) {
            printCorrectStrikeCount(result.strikeCount);
        }

        printNewLine();
    }

    private GameStatus determineRestart() {
        GameStatus gameStatus = GameStatus.chooseStatus(userInput.readOneOrTwo());
        if (gameStatus == GameStatus.EXIT) {
            printlnEndMessage();
        }

        return gameStatus;
    }

    private record Result(int strikeCount, int ballCount) {

        boolean isNothing() {
            return strikeCount == COUNT_ZERO && ballCount == COUNT_ZERO;
        }
    }

}
