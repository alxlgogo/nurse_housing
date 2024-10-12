package test;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import utils.RConnectionUtils;

import java.io.IOException;

public class TestR {
    public static void main(String[] args) throws REngineException, REXPMismatchException, IOException {
//        testRscript();
//        RConnectionUtils.ageStay_time("ageStay_timeNo001");
//        RConnectionUtils.avgLife_year(null);
//        RConnectionUtils.expectancyAge(85);

        String year = "2022,2024,2026,2028,2030,2032,2040";
        String gender = "male";
        RConnectionUtils.lifeExpectancy(gender, year);
        System.out.println();
    }


    private static void testRscript() throws RserveException, IOException, REXPMismatchException {
        RConnection connection = new RConnection();
        String rPath = "/Users/lwj/RScript/test.R";
        connection.eval("source('" + rPath + "')");
        int num = 9;
        REXP rexp = connection.eval("myFunc(" + num + ")");
        System.out.println(rexp.asDouble());
        connection.close();
    }
}
