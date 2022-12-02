package unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import com.makersacademy.nevermore.model.User;

public class UserUnitTest {
    private User user = new User();
    public String username = "Eddie";
    public String password = "Password";
    public Integer salary =  5000;
    public String image = "img";


    @Before
    public void setup(){
        //2, "DisneyPlus+", 5, "Leisure", timestamp
        user.setUsername(username);
        user.setPassword(password);
        user.setSalary(salary);
        user.setImage(image);
    }

    @Test
    public void userHasCorrectUsername(){
        assertEquals(username, user.getUsername());
    }

    @Test
    public void userHasCorrectPassword(){
        assertEquals(password, user.getPassword());
    }

    @Test
    public void userHasCorrectSalary(){
        assertEquals(salary, user.getSalary());
    }

    @Test
    public void userHasCorrectImage(){
        assertEquals(image, user.getImage());
    }
    
}
