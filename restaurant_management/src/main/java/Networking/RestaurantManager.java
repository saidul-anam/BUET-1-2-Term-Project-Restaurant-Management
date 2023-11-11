package Networking;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Restaurant.*;

public class RestaurantManager implements Serializable {
List<food>menu;
List<restaurant>restaurants;

 Map<String,List<restaurant>>comparecatagories=new HashMap<>();


 List<restaurant>getRes(){
     return restaurants;
 }
 List<food>getFood(){
     return menu;
 }

    public RestaurantManager(List<food> b, List<restaurant> a) {
        menu=b;
        restaurants=a;
    }

    void mappingcatagories(){
 for(int i=0;i<restaurants.size();i++){
     restaurant a=restaurants.get(i);
     for(int j=0;j<a.getCatagories().size();j++){
       String s=a.getCatagories().get(j);
       if(comparecatagories.containsKey(s)){
           List<restaurant>x=comparecatagories.get(s);
           x.add(a);
       }
       else{
           List<restaurant>m=new ArrayList<>();
           m.add(a);
           comparecatagories.put(s,m);
       }
     }
    }
 }
restaurant Search_restaurant_name(String name){
    for(int i=0;i< restaurants.size();i++){
         restaurant x=restaurants.get(i);
        if(x.getName().equalsIgnoreCase(name)){
            return x;
    }
}
    return null;
}
List<food>resmenu(String name){
     Integer id=-1;
     for(int i=0;i<restaurants.size();i++){
         restaurant a=restaurants.get(i);
         if(a.getName().equals(name)){
             id=a.getId();
             break;
         }
     }
     List<food>inrange=new ArrayList<>();
     for(int i=0;i<menu.size();i++){
         food a=menu.get(i);
         if(a.getId()==id){
             inrange.add(a);
         }
     }
     return inrange;
}
List<food>update(int id){
    List<food>inrange=new ArrayList<>();
    for(int i=0;i<menu.size();i++){
        food a=menu.get(i);
        if(a.getId()==id){
            inrange.add(a);
        }
    }
    return inrange;
}

    List<restaurant> Search_restaurant_score(double score1,double score2){
    List<restaurant>inrange_restaurants=new ArrayList<>();
        for(int i=0;i< restaurants.size();i++){
            restaurant x=restaurants.get(i);
            if(x.getScore()>=score1&&x.getScore()<=score2){
                inrange_restaurants.add(x);
            }
        }

        return inrange_restaurants;
    }

    List<restaurant> Search_restaurant_catagory(String catagory){
    List<restaurant>inrage_restaurants=new ArrayList<>();
        mappingcatagories();
          if(comparecatagories.containsKey(catagory)){
              inrage_restaurants=comparecatagories.get(catagory);
            }
        return inrage_restaurants;
    }
    Map<String,List<restaurant>>restaurant_by_catagory(){
        mappingcatagories();
     return comparecatagories;
    }
    List<restaurant> Search_restaurant_price(String price){
        List<restaurant>inrage_restaurants=new ArrayList<>();
        for(int i=0;i< restaurants.size();i++){
            restaurant x=restaurants.get(i);
            if(x.getPrice().equals(price)){
                inrage_restaurants.add(x);
            }
        }
        return inrage_restaurants;
    }
    List<restaurant> Search_restaurant_zipcode(String zipcode){
        List<restaurant>inrage_restaurants=new ArrayList<>();
        for(int i=0;i< restaurants.size();i++){
            restaurant x=restaurants.get(i);
            if(x.getZipcode().equals(zipcode)){
                inrage_restaurants.add(x);
            }
        }
        return inrage_restaurants;
    }
    List<food>Search_food_name(String name){
    List<food>inrange_food=new ArrayList<>();
    for(int i=0;i<menu.size();i++){
        food x=menu.get(i);
        if(x.getName().toLowerCase().contains(name)){
            inrange_food.add(x);
        }
    }
return inrange_food;
}
    List<food>Search_food_name_restaurant(String name,String restaurant_name){
        List<food>inrange_food=new ArrayList<>();
        for(int i=0;i<menu.size();i++){
            food x=menu.get(i);
            if(x.getName().contains(name)) {
                int ids = x.getId();
                for (int j = 0; j < restaurants.size(); j++) {
                    restaurant y = restaurants.get(j);
                    if (y.getName().toLowerCase().contains(restaurant_name)){
                        if(y.getId()==ids){
                            inrange_food.add(x);
                        }
                    }
                }
            }
        }
        return inrange_food;
    }
    List<food>Search_food_catagory(String catagory){
        List<food>inrange_food=new ArrayList<>();
        for(int i=0;i<menu.size();i++){
            food x=menu.get(i);
            if(x.getCatagory().equalsIgnoreCase(catagory)){
                inrange_food.add(x);
            }
        }
        return inrange_food;
    }

    List<food>Search_food_catagory_restaurant(String catagory,String restaurant_name){
        List<food>inrange_food=new ArrayList<>();
        for(int i=0;i<menu.size();i++){
            food x=menu.get(i);
            if(x.getCatagory().contains(catagory)) {
                int ids = x.getId();
                for (int j=0; j<restaurants.size();j++) {
                    restaurant y = restaurants.get(j);
                    if (y.getName().toLowerCase().contains(restaurant_name)){
                        if(y.getId()==ids){
                            inrange_food.add(x);
                        }
                    }
                }
            }
        }
        return inrange_food;
    }
    List<food>Search_food_price(double price1,double price2){
        List<food>inrange_food=new ArrayList<>();
        for(int i=0;i<menu.size();i++){
            food x=menu.get(i);
            if(x.getPrice()>=price1&&x.getPrice()<=price2){
                inrange_food.add(x);
            }
        }
        return inrange_food;
    }

    List<food>Search_food_price_restaurant(double price1,double price2,String restaurant_name){
        List<food>inrange_food=new ArrayList<>();
        for(int i=0;i<menu.size();i++){
            food x=menu.get(i);
            if(x.getPrice()>=price1 && x.getPrice()<=price2) {
                int ids = x.getId();
                for (int j=0; j<restaurants.size();j++) {
                    restaurant y = restaurants.get(j);
                    if (y.getName().equalsIgnoreCase(restaurant_name)){
                        if(y.getId()==ids){
                            inrange_food.add(x);
                        }
                    }
                }
            }
        }
        return inrange_food;
    }
    List<food>costliest_item_restaurant(String name){
    List<food>inrange_menu=new ArrayList<>();
    int ids=-1;
    for(int i=0;i<restaurants.size();i++){
        restaurant k=restaurants.get(i);
        if(k.getName().equalsIgnoreCase(name)){
            ids=k.getId();
            break;
        }
    }
    if(ids!=-1){
        double price=-1;
        for(int j=0;j<menu.size();j++){
            food a=menu.get(j);
            if(a.getId()==ids) {
            if(a.getPrice()>price){
                price=a.getPrice();
            }
            }
        }
        for(int j=0;j<menu.size();j++){
            food a=menu.get(j);
            if(a.getId()==ids) {
                if(a.getPrice()==price){
                    inrange_menu.add(a);
                }
            }
        }
    }
    return inrange_menu;
    }
 int  addrestaurent(restaurant ob){
      int id=ob.getId();
      String name=ob.getName();
      double score=ob.getScore();
      String price=ob.getPrice();
      String zipcode=ob.getZipcode();
      List<String>catagory=ob.getCatagories();
      for(int j=0;j<restaurants.size();j++){
          if(restaurants.get(j).getName().equalsIgnoreCase(name)){
              return 1;
          }
      }
      restaurant s=new restaurant(id,name,score,price,zipcode,catagory);
      restaurants.add(s);
      return 0;
  }
  int addfoodtorestaurant(String name1,food ob){
    for(int i=0;i<restaurants.size();i++){
        restaurant x=restaurants.get(i);
        if(x.getName().equalsIgnoreCase(name1)){
            for(int j=0;j<menu.size();j++){
                if((menu.get(j).getName().equals(ob.getName())&&menu.get(j).getCatagory().equals(ob.getCatagory()))&&menu.get(j).getId()==ob.getId()){
                    return 2;
                }
            }
            if(x.getId()==ob.getId()){
                menu.add(ob);
                return 0;}
        }
    }
    return 1;
  }

 Map<String,Integer>food_count_by_restaurants(){
     Map<String,Integer>food_count_restaurant=new HashMap<>();
     int count=0;
     for(int i=0;i<restaurants.size();i++){
         restaurant x=restaurants.get(i);
         count=0;
         for(int j=0;j<menu.size();j++){
             if(menu.get(j).getId()==x.getId()){
             count++;
         }
         }
         food_count_restaurant.put(x.getName(),count);
     }
     return food_count_restaurant;
 }
}
