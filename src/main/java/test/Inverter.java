package test;

public class Inverter {
    public static String invert(Object str) throws Exception {
        if (str instanceof String) {
            String str1 = str + "";
            if (str1 == null || str1.equals("")) {
                return "";
            } else if (str1.length() == 1) {
                return str1;
            } else {
                byte[] strAsByteArray = str1.getBytes();
                byte[] result = new byte[strAsByteArray.length];
                for (int i = 0; i < strAsByteArray.length; i++)
                    result[i] = strAsByteArray[strAsByteArray.length - i - 1];
                return new String(result);
            }
        }else{
            throw new Exception("Can't be do it");
        }
    }
}
