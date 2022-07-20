module walid.projects.app {
    requires javafx.controls;
    requires javafx.fxml;

    opens walid.projects.app to javafx.fxml;
    exports walid.projects.app;
}
