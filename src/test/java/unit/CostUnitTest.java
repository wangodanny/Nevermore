package unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.makersacademy.nevermore.model.Cost;
import com.makersacademy.nevermore.model.User;

public class CostUnitTest {
    private Cost cost = new Cost();
    public Date timestamp = new Date();
    public Double price = 5.0;
    public Long userID = (long) 2;
    public String content = "Netflix";

    @Before
    public void setup(){
        //2, "DisneyPlus+", 5, "Leisure", timestamp
        cost.setPrice(price);
        cost.setContent(content);
        cost.setDate(timestamp);
        cost.setCategory("Leisure");

        }

    @Test
    public void costHasCorrectPrice(){
        assertEquals(price, cost.getPrice());
    }

    @Test
    public void costHasCorrectContent(){
        assertEquals(content, cost.getcontent());
    }

    @Test
    public void costHasCorrectDate(){
        assertEquals(timestamp, cost.getDate());
    }

    @Test
    public void costHasCorrectCategory(){
        assertEquals("Leisure", cost.getCategory());
    }

    
}
