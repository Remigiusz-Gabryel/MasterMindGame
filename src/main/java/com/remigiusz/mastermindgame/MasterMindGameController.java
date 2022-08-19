package com.remigiusz.mastermindgame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MasterMindGameController implements Initializable {

    @FXML
    private Label code;
    @FXML
    private Label movesCounter;
    @FXML
    private GridPane gameGrid;

    @FXML
    private GridPane scoreGrid;
    @FXML
    private GridPane defaultColors;
    @FXML
    private Button restartGame;

    private int gameCirclesCount = 4;
    private int scoreGridColumns = 4;
    private int gameRowCounter = 0;
    private int scoreRowCounter = 0;
    private int MOVES_COUNTER = 10;
    private Color changeColor;
    private final EventHandler<MouseEvent> eventHandler = (e) -> {
        Circle circle = (Circle) e.getSource();
        circle.setFill(changeColor);
    };


    private final List<Color> GAMING_COLORS;
    private MasterMindGame masterMindGame;
    private Map<Character, Color> codeColorMap;

    public MasterMindGameController() {
        GAMING_COLORS = new ArrayList<>();
        setGAMING_COLORS();
        masterMindGame = new MasterMindGame();
//        masterMindGame.setComputerCode("5042");
        initializeColorCodeMap();
    }


    @FXML
    protected void onRestartButtonClick() {
        gameRowCounter = 0;
        scoreRowCounter = 0;
        gameGrid.getChildren().clear();
        scoreGrid.getChildren().clear();
        addNewGamingRow();
        addNewScoreRow();
        MOVES_COUNTER = 10;
        code.setText(masterMindGame.getComputerCode());
        masterMindGame.restartGame();
//        code.setText(masterMindGame.getComputerCode());
        restartGame.setText("Give UP!");
        movesCounter.setText("Zostało Ci : %s \n prób".formatted(--MOVES_COUNTER));

    }

    @FXML
    protected void checkResultOnClick(ActionEvent event) {
//        List<Character> colorsKeys = new ArrayList<>();
        StringBuilder userSequence = new StringBuilder();
        List<Node> nodes = gameGrid.getChildren().stream().filter(c -> !c.isDisabled()).collect(Collectors.toList());

        for (int i = 0; i < gameCirclesCount; i++) {
//            Circle circle = (Circle) gameGrid.getChildren().get(i);
//            Color circleColor = (Color) circle.getFill();
            Circle circle = (Circle) nodes.get(i);
            Color circleColor = (Color) circle.getFill();
            if (GAMING_COLORS.contains(circleColor)) {
                userSequence.append(GAMING_COLORS.indexOf(circleColor));
            } else {
                userSequence.append("M");
            }
        }


        String result = masterMindGame.getScore2(userSequence.toString());


        for (int i = 0; i < result.length(); i++) {
            Circle circle = (Circle) scoreGrid.getChildren().get((int) scoreGrid.getChildren().stream().count() - 4 + i);
            if (result.charAt(i) == '1') {
                circle.setFill(Color.BLACK);

            } else if (result.charAt(i) == '0') {
                circle.setFill(Color.WHITE);
            }
        }

        if ("1111".equals(result)) {
            movesCounter.setText("You WON !");
            code.setText(masterMindGame.getComputerCode());
            restartGame.setText("Restart");
            return;
        }

        movesCounter.setText(masterMindGame.getComputerCode());

        if (1 < MOVES_COUNTER) {
            addNewGamingRow();
            addNewScoreRow();
            movesCounter.setText("Zostało Ci : %s \n prób".formatted(--MOVES_COUNTER));
        } else {
            code.setText(masterMindGame.getComputerCode());
            restartGame.setText("Restart");
            movesCounter.setText("You Lost ! ");
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movesCounter.setText("Zostało Ci : %s \n prób".formatted(--MOVES_COUNTER));
        changeColor = Color.rgb(255, 255, 255);
        addNewGamingRow();
        addNewScoreRow();


        for (int i = 0; i < codeColorMap.size(); i++) {

            Circle circle = new Circle();
            circle.setRadius(12);
            Color color = GAMING_COLORS.get(i);
//            circle.setStroke(color);
            circle.setStroke(Color.rgb(0, 0, 0));
            circle.setFill(color);
            defaultColors.addColumn(i, circle);
            circle.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> changeColor = (Color) circle.getFill());

        }

    }

    private void addNewGamingRow() {
        deactivateRow();

        gameGrid.addRow(++gameRowCounter);
        for (int i = 0; i < gameCirclesCount; i++) {

            Circle circle = new Circle();


            circle.setRadius(12);
            circle.setStroke(Color.rgb(0, 0, 0));
            circle.setFill(Color.rgb(255, 255, 255));
            circle.setAccessibleText("T");
            circle.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
            gameGrid.addColumn(i, circle);
        }

    }

    private void addNewScoreRow() {


        scoreGrid.addRow(++scoreRowCounter);
        for (int i = 0; i < scoreGridColumns; i++) {

            Circle circle = new Circle();


            circle.setRadius(12.5);
            circle.setFill(Color.rgb(180, 180, 180));
            circle.setAccessibleText("T");
            scoreGrid.addColumn(i, circle);
        }

    }

    private void deactivateRow() {
        gameGrid.getChildren().stream().forEach(n -> n.setDisable(true));
    }


    private void setGAMING_COLORS() {

        for (int i = 0; i < 6; i++) {
            // Could assign array to list
            GAMING_COLORS.add(Color.RED);
            GAMING_COLORS.add(Color.BLUE);
            GAMING_COLORS.add(Color.YELLOW);
            GAMING_COLORS.add(Color.BROWN);
            GAMING_COLORS.add(Color.ORANGE);
            GAMING_COLORS.add(Color.DEEPPINK);
        }

    }

    private void initializeColorCodeMap() {
        codeColorMap = new HashMap<>();
        codeColorMap.put('1', GAMING_COLORS.get(1));
        codeColorMap.put('2', GAMING_COLORS.get(2));
        codeColorMap.put('3', GAMING_COLORS.get(3));
        codeColorMap.put('4', GAMING_COLORS.get(4));
        codeColorMap.put('5', GAMING_COLORS.get(5));
        codeColorMap.put('6', GAMING_COLORS.get(6));
    }
}