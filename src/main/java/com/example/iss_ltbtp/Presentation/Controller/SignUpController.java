package com.example.iss_ltbtp.Presentation.Controller;

import com.example.iss_ltbtp.Data.Entity.User;
import com.example.iss_ltbtp.Domain.Service.UserService;
import com.example.iss_ltbtp.Presentation.Utils.SceneController;
import com.example.iss_ltbtp.Presentation.Utils.UIConstants;
import com.example.iss_ltbtp.Utils.Injector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML public TextField usernameField;
    @FXML public PasswordField passwordField;
    @FXML public ToggleButton developerSelectionBtn;
    @FXML public ToggleButton testerSelectionBtn;
    @FXML public Label errorLabel;

    private UserService userService;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        userService = Injector.userService;
    }

    @FXML
    public void onSignUpBtnClicked() {
        final String username = usernameField.getText();
        final String password = passwordField.getText();
        final User.Type type = developerSelectionBtn.isSelected()
                ? User.Type.DEVELOPER
                : testerSelectionBtn.isSelected() ? User.Type.TESTER : User.Type.NONE;

        signUp(username, password, type);
    }

    private void signUp(final String username, final String password, final User.Type type) {
        hideErrorLabel();
        try {
            final User user = userService.insert(username, password, type);
            initializeCurrentUserAndNavigate(user);
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void hideErrorLabel() {
        errorLabel.setVisible(false);
    }

    private void initializeCurrentUserAndNavigate(final User user) {
        final String route = user.getType() == User.Type.DEVELOPER
                ? UIConstants.ScreenRoute.DEV_MAIN
                : UIConstants.ScreenRoute.TESTER_MAIN;

        Injector.setNewestLoggedInUser(user);
        new SceneController(route, user);
    }

    private void showError(final String message) {
        errorLabel.setVisible(true);
        errorLabel.setText(message);
    }

    @FXML
    public void onLoginLinkClicked() {
        SceneController.navigateTo(null, UIConstants.ScreenRoute.LOGIN);
    }

    @FXML
    public void onDeveloperSelectionBtnClicked() {
        testerSelectionBtn.setSelected(false);
    }

    @FXML
    public void onTesterSelectionBtnClicked() {
        developerSelectionBtn.setSelected(false);
    }
}
