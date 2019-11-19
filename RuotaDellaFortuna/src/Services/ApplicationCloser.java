package util;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Classe utilizzata per impostare la chiusura della finestra corrente in modo tale che termini il programma
 */
public class ApplicationCloser {
    public static void setCloser(Stage stage){
        stage.setOnCloseRequest((WindowEvent event1) -> {
            System.exit(0);
        });
    }
}
