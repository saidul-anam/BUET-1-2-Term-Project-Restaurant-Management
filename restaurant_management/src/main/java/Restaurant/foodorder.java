package Restaurant;

import java.io.Serializable;
import java.util.List;

public class foodorder implements Serializable {
    String name;
    List<food> order;
    public String getName(){
        return name;
    }
    public List<food>getOrder(){
        return order;
    }
    public foodorder(String name,List<food>order){
        this.name=name;
        this.order=order;

    }
}
