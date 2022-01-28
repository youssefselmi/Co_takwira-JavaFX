package cotakwira.entities;

import java.util.ArrayList;
import java.util.List;

public class cart {
    public List<stocks> mycart = new ArrayList<stocks>();
    float pricei=0;
    public void setPrix_Unite(float Prix_unite)
    {
        this.pricei=Prix_unite;
    }
    public float getPrix_Unite(){
        return pricei;
    }
}
