package sample.mvp;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * @author eguchi
 */
public class ModelTest{

    @Test
    public void calculateBmi() throws Exception{
        Model model = new Model();
        model.setWeight( "65" );
        model.setHeight( "1.75" );
        assertEquals( Float.parseFloat(model.getBMI()), 21.22, 0.01 );
    }

    @Test
    public void heightIsZero() throws Exception{
        Model model = new Model();
        model.setWeight( "65" );
        model.setHeight( "0" );
        assertEquals(  "", model.getBMI());
    }
    
    @Test
    public void weightIsNotNumber() throws Exception{
        Model model = new Model();
        model.setWeight( "abc" );
        model.setHeight( "1.75" );
        assertEquals(  "", model.getBMI());
    }
    
    @Test
    public void heightIsNotNumber() throws Exception{
        Model model = new Model();
        model.setWeight( "65" );
        model.setHeight( "abc" );
        assertEquals(  "", model.getBMI());
    }
}
