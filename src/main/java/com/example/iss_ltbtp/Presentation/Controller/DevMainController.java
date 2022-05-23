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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class DevMainController implements Initializable, Observer {

    @FXML public ListView<Bug> bugListView;
    @FXML public Spinner<String> bugStatusSpinner;

    private User user;
    private BugService bugService;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        user = Injector.getNewestLoggedInUser();
        bugService = Injector.bugService;

        bugService.addObserver(this);

        initUserStatusSpinner();

        refreshBugs();
    }

    private void initUserStatusSpinner() {
        bugStatusSpinner.setValueFactory(new SpinnerValueFactory<>() {

            private int currentIndex = 0;

            @Override
            public void decrement(final int steps) {
                currentIndex = 0 == currentIndex
                        ? Bug.Status.values().length - 1
                        : currentIndex - 1;
                if (Bug.Status.values()[currentIndex] == Bug.Status.UNSOLVED) {
                    decrement(1);
                } else {
                    setValue(Bug.Status.values()[currentIndex].name());
                }
            }

            @Override
            public void increment(final int steps) {
                currentIndex = Bug.Status.values().length - 1 == currentIndex
                        ? 0
                        : currentIndex + 1;
                if (Bug.Status.values()[currentIndex] == Bug.Status.UNSOLVED) {
                    increment(1);
                } else {
                    setValue(Bug.Status.values()[currentIndex].name());
                }
                setValue(Bug.Status.values()[currentIndex].name());
            }
        });

        Arrays.stream(Bug.Status.values())
                .filter(status -> Bug.Status.UNSOLVED != status)
                .forEach(status -> bugStatusSpinner.getValueFactory().setValue(status.name()));
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
    public void updateBugStatus() {
        final Bug bug = bugListView.getSelectionModel().getSelectedItem();
        final Bug.Status newStatus = Bug.Status.valueOf(bugStatusSpinner.getValue());
        if (Bug.Status.SOLVED != bug.getStatus()) {
            bug.setStatus(newStatus);
            bugService.update(bug);
        }
    }
}
