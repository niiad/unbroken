package com.niiad.unbroken.framework.inputAdapters;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LoginController implements Initializable {
    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    @FXML
    JFXButton signInButton;

    @FXML
    JFXButton contactButton;

    @FXML
    Label forgotPasswordLabel;

    @FXML
    Label errorLabel;

    @Value("classpath:/main.fxml")
    private Resource mainResource;

    private final ApplicationContext applicationContext;

    private final String applicationName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public LoginController(@Value("${spring.application.ui.title}") String applicationName, ApplicationContext applicationContext) {
        this.applicationName = applicationName;
        this.applicationContext = applicationContext;
    }

    public void onSignInButtonClick(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == signInButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(mainResource.getURL());
                fxmlLoader.setControllerFactory(applicationContext::getBean);

                Scene scene = new Scene(fxmlLoader.load(), 1366, 768);

                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setTitle(applicationName);

                stage.setOnShown(e -> {
                    stage.setMinWidth(1400);
                    stage.setMinHeight(800);
                });

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onForgotPasswordLabelClick(MouseEvent mouseEvent) {
    }

    public void onContactButtonClick(MouseEvent mouseEvent) {
    }


}

