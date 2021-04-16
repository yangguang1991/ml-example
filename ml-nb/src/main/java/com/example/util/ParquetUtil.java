package com.example.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.example.GroupReadSupport;

import java.io.IOException;

public class ParquetUtil {


    public static Log log = LogFactory.getLog(ParquetUtil.class);



    //可以直接读取hdfs的parquet文件的内容，非常的方便

    public static void partquetReader(String inPath) throws IOException {

        System.out.println("read start.......");
        GroupReadSupport readSupport = new GroupReadSupport();
        Path path = new Path(inPath);
        ParquetReader<Group> reader = new ParquetReader<Group>(path, readSupport);
        Group line = null;

        while ((line = reader.read()) != null) {
            System.out.println(line.toString());
        }
        System.out.println("read end.......");
    }

    public static void main(String[] args) throws IOException {

        partquetReader(args[0]);


    }

}
