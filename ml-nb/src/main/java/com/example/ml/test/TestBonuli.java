package com.example.ml.test;

public class TestBonuli {


    //验证伯努利分布的概率公式  求解
    public static void main(String[] args) {

        double piLogDenom = Math.log(5 + 2 * 1);//总的样本个数 + 拉普拉斯平滑的值
        System.out.println("piLogDenom=" + piLogDenom);
        double p0 = Math.log(3 + 1) - piLogDenom; // label=0
        System.out.println("p0=" + p0);

        double p1 = Math.log(2 + 1) - piLogDenom; // label=1
        System.out.println("p1=" + p1);

        //label=0的条件概率      多项式分布中可以出现多次
        double thetaLogDenom0 = Math.log(3 + 2 * 1);
        double theta0_1 = Math.log(3 + 1) - thetaLogDenom0;
        System.out.println("theta0_1=" + theta0_1);

        double theta0_2 = Math.log(2 + 1) - thetaLogDenom0;
        System.out.println("theta0_2=" + theta0_2);

        double theta0_3 = Math.log(0 + 1) - thetaLogDenom0;
        System.out.println("theta0_3=" + theta0_3);


        double thetaLogDenom1 = Math.log(2 + 2 * 1);
        double theta1_1 = Math.log(0 + 1) - thetaLogDenom1;
        System.out.println("theta1_1=" + theta1_1);

        double theta1_2 = Math.log(2 + 1) - thetaLogDenom1;
        System.out.println("theta1_2=" + theta1_2);

        double theta1_3 = Math.log(1 + 1) - thetaLogDenom1;
        System.out.println("theta1_3=" + theta1_3);


//        v={"type":1,"values":[0.0,1.0,0.0]},res=1.0
//        v={"type":1,"values":[1.0,0.0,0.0]},res=0.0
//        v={"type":1,"values":[0.0,1.0,1.0]},res=1.0


        System.out.println("==================");
        //v={"type":1,"values":[1.0,1.0,0.0]},res=0.0
        double re0 = p0 + theta0_1 * 1.0 + theta0_2 * 1.0 + Math.log(1.0 - Math.exp(theta0_3));
        System.out.println(re0);
        double re1 = p1 + theta1_1 * 1.0 + theta1_2 * 1.0 + Math.log(1.0 - Math.exp(theta1_3));
        System.out.println(re1);
        System.out.println("==================");
        //v={"type":1,"values":[0.0,1.0,0.0]},res=1.0
        double re01 = p0 + Math.log(1.0 - Math.exp(theta0_1)) * 1.0 + theta0_2 * 1.0 + Math.log(1.0 - Math.exp(theta0_3)) * 1;
        System.out.println(re01);
        double re11 = p0 + Math.log(1.0 - Math.exp(theta1_1)) * 1.0 + theta1_2 * 1.0 + Math.log(1.0 - Math.exp(theta1_3)) * 1;
        System.out.println(re11);

    }
}
