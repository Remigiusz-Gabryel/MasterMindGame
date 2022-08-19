package com.remigiusz.mastermindgame;


import java.util.Random;

public class MasterMindGame {
    private String computerCode;
    private String userInput;

    public MasterMindGame() {
        restartGame();
    }

    public void runGame() {
    }


    public String getComputerCode() {
        return computerCode;
    }

    public void setComputerCode(String computerCode) {
        this.computerCode = computerCode;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public void restartGame(){
        Random random = new Random();
        StringBuilder newCode = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            newCode.append(random.nextInt(0,6));
        }
        computerCode = newCode.toString();
    }
    public String getScore2(String userInput) {

        StringBuilder user = new StringBuilder(userInput);
        StringBuilder code = new StringBuilder(computerCode);


        StringBuilder result = checkMatchingColorsInPosition(user,code);

        if (result.toString().equals("1111")) {
            return result.toString();
        } else {
            checkIfColorInSequence(user,code,result);
//        int colorsLeft = code.length();
//        int counter = 0;
//            for (int i = 0; i < colorsLeft; i++) {
//                if (code.toString().contains(String.valueOf(user.toString().charAt(i)))) {
//                    code.deleteCharAt(code.indexOf(String.valueOf(user.toString().charAt(i))));
//                    user.deleteCharAt(i);
//                    result.append(0);
//                    i--;
//                    colorsLeft--;
//                }
//            }
        }
        return result.toString();

    }
    private StringBuilder checkIfColorInSequence(StringBuilder user, StringBuilder code,StringBuilder result){
        int colorsLeft = code.length() - 1;

        for (int i = colorsLeft; i >= 0; i--) {
            if (code.toString().contains(String.valueOf(user.toString().charAt(i)))) {
                code.deleteCharAt(code.indexOf(String.valueOf(user.toString().charAt(i))));
                user.deleteCharAt(i);
                result.append(0);
            }
        }
        return result;
    }

    private StringBuilder checkMatchingColorsInPosition(StringBuilder userColor, StringBuilder randomColors){
        StringBuilder result = new StringBuilder();
        int initialColorSizePool = userColor.length() - 1;

        for (int i = initialColorSizePool; i >= 0; i--) {
            if (userColor.toString().charAt(i) == randomColors.toString().charAt(i)) {
                result.append(1);
                userColor.deleteCharAt(i);
                randomColors.deleteCharAt(i);;
            }
        }

//        for (int i = 0; i < userColor.length(); i++) {
//            if (userColor.toString().charAt(i) == randomColors.toString().charAt(i)) {
//                result.append(1);
//                userColor.deleteCharAt(i);
//                randomColors.deleteCharAt(i);;
//                i--;
//            }
//        }
        return result;
    }
}
