package com.example.iss_ltbtp;

import com.example.iss_ltbtp.Presentation.Utils.SceneController;
import com.example.iss_ltbtp.Presentation.Utils.UIConstants;
import javafx.application.Application;
import javafx.stage.Stage;

public class EntryPoint extends Application {

    public static void main(final String[] args) { launch(args); }

    @Override
    public void start(final Stage primaryStage) {
        initSceneController(primaryStage);
    }

    private void initSceneController(final Stage stage) {
        try {
            final String entryPointRoute = UIConstants.ScreenRoute.ENTRY_POINT;
            new SceneController(stage, entryPointRoute, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
