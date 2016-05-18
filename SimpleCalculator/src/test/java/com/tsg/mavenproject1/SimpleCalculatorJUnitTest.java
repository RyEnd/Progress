package com.tsg.mavenproject1;

import static oracle.jrockit.jfr.events.Bits.intValue;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author ryanbmolnar@gmail.com
 */
public class SimpleCalculatorJUnitTest {
    
    public SimpleCalculatorJUnitTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    SimpleCalculatorLogic equation = new SimpleCalculatorLogic();
    ConsoleIO fido = new ConsoleIO();
    
    @Test
    public void TestPositviePlusPositive() {
        int answer = intValue(equation.calculate("+", 2, 3));
        assertEquals(5, answer);
    }

    @Test
    public void TestPositivePlusZero() {
        int answer = intValue(equation.calculate("+", 2, 0)); 
        assertEquals(answer, 2);
    }

    @Test
    public void TestPositivePlusNegative() {
        int answer = intValue(equation.calculate("+", 2, -44));
        assertEquals(answer, -42);
    }

    @Test
    public void TestNegativePlusPositive() {
        int answer = intValue(equation.calculate("+", -44, 3));
        assertEquals(answer, -41);
    }

    @Test
    public void TestNegativePlusZero() {
        int answer = intValue(equation.calculate("+", -44, 0));
        assertEquals(answer, -44);
    }

    @Test
    public void TestNegativePlusNegative() {
        int answer = intValue(equation.calculate("+", -44, -44));
        assertEquals(answer, -88);
    }

    @Test
    public void TestZeroPlusPositive() {
        int answer = intValue(equation.calculate("+", 0, 44));
        assertEquals(answer, 44);
    }

    @Test
    public void TestZeroPlusZero() {
        int answer = intValue(equation.calculate("+", 0, 0));
        assertEquals(answer, 0);
    }

    @Test
    public void TestZeroPlusNegative() {
        int answer = intValue(equation.calculate("+", 0, -44));
        assertEquals(answer, -44);
    }

    @Test
    public void TestPostivieMinusPositive() {
        int answer = intValue(equation.calculate("-", 44, 66));
        assertEquals(answer, -22);
    }

    @Test
    public void TestPostivieMinusZero() {
        int answer = intValue(equation.calculate("-", 44, 0));

        assertEquals(answer, 44);
    }

    @Test
    public void TestPostivieMinusNegative() {
        int answer = intValue(equation.calculate("-", 44, -22));
        assertEquals(answer, 66);
    }

    @Test
    public void TestZeroMinusPositive() {
        int answer = intValue(equation.calculate("-", 0, 22));
        assertEquals(answer, -22);
    }

    @Test
    public void TestZeroMinusZero() {
        int answer = intValue(equation.calculate("-", 0, 0));
        assertEquals(answer, 0);
    }

    @Test
    public void TestZeroMinusNegative() {
        int answer = intValue(equation.calculate("-", 0, -20));
        assertEquals(answer, 20);
    }

    @Test
    public void TestNegativeMinusPositive() {
        int answer = intValue(equation.calculate("-", -20, 20));
        assertEquals(answer, -40);
    }

    @Test
    public void TestNegativeMinusZero() {
        int answer = intValue(equation.calculate("-", -20, 0));
        assertEquals(answer, -20);
    }

    @Test
    public void TestNegativeMinusNegative() {
        int answer = intValue(equation.calculate("-", -20, -40));
        assertEquals(answer, 20);
    }

    @Test
    public void TestPositiveTimesPositive() {
        int answer = intValue(equation.calculate("*", 2, 40));
        assertEquals(answer, 80);
    }

    @Test
    public void TestPositiveTimesZero() {
        int answer = intValue(equation.calculate("*", 2, 0));
        assertEquals(answer, 0);
    }

    @Test
    public void TestPositiveTimesNegative() {
        int answer = intValue(equation.calculate("*", 2, -20));
        assertEquals(answer, -40);
    }

    @Test
    public void TestZeroTimesPositive() {
        int answer = intValue(equation.calculate("x", 0, 20));
        assertEquals(answer, 0);
    }

    @Test
    public void TestZeroTimesZero() {
        int answer = intValue(equation.calculate("*", 0, 0));
        assertEquals(answer, 0);
    }

    @Test
    public void TestZeroTimesNegative() {
        int answer = intValue(equation.calculate("*", 0, -40));
        assertEquals(answer, 0);
    }

    @Test
    public void TestNegativeTimesPositive() {
        int answer = intValue(equation.calculate("*", -40, 2));
        assertEquals(answer, -80);
    }

    @Test
    public void TestNegativeTimesZero() {
        int answer = intValue(equation.calculate("*", -40, 0));
        assertEquals(answer, 0);
    }

    @Test
    public void TestNegativeTimesNegative() {
        int answer = intValue(equation.calculate("*", -40, -2));
        assertEquals(answer, 80);
    }
    @Test
   public void TestPositiveDividedByPositive()
    {
        double answer = equation.calculate("/", 40, 20);
        assertEquals(answer, 2, .000001);
}
   @Test
   public void TestPositiveDividedByZero()
    {
        try { 
        double answer = equation.calculate("/", 40, 0);
        Assert.fail();
        } catch (Exception ex) {
        Assert.assertTrue(true);
        }
        
}   
   @Test
   public void TestPositiveDividedByNegative()
    {
        double answer = equation.calculate("/", 40, -10);
        assertEquals(answer, -4, .000001);
}
   @Test
   public void TestZeroDividedByPositive()
    {
        double answer = equation.calculate("/", 0, 10);
        assertEquals(answer, 0, .000001);
}
   @Test
  public void TestZeroDividedByZero()
    {
        try { 
        double answer = equation.calculate("/", 0, 0);
        Assert.fail();
        } catch (Exception ex) {
        Assert.assertTrue(true);
        }
    }

   @Test
   public void TestZeroDividedByNegative()
    {
        double answer = equation.calculate("/", 0, -20);
        assertEquals(answer, 0, .000001); 
}
   @Test
   public void TestNegativeDividedByPositive()
    {
        double answer = equation.calculate("/", -10, 2);
        assertEquals(answer, -5, .000001);
}
   @Test
   public void TestNegativeDividedByZero()
    {
        try { 
        double answer = equation.calculate("/", -40, 0);
        Assert.fail();
        } catch (Exception ex) {
        Assert.assertTrue(true);
        }
    }
   @Test
   public void TestNegativeDividedByNegative()
    {
        double answer= equation.calculate("/", -10, -2);
        assertEquals(answer, 5, .000001);
} 
   
   @Test
   public void TestNormalRangeNormalStringOutOfRange() throws Exception
   {
       boolean exception = false;
       
       try{
       fido.testIntForRange(-100,100, 10000);
       } catch(Exception ex){
           exception = true;
       }
       
       assertEquals(exception, true);
       
   }
   
      @Test
   public void TestBadRangeNormal()
   {
       boolean exception = false;
       
       try{
       fido.testIntForRange(100,-100, 50);
       } catch(Exception ex) {
           exception = true;
       }
       
       assertEquals(exception, true);
   }  
}