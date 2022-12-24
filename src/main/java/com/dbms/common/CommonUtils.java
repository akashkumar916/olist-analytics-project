package com.dbms.common;

import java.util.List;

/*
 * @author ayushkumar
 * @created on 06/12/22
 */
public class CommonUtils {

    public static Object[] toArray(List<String> list) {
        Object[] arr = new Object[list.size()];
        for(int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static String getParamPlaceholders(int count) {
        StringBuilder builder = new StringBuilder();

        for( int i = 0 ; i < count; i++ ) {
            builder.append("?,");
        }

        String placeHolders =  builder.deleteCharAt( builder.length() -1 ).toString();
        return placeHolders;
    }

}
