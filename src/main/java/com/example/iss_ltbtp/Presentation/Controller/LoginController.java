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

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML public TextField usernameField;
    @FXML public PasswordField passwordField;
    @FXML public Label errorLabel;

    private UserService userService;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        userService = Injector.userService;
    }

    @FXML
    public void onSignUpLinkClicked() {
        SceneController.navigateTo(null, UIConstants.ScreenRoute.SIGN_UP);
    }

    @FXML
    public void onLoginBtnClicked() {
        final String username = usernameField.getText();
        final String password = passwordField.getText();
        if (!username.isBlank() && !password.isBlank()) {
            signIn(username, password);
        } else {
            showError();
        }
    }

    private void signIn(final String username, final String password) {
        hideErrorLabel();

        final Optional<User> optional = userService.findByCredentials(username, password);
        optional.ifPresentOrElse(this::initializeCurrentUserAndNavigate, this::showError);
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

    private void showError() {
        errorLabel.setVisible(true);
        errorLabel.setText("invalid credentials");
    }
}