package com.example.iss_ltbtp.Presentation.Utils;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public record NavGraphController(Scene scene) {

    private static final Map<String, URL> routes = new HashMap<>() {{
        put(UIConstants.ScreenRoute.LOGIN, Resources.urlOf(UIConstants.ScreenRoute.LOGIN));
        put(UIConstants.ScreenRoute.SIGN_UP, Resources.urlOf(UIConstants.ScreenRoute.SIGN_UP));
        put(UIConstants.ScreenRoute.DEV_MAIN, Resources.urlOf(UIConstants.ScreenRoute.DEV_MAIN));
    }};

    public void navigateTo(final String resourceFileName) {
        navigateTo(routes.get(resourceFileName));
    }

    private void navigateTo(final URL url) {
        if (url != null) {
            navigateTo(Resources.load(url));
        }
    }

    private void navigateTo(final Parent root) {
        if (root != null) {
            scene.setRoot(root);
        }
    }
}
