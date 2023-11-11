package Restaurant;
import java.io.Serializable;
public class client implements Serializable{
    private String name;
    private String password;
    private boolean status;
public client(String name, String password){
    this.name=name;
    this.password=password;
}
public String getname(){
    return name;
}
    public String getpassword(){
    return password;
}
public void setstatus(boolean status){
    this.status=status;
    }
    public boolean isstatus(){
    return status;
    }
}
