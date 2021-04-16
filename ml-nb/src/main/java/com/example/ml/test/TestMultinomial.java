package com.example.ml.test;


//计算出       多项式朴素贝叶斯的概率
public class TestMultinomial {


    public static void main(String[] args) {

        double piLogDenom = Math.log(12 + 2 * 1);//总的样本个数 + 拉普拉斯平滑的值
        System.out.println("piLogDenom=" + piLogDenom);
        double p0 = Math.log(6 + 1) - piLogDenom; // label=0
        System.out.println("p0=" + p0);

        double p1 = Math.log(6 + 1) - piLogDenom; // label=1
        System.out.println("p1=" + p1);

        //label=0的条件概率      多项式分布中可以出现多次
        double thetaLogDenom0 = Math.log(11 + 3 * 1);
        double theta0_1 = Math.log(5 + 1) - thetaLogDenom0;
        System.out.println("theta0_1=" + theta0_1);

        double theta0_2 = Math.log(4 + 1) - thetaLogDenom0;
        System.out.println("theta0_2=" + theta0_2);

        double theta0_3 = Math.log(2 + 1) - thetaLogDenom0;
        System.out.println("theta0_3=" + theta0_3);


        //label=1的条件概率      多项式分布中可以出现多次
        double thetaLogDenom1 = Math.log(12 + 3 * 1);
        double theta1_1 = Math.log(2 + 1) - thetaLogDenom1;
        System.out.println("theta1_1=" + theta1_1);

        double theta1_2 = Math.log(6 + 1) - thetaLogDenom1;
        System.out.println("theta1_2=" + theta1_2);

        double theta1_3 = Math.log(4 + 1) - thetaLogDenom1;
        System.out.println("theta1_3=" + theta1_3);


        System.out.println("==================");

        //0  1   0
        double re0 = p0 + theta0_2 * 1.0;
        System.out.println(re0);
        double re1 = p1 + theta1_2 * 1.0;
        System.out.println(re1);

        System.out.println("==================");

        //  2 0  1

        double re01 = p0 + theta0_1 * 2.0 + theta0_3 * 1.0;
        System.out.println(re01);
        double re11 = p1 + theta1_1 * 2.0 + theta1_3 * 1.0;
        System.out.println(re11);

    }
}
