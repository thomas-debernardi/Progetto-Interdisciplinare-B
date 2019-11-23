package util.view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.ApplicationCloser;
import util.Notification;
import util.client.Client;
import server.Server;
import server.registration.OTPHelper;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

/**
 * Controller della finestra per l'inserimento dell'OTP necessario al completamento della registrazione. Possiede un timer di dieci minuti oltre il
 * quale la registrazione viene annullata
 */
public class OTPRegistrationController implements Initializable {
    @FXML
    private TextField otpTextField;
    @FXML
    private Button confirmButton;
    @FXML
    private Label timeLabel;
    private int timeSeconds = 0;
    private int timeMinutes = 10;
    private Timeline timeline;
    private Server server;
    private Client client;
    private OTPHelper otp;

    public OTPRegistrationController() {
    }

    /**
     * Controlla che l'OTP inserito sia uguale a quello inviato via email. Se i due codici corrispondono, si viene reindirizzati alla schermata di login
     *
     * @throws IOException In caso non sia possibile accedere alla finestra successiva
     */
    public void enter() throws IOException {
        String otpStr = otpTextField.getText();
        boolean check = false;
        try {
            check = otp.checkOTP(otpStr, client);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (!check) {
            Notification.notify("OTP Notification", "CodiceOTP non valido\n", true);
        } else {
            timeline.stop();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main_pane.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle(FrameTitle.main);
            primaryStage.setScene(scene);
            primaryStage.show();
            ApplicationCloser.setCloser(primaryStage);
            Stage thisStage = (Stage) confirmButton.getScene().getWindow();
            thisStage.close();
        }
    }

    /**
     * Avvia il timer di dieci minuti. Quando il tempo scade, si viene reindirizzati alla schermata di login e viene segnalato il time out
     */
    public void runCountdown() {
        timeLabel.setText(String.valueOf(timeMinutes) + ":0" + String.valueOf(timeSeconds));
        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (timeSeconds == 0) {
                    timeMinutes--;
                    timeSeconds = 60;
                }
                timeSeconds--;
                if (timeSeconds < 10) {
                    timeLabel.setText(String.valueOf(timeMinutes) + ":0" + String.valueOf(timeSeconds));
                } else {
                    timeLabel.setText(String.valueOf(timeMinutes) + ":" + String.valueOf(timeSeconds));
                }
                if (timeSeconds <= 0 && timeMinutes <= 0) {
                    timeline.stop();
                    Notification.notify("OTP Notification", "Tempo esaurito \nripeti la procedura di registrazione", true);

                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getClassLoader().getResource("main_pane.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle(FrameTitle.main);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    ApplicationCloser.setCloser(primaryStage);
                    Stage thisStage = (Stage) confirmButton.getScene().getWindow();
                    thisStage.close();
                }
            }
        }));
        timeline.playFromStart();
    }

    /**
     * Notifica che l'OTP inserito non corrisponde a quello inviato via email.
     */
    public void notifyWrongOTP() {
        Notification.notify("OTP Notification", "OTP inserito errato \nriprova", true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RegistrationFormController.setOTP(this);
        runCountdown();
        try {
            client.setOtpPane(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setOtp(OTPHelper otp) {
        this.otp = otp;
    }
}
