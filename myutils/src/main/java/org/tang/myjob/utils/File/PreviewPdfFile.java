package org.tang.myjob.utils.File;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.tang.myjob.utils.constant.FileConstant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/5/4.
 */
public class PreviewPdfFile {

    private static String openOfficePath = FileConstant.openOfficeDir;

    /**
     * 将Office文档转换为PDF. 运行该函数需要用到OpenOffice和jodconverter-2.2.2
     * 使用步骤：
     * 1.下载OpenOffice软件和jodconverter-2.2.2
     * 2.安装OpenOffice软件
     * 3.将jodconverter-2.2.2压缩包解压，将lib目录下的包全部加入路径
     *   即可直接调用方法
     *
     * <pre>
     * 方法示例:
     * String sourcePath = "F:\\office\\source.doc";
     * String destFile = "F:\\pdf\\dest.pdf";
     * Converter.office2PDF(sourcePath, destFile);
     * </pre>
     *
     * @param sourceFile
     *            源文件, 绝对路径. 可以是Office2003-2007全部格式的文档, Office2010的没测试. 包括.doc,
     *            .docx, .xls, .xlsx, .ppt, .pptx等. 示例: F:\\office\\source.doc
     * @param destFile
     *            目标文件. 绝对路径. 示例: F:\\pdf\\dest.pdf
     * @return 操作成功与否的提示信息. 如果返回 -1, 表示找不到源文件, 或url.properties配置错误; 如果返回 0,
     *         则表示操作成功; 返回1, 则表示转换失败
     */
//    public static int windowsSystemOffice2PDF(String sourceFile, String destFile) throws IOException {
//        try {
//            File inputFile = new File(sourceFile);
//            if (!inputFile.exists()) {
//                return -1;// 找不到源文件, 则返回-1
//            }
//
//            // 如果目标路径不存在, 则新建该路径
//            File outputFile = new File(destFile);
//            if (!outputFile.getParentFile().exists()) {
//                outputFile.getParentFile().mkdirs();
//            }
//
//            String OpenOffice_HOME = openOfficePath;//这里是OpenOffice的安装目录
//            // 如果从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'
//            if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '\\') {
//                OpenOffice_HOME += "\\";
//            }
//            // 启动OpenOffice的服务
//            String command = OpenOffice_HOME
//                    + "program\\soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";
//            Process pro = Runtime.getRuntime().exec(command);
//            // connect to an OpenOffice.org instance running on port 8100
//            OpenOfficeConnection connection = new SocketOpenOfficeConnection(
//                    "127.0.0.1", 8100);
//            connection.connect();
//
//            // convert
//            DocumentConverter converter = new OpenOfficeDocumentConverter(
//                    connection);
//            converter.convert(inputFile, outputFile);
//
//            // close the connection
//            connection.disconnect();
//            // 关闭OpenOffice服务的进程
//            pro.destroy();
//
//            return 0;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return -1;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return 1;
//    }


    /**
     * 将Office文档转换为PDF. 运行该函数需要用到OpenOffice, OpenOffice
     *            源文件,绝对路径. 可以是Office2003-2007全部格式的文档, Office2010的没测试. 包括.doc, .docx, .xls, .xlsx, .ppt, .pptx等.
     *
     */
    public static void windowsSystemOffice2PDF(String inputFilePath,String outputFilePath) {
        DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();

        String officeHome = getOfficeHome();
        config.setOfficeHome(officeHome);

        OfficeManager officeManager = config.buildOfficeManager();
        officeManager.start();

        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
//        String outputFilePath = getOutputFilePath(inputFilePath);
        File inputFile = new File(inputFilePath);
        if (inputFile.exists()) {// 找不到源文件, 则返回
            File outputFile = new File(outputFilePath);
            if (!outputFile.getParentFile().exists()) { // 假如目标路径不存在, 则新建该路径
                outputFile.getParentFile().mkdirs();
            }
            converter.convert(inputFile, outputFile);
        }

        officeManager.stop();
    }

    public static String getOutputFilePath(String inputFilePath) {
        String outputFilePath = inputFilePath.replaceAll(".doc", ".pdf");
        return outputFilePath;
    }

    public static String getOfficeHome() {
        String osName = System.getProperty("os.name");
        if (Pattern.matches("Linux.*", osName)) {
            return "/opt/openoffice.org4";
        } else if (Pattern.matches("Windows.*", osName)) {
            return openOfficePath;
        } else if (Pattern.matches("Mac.*", osName)) {
            return "/Application/OpenOffice.org.app/Contents";
        }
        return null;
    }


    public static boolean judgeIsOffice(String fileSuffix){
        boolean flag = false;
        switch (fileSuffix) {
            case "doc":
                flag = true;
                break;
            case "docx":
                flag = true;
                break;
            case "xls":
                flag = true;
                break;
            case "xlsx":
                flag = true;
                break;
            case "ppt":
                flag = true;
                break;
            case "pptx":
                flag = true;
                break;
            default:
                flag = false;
                break;
        }

        return  flag;
    }
}
