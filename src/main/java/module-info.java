module com.remigiusz.mastermindgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.remigiusz.mastermindgame to javafx.fxml;
    exports com.remigiusz.mastermindgame;
}