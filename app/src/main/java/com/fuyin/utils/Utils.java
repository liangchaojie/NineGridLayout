package com.fuyin.utils;

import com.fuyin.demo.ninegrid.Name;

/**
 * @Description:
 * @Author: Liangchaojie
 * @Create On 2018/4/2 19:40
 */
public class Utils {
    public static String getNameByPosition(int itemPosition,int i) {
        String name = itemPosition+"_"+Name.IMAGE_1;
        switch (i){
            case 0:
                name = itemPosition+"_"+Name.IMAGE_1;
                break;
            case 1:
                name = itemPosition+"_"+Name.IMAGE_2;
                break;
            case 2:
                name = itemPosition+"_"+Name.IMAGE_3;
                break;
            case 3:
                name = itemPosition+"_"+Name.IMAGE_4;
                break;
            case 4:
                name = itemPosition+"_"+Name.IMAGE_5;
                break;
            case 5:
                name = itemPosition+"_"+Name.IMAGE_6;
                break;
            case 6:
                name = itemPosition+"_"+Name.IMAGE_7;
                break;
            case 7:
                name = itemPosition+"_"+Name.IMAGE_8;
                break;
            case 8:
                name = itemPosition+"_"+Name.IMAGE_9;
                break;

        }
        return name;
    }
}
