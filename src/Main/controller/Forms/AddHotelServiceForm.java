package Main.controller.Forms;

import Main.Main;
import Main.model.Buildings.Hotel.HotelService;
import Main.model.Entry;
import Main.model.Exceptions.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AddHotelServiceForm implements Initializable {

    Map<CheckBox, HotelService> serviceMap = new HashMap<>();

    private double totalPrice;

    @FXML
    private CheckBox restaurantCBOX;

    @FXML
    private CheckBox gameNetCBOX;

    @FXML
    private CheckBox sportsHallCBOX;

    @FXML
    private CheckBox libraryCBOX;

    @FXML
    private CheckBox swimmingPoolCBOX;

    @FXML
    private CheckBox shoppingCenterCBOX;

    @FXML
    private CheckBox carParkingCBOX;

    @FXML
    private CheckBox cinemaCBOX;

    @FXML
    private Label totalPriceLBL;

    @FXML
    void clearAction() {
        for (CheckBox item : serviceMap.keySet()) {
            if (!item.isDisabled()) item.setSelected(false);
        }
        resetTotalPrice();
    }

    @FXML
    void doneAction() {
        try {
            if (totalPrice != 0) {
                Entry.getCity().checkPayment(-totalPrice);

                ArrayList<HotelService> services = new ArrayList<>();

                for (Map.Entry<CheckBox, HotelService> service : serviceMap.entrySet()) {
                    CheckBox item = service.getKey();
                    if (item.isSelected() && !item.isDisabled()) {
                        services.add(service.getValue());
                        item.setDisable(true);
                    }
                }
                Main.getHotelPage().addServices(services, totalPrice);
                resetTotalPrice();
            }
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void itemSelectAction(ActionEvent event) {
        CheckBox item = (CheckBox) event.getSource();
        HotelService service = serviceMap.get(item);
        totalPrice += (item.isSelected()) ? service.getPrice() : -service.getPrice();
        totalPriceLBL.setText(totalPrice + " $");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serviceMap.put(restaurantCBOX, HotelService.Restaurant);
        serviceMap.put(gameNetCBOX, HotelService.GameNet);
        serviceMap.put(sportsHallCBOX, HotelService.SportsHall);
        serviceMap.put(libraryCBOX, HotelService.Library);
        serviceMap.put(swimmingPoolCBOX, HotelService.SwimmingPool);
        serviceMap.put(shoppingCenterCBOX, HotelService.ShoppingCenter);
        serviceMap.put(carParkingCBOX, HotelService.CarParking);
        serviceMap.put(cinemaCBOX, HotelService.Cinema);

        ArrayList<HotelService> services = Entry.getHotel().getServices();
        for (Map.Entry<CheckBox, HotelService> service : serviceMap.entrySet()) {
            if (services.contains(service.getValue())) {
                service.getKey().setSelected(true);
                service.getKey().setDisable(true);
            }
        }
        resetTotalPrice();
    }

    private void resetTotalPrice() {
        totalPriceLBL.setText("0.0 $");
        totalPrice = 0;
    }

}
