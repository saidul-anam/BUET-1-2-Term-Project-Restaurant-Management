package Networking;
import Restaurant.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class Main extends Application implements Serializable {

    private Stage stage;
    private SocketWrapper socketWrapper;
    public RestaurantManager s;
   void setS(RestaurantManager manager){
       System.out.println("setting manager in main");
       List<food>menu=manager.getFood();
       if(menu==null){
           System.out.println("menu is null in main");
       }
       if(menu!=null){
           System.out.println("menu is  not null in main");
       }
       List<restaurant>res=manager.getRes();
       if(res==null){
           System.out.println("restaurants is null in main");
       }
       if(menu!=null){
           System.out.println("menu is not null in main");
       }
       s=new RestaurantManager(menu,res);
   }
    public Main() throws Exception {

    }
 public List<restaurant> getRes(){
       return s.getRes();
 }
 public List<food> getFood(){
       return s.getFood();
 }
    public Stage getStage() {
        return stage;
    }
    public SocketWrapper getSocketWrapper() {
        return socketWrapper;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        connectToServer();
        showLoginPage();
    }

    private void connectToServer() throws Exception {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        socketWrapper = new SocketWrapper(serverAddress, serverPort);
        new ReadThread(this);
    }

    public void showLoginPage() throws Exception,NullPointerException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("login.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        controller.setMain(this);
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 750, 420));
        stage.show();
    }

    public void showHomePage() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("home.fxml"));
        Parent root = loader.load();
        HomeController controller = loader.getController();
        controller.setMain(this);
        controller.init();
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 1250, 692));
        stage.show();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
