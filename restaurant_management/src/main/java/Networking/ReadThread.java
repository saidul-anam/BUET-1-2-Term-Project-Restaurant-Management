package Networking;
import Restaurant.*;

import javafx.application.Platform;
import java.io.IOException;
import java.util.List;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Main main;

    public ReadThread(Main main) throws Exception {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
                Object o = main.getSocketWrapper().read();
                if (o != null) {
                    if (o instanceof client) {
                        client a = (client) o;
                        System.out.println(a.getname());
                        System.out.println(a.isstatus());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (a.isstatus()) {
                                    try {
                                        System.out.println("done with the login");
                                        main.showHomePage();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    main.showAlert();
                                }

                            }
                        });
                    }
                   /* else if(o instanceof RestaurantManager) {
                        System.out.println("recieved restaurantmanager");
                        RestaurantManager manager = (RestaurantManager) o;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    System.out.println("setting to main ");

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }*/
                }


        } catch (Exception e) {
            System.out.println(e);
        } /*finally {
            try {
                main.getSocketWrapper().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
}
