package com.example.iss_ltbtp.Presentation.Controller;

import com.example.iss_ltbtp.Data.Entity.Bug;
import com.example.iss_ltbtp.Data.Entity.User;
import com.example.iss_ltbtp.Domain.Service.BugService;
import com.example.iss_ltbtp.Presentation.Utils.SceneController;
import com.example.iss_ltbtp.Presentation.Utils.UIConstants;
import com.example.iss_ltbtp.Utils.Injector;
import com.example.iss_ltbtp.Utils.Observer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TesterMainController implements Initializable, Observer {

    @FXML public ListView<Bug> bugListView;
    @FXML public TextField descriptionField;

    private User user;
    private BugService bugService;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        user = Injector.getNewestLoggedInUser();
        bugService = Injector.bugService;

        bugService.addObserver(this);

        refreshBugs();
    }

    private void refreshBugs() {
        bugListView.getItems().setAll(bugService.findAll());
    }

    @Override
    public void update() {
        refreshBugs();
    }

    @FXML
    public void logout() {
        SceneController.navigateTo(user.getId(), UIConstants.ScreenRoute.LOGIN);
    }

    @FXML
    public void addBug() {
        final String description = descriptionField.getText();

        try {
            bugService.insert(description);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
