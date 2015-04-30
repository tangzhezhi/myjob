package org.tang.myjob.utils.File;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2015/4/30.
 */
public class FileUtil {

    /**
     * 复制文件(以超快的速度复制文件)
     *
     * @param srcFile
     *            源文件File
     * @param destDir
     *            目标目录File
     * @param newFileName
     *            新文件名
     * @return 实际复制的字节数，如果文件、目录不存在、文件为null或者发生IO异常，返回-1
     */
    public static long copyFile(File srcFile, File destDir, String newFileName) {
        long copySizes = 0;
        if (!srcFile.exists()) {
            System.out.println("源文件不存在");
            copySizes = -1;
        } else if (!destDir.exists()) {
            System.out.println("目标目录不存在");
            copySizes = -1;
        } else if (newFileName == null) {
            System.out.println("文件名为null");
            copySizes = -1;
        } else {
            try {
                FileChannel fcin = new FileInputStream(srcFile).getChannel();
                FileChannel fcout = new FileOutputStream(new File(destDir,
                        newFileName)).getChannel();
                long size = fcin.size();
                fcin.transferTo(0, fcin.size(), fcout);
                fcin.close();
                fcout.close();
                copySizes = size;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return copySizes;
    }
}
