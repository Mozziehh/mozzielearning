import android.provider.Settings;
import android.util.Log;

import com.example.mozzie.mozlearning.b_utils.LOGGER;

/**
 * Created by mozzie on 17/10/26.
 */

public class Main {
//    //生成一个空的9*9的数组
//    function createArray(){
//        var arr=[]
//        for(var row=0;row<9;row++){
//            arr[row]=[]
//            for(var col=0;col<9;col++){
//                arr[row][col]=0
//            }
//        }
//        return arr
//    }
//
//    //生成1-9的随机数
//    function createNum(){
//        return Math.round(Math.random()*8+1)
//    }
//
//    //判断行内是否有重复的
//    function checkRow(arr,num,row,col){
//        for(i=col;i>0;i--){
//            if(num==arr[row][i-1]){
//                return false
//            }
//        }
//        return true
//    }
//
//    //判断列内是否有重复的
//    function checkCol(arr,num,row,col){
//        for(i=row;i>0;i--){
//            if(num==arr[i-1][col]){
//                return false
//            }
//        }
//        return true
//    }
//
//    //将符合的数字填进数组
//    function addNumber(){
//        var arr=createArray()
//        for(var row=0;row<9;row++){
//            for(var col=0;col<9;col++){
//                var num=createNum()
//                if(checkCol(arr,num,row,col)){
//                    arr[row][col]=num
//                }else{
//                    col--
//                }
//            }
//        }
//        return arr
//    }

    int mSuduku[][] = {};

    /**
     * 初始化数独
     */
    public void newSudu(){
        for(int i = 0 ; i < 9 ; i ++){
            for(int j = 0 ; j < 9 ; i ++){
                mSuduku[i][j] = 0;
            }
        }
    }

    /**
     * 检测行的有效性
     */
    public void validateRaw(){

    }

    /**
     * 检测列的有效性
     */
    public void validateCol(){

    }

    /**
     * 检测所在区域的正确性
     */
    public void validateCurrentRect(){

    }

    public static void main(String args[]){
        System.out.print("ff");
    }
}
