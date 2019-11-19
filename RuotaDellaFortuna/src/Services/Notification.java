package Services;


import java.nio.charset.StandardCharsets;

import GUI.NotificationModel;

public class Notification {
    /**
     * @param title il titolo della notifica
     * @param msg   il messaggio della notifica
     * @param error <code>true</code> se far visualizzare la notifica come errore, <code>false</code> altrimenti
     */
    public static void notify(String title, String msg, boolean error) {
        byte[] bTitle = title.getBytes();
        byte[] bMsg = msg.getBytes();
        int duration = 3;
        if (error) {
            NotificationModel.create()
                    .title(new String(bTitle, StandardCharsets.UTF_8))
                    .text(new String(bMsg, StandardCharsets.UTF_8))
                    .hideAfter(Duration.seconds(duration))
                    .position(Pos.BASELINE_RIGHT)
                    .showError();
        } else {
            NotificationModel.create()
                    .title(new String(bTitle, StandardCharsets.UTF_8))
                    .text(new String(bMsg, StandardCharsets.UTF_8))
                    .hideAfter(Duration.seconds(duration))
                    .position(Pos.BASELINE_RIGHT)
                    .showInformation();
        }
    }
}
