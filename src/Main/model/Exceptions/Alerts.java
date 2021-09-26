package Main.model.Exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Alerts {

    private static boolean infoAlertsON = true;
    private static boolean confirmAlertsON = true;
    private static boolean warningAlertsON = true;

    public static void showError(String msg) {
        Alert alert = new Alert(AlertType.ERROR, msg);
        alert.setHeaderText("Failure");
        alert.showAndWait();
    }

    public static void showInfo(String msg) {
        if (infoAlertsON) {
            Alert alert = new Alert(AlertType.INFORMATION, msg);
            alert.setHeaderText("Done");
            alert.show();
        }
    }

    public static boolean showConfirm(String msg) {
        if (confirmAlertsON) {
            Alert alert = new Alert(AlertType.CONFIRMATION, msg, ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            return alert.getResult() == ButtonType.YES;
        }
        return true;
    }

    public static boolean showWarning(String msg) {
        if (warningAlertsON) {
            Alert alert = new Alert(AlertType.WARNING, msg, ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            return alert.getResult() == ButtonType.YES;
        }
        return true;
    }

    public static void setInfoAlertsON(boolean ON) {
        infoAlertsON = ON;
    }

    public static void setConfirmAlertsON(boolean ON) {
        confirmAlertsON = ON;
    }

    public static void setWarningAlertsON(boolean ON) {
        warningAlertsON = ON;
    }

}
