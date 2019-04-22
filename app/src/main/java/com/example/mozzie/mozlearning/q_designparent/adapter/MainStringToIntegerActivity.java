package com.example.mozzie.mozlearning.q_designparent.adapter;

/**
 * Created by mozzie on 17/8/5.
 适配器模式：A和C接口不兼容，需要找一个B做中间件的适配
 */

public class MainStringToIntegerActivity {

    public static void main(String args[]){
        onDownloadChange(1L,2L);
        onDownloadChange(3L,2L);
        onDownloadChange(10000000000L,2L);
        onDownloadChange(0L,2L);
        onDownloadChange(-1L,2L);
        onDownloadChange(-1L,-22L);
        onDownloadChange(1232312312312312312L,127392173912837129L);
    }


    public static void onDownloadChange(long downloadBytes, long totalBytes) {
        System.out.println();
        System.out.print(downloadBytes + "++++++" + totalBytes);
        System.out.print(String.valueOf((int) (downloadBytes * 100 / totalBytes)));
        System.out.print("------");
        System.out.print(String.valueOf(100));
        System.out.println();
    }
}
