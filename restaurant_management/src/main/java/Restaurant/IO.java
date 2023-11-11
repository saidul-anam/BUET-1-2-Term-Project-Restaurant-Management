package Restaurant;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class IO {
    static final String INPUT_FILE_NAME = "data/restaurant.txt";
    private static final String OUTPUT_FILE_NAME = "data/restaurant.txt";
    static final String INPUT_FILE_NAME1 = "data/menu.txt";
    private static final String OUTPUT_FILE_NAME1 = "data/menu.txt";
    static final String INPUT_FILE_NAME2 = "data/client.txt";
    private static final String OUTPUT_FILE_NAME2 = "data/client.txt";
    static final String INPUT_FILE_NAME3 = "data/order.txt";
    private static final String OUTPUT_FILE_NAME3 = "data/order.txt";


    public static List<food>addmenu()throws Exception{
        List<food>Menu=new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME1));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] array = line.split(",", -1);
            int id=Integer.parseInt(array[0]);
            double price=Double.parseDouble(array[3]);
            food s=new food(id,array[1],array[2],price);
            Menu.add(s);
        }
        br.close();
        return Menu;
    }
    public static List<food>ordermenu()throws Exception{
        List<food>Menu=new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME3));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] array = line.split(",", -1);
            int id=Integer.parseInt(array[0]);
            double price=Double.parseDouble(array[3]);
            food s=new food(id,array[1],array[2],price);
            Menu.add(s);
        }
        br.close();
        return Menu;
    }

    public static List<restaurant>addres()throws Exception{
        List<restaurant>Restaurants=new ArrayList<>();
        BufferedReader br1 = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br1.readLine();
            if (line == null) break;
            String [] array = line.split(",", -1);
            int id=Integer.parseInt(array[0]);
            double score=Double.parseDouble(array[2]);
            List<String>catagory=new ArrayList<>();
            for(int i=5;i<array.length;i++){
                catagory.add(array[i]);
            }
            restaurant s=new restaurant(id,array[1],score,array[3],array[4],catagory);
            Restaurants.add(s);
        }
        br1.close();
        return Restaurants;
    }
    public static HashMap<String,String>getclient()throws Exception{
        HashMap<String,String>clients=new HashMap<>();
        BufferedReader br1 = new BufferedReader(new FileReader(INPUT_FILE_NAME2));
        while (true) {
            String line = br1.readLine();
            if (line == null) break;
            String [] array = line.split(" ", -1);
            String name=array[0];
            String password=array[1];
           clients.put(name,password);
        }
        br1.close();
        return clients;
    }
    public static HashMap<String, String> resownMap()throws Exception{
         HashMap<String, String> resownMap=new HashMap<>();
        BufferedReader br1 = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br1.readLine();
            if (line == null) break;
            String [] array = line.split(",", -1);
            resownMap.put(array[1],array[0]);
        }
        br1.close();
        return resownMap;
    }
    public static void store(List<food> order) throws IOException {
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME3));
        for(int i=0;i<order.size();i++){
            food ob3=order.get(i);
            String total=ob3.getId()+","+ob3.getCatagory()+","+ob3.getName()+","+ob3.getPrice();
            bw1.write(total);
            bw1.write(System.lineSeparator());
        }
        bw1.close();
    }
}
