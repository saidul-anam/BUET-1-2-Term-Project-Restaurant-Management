package Networking;
import Restaurant.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HomeController {
    private Main main;
    RestaurantManager s;
    @FXML
    private TableColumn<food,String> name;
    @FXML
    private TableColumn<food,Integer> id;
    @FXML
    private TableColumn<food,String> catagory;
    @FXML
    private TableColumn<food,Double> price;
    @FXML
    private TableColumn<food,String> name3;
    @FXML
    private TableColumn<food,String> category3;
    @FXML
    private TableColumn<food,Double> price3;
    @FXML
    private TableColumn<food,String> name4;
    @FXML
    private TableColumn<food,Double> price4;
    List<food>c;
    List<food>b;
    List<food>cart;
    restaurant m;
    List<restaurant>n;
@FXML
private Button menu1;
    @FXML
    private Label message;

    @FXML
    private ImageView image;

    @FXML
    private Button button;
    @FXML
    private Button searches1;
    @FXML
    private Button tocart;
    @FXML
    private Button buttonSearch;
    @FXML
    private ChoiceBox choice_box_search;
    @FXML
    private ChoiceBox choice_box_search1;
    @FXML
    private ChoiceBox orderres;
    @FXML
    private TextField text1;
    @FXML
    private TextField text2;
    @FXML
    private TextField text3;
    @FXML
    private TextField text4;
   @FXML
   private Label label1;
   @FXML
   private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
   @FXML
   private TableView<food> searchtable;
@FXML
 private TableColumn<restaurant,Integer>id1;
    @FXML
    private TableColumn<restaurant,String>name1;
    @FXML
    private TableColumn<restaurant,Double>score1;
    @FXML
    private TableColumn<restaurant,String>price1;
    @FXML
    private TableColumn<restaurant,String>zipcode;
    @FXML
    private TableColumn<restaurant,List<String>>catagory1;
    @FXML
    private TableView<restaurant>searches;
    @FXML
    private TableView<food> table3;
    @FXML
    private TableView<food> table4;
    List<restaurant>restaurants;
    public HomeController() throws Exception {
    }

    public void init() throws Exception {
        c=new ArrayList<>();
        n=new ArrayList<>();
        cart=new ArrayList<>();
        text1.setVisible(false);
        text2.setVisible(false);
        label1.setVisible(false);
        label2.setVisible(false);
        text3.setVisible(false);
        text4.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        String[] any={"By Name","By Category", "By Price Range"};
        choice_box_search1.getItems().addAll(any);
        choice_box_search1.setOnAction(this::persingfood);
        String[] z={ "By Name","By Score ","By Price ","By Zip Code "};
        choice_box_search.getItems().addAll(z);
        choice_box_search.setOnAction(this::persingrestaurant);
        id.setCellValueFactory(new PropertyValueFactory<food,Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<food,String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<food,Double>("price"));
        catagory.setCellValueFactory(new PropertyValueFactory<food,String>("catagory"));
        name3.setCellValueFactory(new PropertyValueFactory<food,String>("name"));
        price3.setCellValueFactory(new PropertyValueFactory<food,Double>("price"));
        category3.setCellValueFactory(new PropertyValueFactory<food,String>("catagory"));
        id1.setCellValueFactory(new PropertyValueFactory<restaurant,Integer>("id"));
        name1.setCellValueFactory(new PropertyValueFactory<restaurant,String>("name"));
        price1.setCellValueFactory(new PropertyValueFactory<restaurant,String>("price"));
        score1.setCellValueFactory(new PropertyValueFactory<restaurant,Double>("score"));
        zipcode.setCellValueFactory(new PropertyValueFactory<restaurant,String>("zipcode"));
        name4.setCellValueFactory(new PropertyValueFactory<food,String>("name"));
        price4.setCellValueFactory(new PropertyValueFactory<food,Double>("price"));
    }

    private void persingfood(Event event) {
        text1.clear();
        text2.clear();
        c.clear();
        searchtable.getItems().clear();
        text1.setVisible(false);
        text2.setVisible(false);
        label1.setVisible(false);
        label2.setVisible(false);
        String x = (String) choice_box_search1.getValue();
        if(x!=null){
            if (x.equals("By Name")) {
                label1.setText("Food:");
                text1.setVisible(true);
                label1.setVisible(true);
            }  else if (x.equals("By Category")) {
                text1.setVisible(true);
                label1.setText("Catagory");
                label1.setVisible(true);

            }
            else if(x.equals("By Price Range")){
                text1.setVisible(true);
                label1.setText("From :");
                label1.setVisible(true);
                text2.setVisible(true);
                label2.setText("To:");
                label2.setVisible(true);

            }
        }
        buttonSearch.setOnAction(this::Searchingfood);
        //String x = (String) choice_box_search1.getValue();
    }

    private void persingrestaurant(Event event){
        text3.clear();
        text4.clear();
        n.clear();
        searches.getItems().clear();
        text3.setVisible(false);
        text4.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        String x = (String) choice_box_search.getValue();
        if(x!=null){
        if (x.equals("By Name")) {
            label3.setText("Restaurant:");
            text3.setVisible(true);
            label3.setVisible(true);
        }
        else if (x.equals("By Score ")) {
            text3.setVisible(true);
            text4.setVisible(true);
            label3.setText("From:");
            label4.setText("To :");
            label3.setVisible(true);
            label4.setVisible(true);

        }
        else if (x.equals("By Price ")) {
            text3.setVisible(true);
            label3.setText("Price :");
            label3.setVisible(true);
        }

        else if (x.equals("By Zip Code ")) {
            text3.setVisible(true);
            label3.setText("ZipCode :");
            label3.setVisible(true);
        }
    }
        searches1.setOnAction(this::Searchingres);
    }


    @FXML
    void logoutAction(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void Searchingres(ActionEvent event){
        String x = (String) choice_box_search.getValue();
        if(x!=null){
            if (x.equals("By Name")) {

                String a=text3.getText();
                m=s.Search_restaurant_name(a);
                n.add(m);
            }
            else if (x.equals("By Score ")) {
                if (!text3.getText().isEmpty() && !text4.getText().isEmpty()) {
                    try {
                        double a = Double.parseDouble(text3.getText());
                        double b = Double.parseDouble(text4.getText());
                        n = s.Search_restaurant_score(a, b);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                }

            }
            else if (x.equals("By Price ")) {
                String a=text3.getText();
                n=s.Search_restaurant_price(a);
            }

            else if (x.equals("By Zip Code ")) {
                String a=text3.getText();
                n=s.Search_restaurant_zipcode(a);
            }
        }
        ObservableList<restaurant>data1;
        if(n!=null){
       data1=FXCollections .observableArrayList(n);}
        else{
            data1=FXCollections.observableArrayList(n);
        }
        searches.setItems(data1);
    }
    @FXML
    void clicksearch(ActionEvent event){
                    cart.clear();
                    restaurant clickedRow = searches.getSelectionModel().getSelectedItem();
                    System.out.println("Now i am here for restaurant order");
                    if(clickedRow==null){
                        System.out.println("its null buddy");
                    }
                    Integer resid = clickedRow.getId();
                    System.out.println("resid "+resid);
                    List<food>resmenu=new ArrayList<>();
                    for(int i=0;i<b.size();i++){
                        System.out.println("b er id"+b.get(i).getId());
                        if(b.get(i).getId().equals(resid)){
                            resmenu.add(b.get(i));
                            System.out.println("added");
                        }
                        else{
                            System.out.println("No");
                        }
                    }
                    ObservableList<food>data2;
                    if(resmenu!=null){
                        data2=FXCollections.observableArrayList(resmenu);}
                    else{
                        System.out.println("sorry buddy.your food list is null for the restaurant");
                        data2=FXCollections.observableArrayList();
                    }
                    table3.setItems(data2);

            }

    void Searchingfood(ActionEvent event){
        String x = (String) choice_box_search1.getValue();
        if(x!=null){
            if (x.equals("By Name")) {
                String a=text1.getText();
                c=s.Search_food_name(a);
                for(int i=0;i<c.size();i++){
                    System.out.println(c.get(i).getName());
                }
            }  else if (x.equals("By Category")) {
                String a=text1.getText();
                c=s.Search_food_catagory(a);
                for(int i=0;i<c.size();i++){
                    System.out.println(c.get(i).getName());
                }

            }
            else if(x.equals("By Price Range")){
                if (!text1.getText().isEmpty() && !text2.getText().isEmpty()) {
                    try {
                        double a = Double.parseDouble(text1.getText());
                        double b = Double.parseDouble(text2.getText());
                        c = s.Search_food_price(a, b);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                }
                for(int i=0;i<c.size();i++){
                    System.out.println(c.get(i).getName());
                }
            }
        }
        ObservableList<food> data;
        if (c != null) {
            data = FXCollections.observableArrayList(c);
        } else {
            data = FXCollections.observableArrayList();
            System.out.println(" wrong");
        }
        //   ObservableList<food>data= FXCollections.observableArrayList(c);
        searchtable.setItems(data);

    }
    @FXML
    void addcart(ActionEvent add){
        food clickedRow = table3.getSelectionModel().getSelectedItem();
        cart.add(clickedRow);
        ObservableList<food>data2;
        if(cart!=null){
            data2=FXCollections .observableArrayList(cart);}
        else{
            data2=FXCollections.observableArrayList();
        }
        table4.setItems(data2);

    }
    @FXML
    void ordercart(ActionEvent remove) throws IOException {
     //  IO.store(cart);
        if(cart!=null){
            int hell=cart.get(0).getId();
            for(int i=0;i<restaurants.size();i++){
                if(restaurants.get(i).getId()==hell){
       foodorder ob =new foodorder(restaurants.get(i).getName(),cart);
       main.getSocketWrapper().write(ob);
       cart.clear();
                break;}
            }
        }
       ObservableList<food>data2=FXCollections.observableArrayList();
       table4.setItems(data2);
        }
    void setMain(Main main) throws IOException, ClassNotFoundException {
        this.main = main;
        System.out.println("we are in main for home");
        try {
            String hello="start";
            main.getSocketWrapper().write(hello);
        } catch (IOException e) {
            e.printStackTrace();
        }
        s=(RestaurantManager)main.getSocketWrapper().read();
        b= s.getFood();
        restaurants=s.getRes();

    }
    }

