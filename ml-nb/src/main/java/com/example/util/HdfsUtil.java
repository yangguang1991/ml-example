package com.example.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class HdfsUtil {


    public static void deleteFile(String path) {
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(new Configuration());
            fileSystem.delete(new Path(path), true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileSystem.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void copyFileFromLocalToHdfs(String srcPath,String  desPath) {
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(new Configuration());
            fileSystem.copyFromLocalFile(new Path(srcPath),new Path(desPath));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileSystem.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }






}
