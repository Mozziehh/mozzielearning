package com.example.mozzie.mozlearning.q_designparent.component;

import java.util.ArrayList;

/**
 * Created by mozzie on 17/8/5.
 * 组合模式允许你将对象组合成树形结构来表现”部分-整体“的层次结构，使得客户以一致的方式处理单个对象以及对象的组合。
 组合模式实现的最关键的地方是——简单对象和复合对象必须实现相同的接口。这就是组合模式能够将组合对象和简单对象进行一致处理的原因。
 -root
 ---Leaf1
 ---Leaf2
 ---leaf6
 ---SubRoot
 -----leaf3
 -----leaf4
 -----leaf5
 */

public class MainActivity {

    public static void main(String args[]){
//        System.out.println("========组合模式========");
//
//        ComponentImpl root = new ComponentImpl("root");
//        root.add(new Leaf("Leaf1"));
//        root.add(new Leaf("Leaf2"));
//
//        ComponentImpl subRoot = new ComponentImpl("SubRoot");
//        subRoot.add(new Leaf("leaf3"));
//        subRoot.add(new Leaf("leaf4"));
//        subRoot.add(new Leaf("leaf5"));
//
//        Leaf leaf6 = new Leaf("leaf6");
//        root.add(leaf6);
////        root.delete(leaf6);
//        subRoot.add(leaf6);
//        root.add(subRoot);
//        root.show(1);

        //A = [1,2,5], B = [2,4]    [5,4]

        int[] height = new int[12];
        height[0] = 0;
        height[1] = 1;
        height[2] = 0;
        height[3] = 2;
        height[4] = 1;
        height[5] = 0;
        height[6] = 1;
        height[7] = 3;
        height[8] = 2;
        height[9] = 1;
        height[10] = 2;
        height[11] = 1;
//
//          int[] height = new int[]{0,2,0};
//        height[0] = 0;
//        height[1] = 2;
//        height[2] = 0;

//        height[0] = 4;
//        height[1] = 2;
//        height[2] = 3;


//        height[0] = 5;
//        height[1] = 2;
//        height[2] = 1;
//        height[3] = 2;
//        height[4] = 1;
//        height[5] = 5;

//        height[0] = 2;
//        height[1] = 0;
//        height[2] = 2;
        //找到最大值；
        int result = 0;
        int arrayMax = -1;

        if(height.length == 0){
            return ;
        }

        //简化数组
        int arrayMin = height[0];
        for(int i = 0 ; i < height.length ; i ++){
            if(arrayMax < height[i]){
                arrayMax = height[i];
            }
            if(arrayMin > height[i]){
                arrayMin = height[i];
            }
        }
        if(arrayMax == -1 || arrayMax == 0) {
            return ;
        }

        //建立一个集合装数组；
        ArrayList<Integer> AList = new ArrayList<>();
        for(int i = 0 ; i < height.length ; i ++){
            height[i] = height[i] - arrayMin;
            AList.add(height[i]);
        }

        for(int m = 0 ; m < arrayMax; m ++){
            //去除左右两侧的0；
            boolean delateFlag = true;
            for(int i = 0 ; i < AList.size() ; i ++){
                if(i < AList.size()){
                    if(AList.get(i) != 0){
                        delateFlag = false;
                        break;
                    }
                    if(delateFlag){
                        AList.remove(i);
                        i--;
                    }
                }
            }
            delateFlag = true;
            for(int i = AList.size() - 1 ; i > 0 ; i --){
                if(i < AList.size()){
                    if(AList.get(i) != 0){
                        delateFlag = false;
                        break;
                    }
                    if(delateFlag){
                        AList.remove(i);
                        i++;
                    }
                }
            }

            //找一共有多少个0，并计数；
            for(int i = 0 ; i < AList.size() ; i ++){
                if(AList.get(i) == 0){
                    result ++;
                    AList.set(i, AList.get(i) + 1);
                }
                int yuanzhi = AList.get(i);
                AList.set(i, yuanzhi - 1);
            }
        }
        System.out.print(result);
    }
}
