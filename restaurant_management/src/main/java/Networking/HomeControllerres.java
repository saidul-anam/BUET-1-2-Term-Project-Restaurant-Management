package Networking;

import Restaurant.IO;
import Restaurant.food;
import Restaurant.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class HomeControllerres {
    private ResMain main;
    RestaurantManager s;
    @FXML
    private TableColumn<food, String> name3;
    @FXML
    private TableColumn<food, String> category3;
    @FXML
    private TableColumn<food, String> orderfood1;
    @FXML
    private TableColumn<food, Double> orderprice1;
    @FXML
    private TableColumn<food, Double> price3;
    @FXML
    private TableView<food> table3;
    int id;
    @FXML
    private TableView<food> table4;
    @FXML
    TextField addname;
    @FXML
    TextField addprice;
    @FXML
    TextField addcat;
    List<food> b;
    String msg1;
    public void init(String msg) throws Exception {
        List<restaurant> a = IO.addres();
        b = IO.addmenu();
        s = new RestaurantManager(b, a);
        List<food> menu = s.resmenu(msg);
        msg1=msg;
        System.out.println(msg1);
        name3.setCellValueFactory(new PropertyValueFactory<food, String>("name"));
        price3.setCellValueFactory(new PropertyValueFactory<food, Double>("price"));
        category3.setCellValueFactory(new PropertyValueFactory<food, String>("catagory"));
        orderfood1.setCellValueFactory(new PropertyValueFactory<food, String>("name"));
        orderprice1.setCellValueFactory(new PropertyValueFactory<food, Double>("price"));
        ObservableList<food> data2;
        if (menu != null) {
            data2 = FXCollections.observableArrayList(menu);
        } else {
            data2 = FXCollections.observableArrayList();
        }
        table3.setItems(data2);
        for (int i = 0; i < a.size(); i++) {
            restaurant x = a.get(i);
            if (x.getName().equals(msg)) {
                id = x.getId();
                break;
            }
        }
           /*
        Image img = new Image(Main.class.getResourceAsStream("/1.png"));
        image.setImage(img);*/
    }

    @FXML
    void logoutAction(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void refresh(ActionEvent event) throws Exception {
        System.out.println("refreshed for getting order");
        main.getSocketWrapper().write(msg1);
        foodorder ob=(foodorder)main.getSocketWrapper().read();
        if(ob==null){
            System.out.println("order is null in restauarant");
        }
        List<food> order=ob.getOrder();
        ObservableList<food> data2;
        if (order != null) {
            data2 = FXCollections.observableArrayList(order);
        } else {
            data2 = FXCollections.observableArrayList();
        }
        table4.setItems(data2);

    }

    void setMain(ResMain main) {
        this.main = main;
    }
@FXML
    void addfood(ActionEvent event) {
        String name = addname.getText();
        String cat = addcat.getText();
        double a = 0;
        if (!addprice.getText().isEmpty()) {
            try {
                a = Double.parseDouble(addprice.getText());
                food x=new food(id,cat,name,a);
                b.add(x);

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        List<food>up=s.update(id);
    ObservableList<food> data2;
    if (up != null) {
        data2 = FXCollections.observableArrayList(up);
    } else {
        data2 = FXCollections.observableArrayList();
    }
    table3.setItems(data2);

    }
}
