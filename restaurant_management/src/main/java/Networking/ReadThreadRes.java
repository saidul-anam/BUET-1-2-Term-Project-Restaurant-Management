package Networking;

import Restaurant.ResOwn;
import javafx.application.Platform;

import java.io.IOException;

public class ReadThreadRes implements Runnable {

        private final Thread thr;
        private final ResMain main;

        public ReadThreadRes(ResMain main) {
            this.main = main;
            this.thr = new Thread(this);
            thr.start();
        }

        public void run() {
            try {
                while (true) {
                    Object o = main.getSocketWrapper().read();
                    if (o != null) {
                        if (o instanceof ResOwn) {
                            ResOwn loginDTO = (ResOwn) o;
                            System.out.println(loginDTO.getname());
                            System.out.println(loginDTO.isstatus());
                            // the following is necessary to update JavaFX UI components from user created
                            // threads
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (loginDTO.isstatus()) {
                                        try {
                                            main.showHomePage(loginDTO.getname());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        main.showAlert();
                                    }

                                }
                            });
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    main.getSocketWrapper().closeConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
