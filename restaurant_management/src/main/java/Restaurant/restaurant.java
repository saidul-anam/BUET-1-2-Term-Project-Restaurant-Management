package Restaurant;

import java.io.Serializable;
import java.util.List;
public class restaurant implements Serializable
{
       private int id;
     private String name;
      private double score;
       private String price;
        private String zipcode;
        private List<String>catagories;
 public restaurant(int id, String name, double score, String price, String zipcode, List<String> catagory){
     this.id=id;
     this.name=name;
     this.score=score;
     this.price=price;
     this.zipcode=zipcode;
     this.catagories=catagory;

 }
 public int getId(){
     return id;
 }
 public String getName(){
     return name;
 }
 public double getScore(){
     return score;
 }
 public String getPrice(){
     return price;
 }
 public String getZipcode(){
     return zipcode;
 }
 public List<String>getCatagories(){
     return catagories;
 }

    }
