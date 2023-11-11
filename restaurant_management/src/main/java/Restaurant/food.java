package Restaurant;

import java.io.Serializable;

public class food implements Serializable {
 private Integer id;
 private String name;
 private Double price;
  private String catagory;

public food(Integer id,String catagory,String name,Double price){
    this.id=id;
    this.catagory=catagory;
    this.name=name;
    this.price=price;
}
    public Integer getId(){
    return id;
}
    public String getName(){
    return name;
}
    public double getPrice(){
    return price;
}
    public String getCatagory(){
    return catagory;
}
}
