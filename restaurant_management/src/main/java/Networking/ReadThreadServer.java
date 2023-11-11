package Networking;
import Restaurant.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final SocketWrapper socketWrapper;
    private HashMap<String, String> userMap;
    private HashMap<String,foodorder>ordered;
    private List<restaurant>restaurants;
    RestaurantManager manager;
    public ReadThreadServer(HashMap<String, String> map,RestaurantManager manager,SocketWrapper socketWrapper) {
        this.userMap = map;
        this.manager=manager;
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        this.ordered=new HashMap<>();
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = socketWrapper.read();
                if (o != null) {
                    if (o instanceof client) {
                        System.out.println("going to login");
                        client loginDTO = (client) o;
                        String password = userMap.get(loginDTO.getname());
                        loginDTO.setstatus(loginDTO.getpassword().equals(password));
                        socketWrapper.write(loginDTO);
                    }
                    else if (o instanceof String) {
                        System.out.println("manager going");
                        String x = (String) o;
                        if (x.equals("start")) {
                            socketWrapper.write(manager);
                        }
                        else{
                            System.out.println("getting the order in restaurant ");
                            String ob = (String) o;
                            System.out.println(ob);
                            List<restaurant>res=manager.getRes();
                            List<food>menu=IO.ordermenu();
                            int id=-1;
                            for(int i=0;i<res.size();i++){
                                if(res.get(i).getName().equals(ob)){
                                    id=res.get(i).getId();
                                }
                            }
                            List<food>my=new ArrayList<>();
                            for(int i=0;i<menu.size();i++){
                                if(menu.get(i).getId()==id){
                                    my.add(menu.get(i));
                                }
                            }
                            foodorder my1=new foodorder(ob,my);
                         socketWrapper.write(my1);
                        }
                    } else if (o instanceof foodorder) {
                        System.out.println("connected the order");
                        foodorder ob = (foodorder) o;
                        List<restaurant>res=manager.getRes();
                        ordered.put(ob.getName(),ob);
                        System.out.println(ob.getName());
                        IO.store(ob.getOrder());
                        List<food>order=ordered.get(ob.getName()).getOrder();
                        for(int i=0;i<order.size();i++){
                            System.out.println(order.get(i).getName());
                        }

                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
