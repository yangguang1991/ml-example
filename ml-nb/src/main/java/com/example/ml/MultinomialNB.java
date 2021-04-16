package com.example.ml;

import com.example.util.HdfsUtil;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import scala.Tuple2;

public class MultinomialNB {


    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("NaiveBayesTest").setMaster("local[*]");
        //.setMaster("local[*]");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        //读取文件的每一行到RDD
        if (args.length == 0) {
            System.out.println("please input trainData path.");
            return;
        }
        //训练集 /home/ymdxtest/yangguang/linecommon/machine_learning/train.txt
        JavaRDD<String> lines = jsc.textFile(args[0]);
        //对每一行进行拆分，封装成LabeledPoint
        JavaRDD<LabeledPoint> data = lines.map(new Function<String, LabeledPoint>() {
            private static final long serialVersionUID = 1L;

            @Override
            public LabeledPoint call(String str) throws Exception {

                String[] t1 = String.valueOf(str).split(",");
                String[] t2 = t1[1].split(" ");
                LabeledPoint a = new LabeledPoint(Double.parseDouble(t1[0]),
                        Vectors.dense(Double.parseDouble(t2[0]), Double.parseDouble(t2[1]), Double.parseDouble(t2[2])));

                return a;
            }
        });

        //朴素贝叶斯模型训练  bernoulli 用来处理特征是二分的，multinomial用来处理特征是多分的
        final NaiveBayesModel model = NaiveBayes.train(data.rdd(), 1.0, "multinomial");

        // 直接利用模型计算答案，测试集由外部输入
        JavaRDD<String> testData1 = jsc.textFile("/home/ymdxtest/yangguang/linecommon/machine_learning/testData.txt");
        JavaPairRDD<String, Double> res = testData1.mapToPair(new PairFunction<String, String, Double>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Tuple2<String, Double> call(String line) throws Exception {
                String[] t2 = line.split(" ");
                Vector v = Vectors.dense(Double.parseDouble(t2[0]), Double.parseDouble(t2[1]),
                        Double.parseDouble(t2[2]));
                double res = model.predict(v);
                Vector vp = model.predictProbabilities(v);
                //System.out.print("v=" + v.toJson() + " , vp=" + vp.toJson() + ",res=" + res);
                System.out.print("v=" + v.toJson() + " , vp=" + vp.toJson() + ",res=" + res);
                System.out.println();
                return new Tuple2<String, Double>(line, res);
            }
        });

        //将计算结果保存
        String res_path = "/home/ymdxtest/yangguang/linecommon/machine_learning/res_multinomial";
        HdfsUtil.deleteFile(res_path);
        res.saveAsTextFile(res_path);
        //保存模型后，这个模型就可以重复使用了
        String model_path = "/home/ymdxtest/yangguang/linecommon/machine_learning/BayesModel_multinomial/";
        HdfsUtil.deleteFile(model_path);
        model.save(jsc.sc(), model_path);
    }
}
