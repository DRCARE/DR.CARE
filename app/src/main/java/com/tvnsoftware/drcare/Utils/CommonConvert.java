package com.tvnsoftware.drcare.Utils;

/**
 * Created by Thieusike on 7/23/2017.
 */

public class CommonConvert {
    public static Integer stringToInterge(String string){
        int myNum = 0;

        try {
            myNum = Integer.parseInt(string);
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        return myNum;
    }
}
