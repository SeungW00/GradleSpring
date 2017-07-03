package Calcu;

import java.io.*;


/**
 * Created by Administrator on 2017-07-03.
 */
public class Calculator {

    private BufferedReader br = null;

    public Integer calcSum(String filepath) throws IOException {

        LineCallback<Integer> sumcallback = new LineCallback<Integer>(){

            @Override
            public Integer doSomethingWithReader(String line, Integer value) {
                return value+Integer.valueOf(line);
            }

        };
       return lineReadTemplate(filepath, sumcallback,0);

    }

    public <T> T lineReadTemplate(String filepath, LineCallback<T> callback, T initVal) throws IOException{
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(filepath));
        T res = initVal;
        String line = null;

        while ((line = br.readLine())!=null) {
            res = callback.doSomethingWithReader(line,res);
        }

        return res;
    }


    public Integer calcMultiply(String filepath) throws IOException{

        LineCallback<Integer> multiplyCalback = new LineCallback<Integer>(){

            @Override
            public Integer doSomethingWithReader(String line, Integer value) {
                return value * Integer.valueOf(line);
            }



        };
        return lineReadTemplate(filepath, multiplyCalback,1);
    }

    public String concatenate(String filepath) throws IOException{
        LineCallback<String> concatenateCallback = new LineCallback<String>() {
            @Override
            public String doSomethingWithReader(String line, String value) {
                return value+=line;
            }
        };

        return lineReadTemplate(filepath,concatenateCallback,"");
    }
}
