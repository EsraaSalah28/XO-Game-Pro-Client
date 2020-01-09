package com.itijavafinalprojectteam8.view.login;

import com.itijavafinalprojectteam8.controller.ClientController;
import com.itijavafinalprojectteam8.controller.JsonOperations;
import com.itijavafinalprojectteam8.controller.UserInputChecker;
import com.itijavafinalprojectteam8.view.interfaces.GameAppView;
import com.itijavafinalprojectteam8.view.interfaces.LoginView;
import com.itijavafinalprojectteam8.view.others.Toast;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;


/**
 * @author ahares
 */
public class LoginController implements LoginView {

    @FXML
    private TextField emailAddressTF;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private Pane progressPane;

    private static GameAppView mApplicationCallback;

    public LoginController() {
        ClientController.setLoginView(this);
    }

    public static void setApplicationCallback(GameAppView callback) {
        mApplicationCallback = callback;
    }

    @FXML
    private void changeScreenHyperLink(ActionEvent event) throws IOException {
        if (mApplicationCallback != null)
            mApplicationCallback.switchToSignUpScreen();
    }

    @FXML
    private void onLoginBtnClicked(ActionEvent event) throws IOException {
        String email = emailAddressTF.getText();
        String plainPass = passwordPF.getText();

        if (!UserInputChecker.isValidEmail(email)) {
            if (mApplicationCallback != null)
                mApplicationCallback.showToastMessage("Email is invalid");
            return;
        }

        try {
            progressPane.setVisible(true);
            ClientController.sendToServer(JsonOperations.getSignInJson(email, plainPass));
            ClientController.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(String errorMsgFromServer) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                progressPane.setVisible(false);

                if (mApplicationCallback != null)
                    mApplicationCallback.showToastMessage(errorMsgFromServer);
            }
        });
    }

    @Override
    public void onSuccessResponse() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                progressPane.setVisible(false);

                if (mApplicationCallback != null)
                    mApplicationCallback.switchToGameScreen();
            }
        });
    }
}
