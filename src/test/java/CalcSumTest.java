
import Calcu.Calculator;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2017-07-03.
 */
public class CalcSumTest {

    private Calculator calculator;
    private String numFilepath;

    @Before
    public void setup(){
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("numbers.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException{

        assertThat(calculator.calcSum(this.numFilepath),is(10));


    }

    @Test
    public void multiplyOfNumbers() throws IOException{
        assertThat(calculator.calcMultiply(this.numFilepath),is(30));

    }
    @Test
    public void concatenateSting() throws IOException{
        assertThat(calculator.concatenate(numFilepath),is("253"));
    }
}
