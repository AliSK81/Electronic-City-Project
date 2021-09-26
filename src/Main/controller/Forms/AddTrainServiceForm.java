package Main.controller.Forms;

import Main.Main;
import Main.model.Entry;
import Main.model.Exceptions.Alerts;
import Main.model.Vehicles.Train;
import Main.model.Vehicles.Train.TrainService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AddTrainServiceForm implements Initializable {

    Map<ToggleButton, TrainService> serviceMap = new HashMap<>();

    private double paymentCost;

    @FXML
    private ToggleButton foodTBTN;

    @FXML
    private ToggleButton coffeeTBTN;

    @FXML
    private ToggleButton gameTBTN;

    @FXML
    private ToggleButton bookTBTN;

    @FXML
    private ToggleButton tvTBTN;

    @FXML
    private ToggleButton musicTBTN;

    @FXML
    private Label costLBL;

    @FXML
    void clearAction(ActionEvent event) {
        for (Map.Entry<ToggleButton, TrainService> service : serviceMap.entrySet()) {
            boolean select = ((Train) Entry.getVehicle()).getServices().contains(service.getValue());
            service.getKey().setSelected(select);
        }
        resetPaymentCost();
    }

    @FXML
    void clickServiceAction(ActionEvent event) {
        ToggleButton item = (ToggleButton) event.getSource();
        TrainService service = serviceMap.get(item);
        paymentCost += (item.isSelected()) ? service.getPrice() : -service.getPrice();
        costLBL.setText(-paymentCost + " $");
    }

    @FXML
    void doneAction(ActionEvent event) {
        try {
            if (paymentCost != 0) {
                Entry.getCity().checkPayment(-paymentCost);

                ArrayList<TrainService> services = new ArrayList<>();

                for (Map.Entry<ToggleButton, TrainService> service : serviceMap.entrySet()) {
                    if (service.getKey().isSelected())
                        services.add(service.getValue());
                }
                Main.getRsPage().addServices(services, paymentCost);
                resetPaymentCost();
            }
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serviceMap.put(foodTBTN, TrainService.Food);
        serviceMap.put(coffeeTBTN, TrainService.Coffee);
        serviceMap.put(gameTBTN, TrainService.Game);
        serviceMap.put(bookTBTN, TrainService.Book);
        serviceMap.put(tvTBTN, TrainService.TV);
        serviceMap.put(musicTBTN, TrainService.Music);

        ArrayList<TrainService> services = ((Train) Entry.getVehicle()).getServices();
        for (Map.Entry<ToggleButton, TrainService> service : serviceMap.entrySet()) {
            if (services.contains(service.getValue()))
                service.getKey().setSelected(true);
        }
        resetPaymentCost();
    }

    private void resetPaymentCost() {
        costLBL.setText("0.0 $");
        paymentCost = 0;
    }
}
