package test;


import org.testng.Assert;
import org.testng.annotations.Test;

public class InverterTest {

    @Test
    public void showldInvert() {
        try {
            String inverted1 = Inverter.invert(null);
            String inverted2 = Inverter.invert("A");
            String inverted3 = Inverter.invert("ABC");
            Assert.assertEquals("", inverted1);
            Assert.assertEquals("A", inverted2);
            Assert.assertEquals("CBA", inverted3);

            String inverted4 = Inverter.invert("");
            Assert.assertEquals("", inverted4);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void showldInvert2() {
        int abc = 10;
        try {
            Assert.assertEquals("CBA", abc);
        } catch (Exception e) {
            Assert.fail("Can't be do it");
            e.printStackTrace();
        }

    }

}
