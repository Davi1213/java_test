package main.java.com.serviceImpl;

/**
 * @author louwei
 * @ClassName: volatile_ordering: 有序性
 * @Description:
 */

public class volatile_ordering {

    public void mySort() {
        int x = 11;  //语句1
        int y = 12;  //语句2
        x = x + 5;   //语句3
        y = x * x;   //语句4
    }


}
