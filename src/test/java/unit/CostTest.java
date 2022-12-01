package unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.makersacademy.nevermore.model.Cost;

public class CostTest {
    private Cost cost = new Cost();
    public Date timestamp = new Date();
    public Integer price = 5;
    public Long userID = (long) 2;

    @Before
    public void setup(){
        //2, "DisneyPlus+", 5, "Leisure", timestamp
        cost.setPrice(price);
        cost.setContent("Disney Plus+");
        cost.setDate(timestamp);
        cost.setCategory("Leisure");
    }

    @Test
    public void costHasCorrectPrice(){
        assertEquals(price, cost.getPrice());
    }

    @Test
    public void costHasCorrectContent(){
     
    }

    @Test
    public void costHasCorrectDate(){
        assertEquals(timestamp, cost.getDate());
    }
    
    @Test
    public void costHasCorrectUserID(){
   
    }

    @Test
    public void costHasCorrectCategory(){
        assertEquals("Leisure", cost.getCategory());
    }
    
}
