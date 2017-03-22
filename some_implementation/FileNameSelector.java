package algorithm.some_implementation;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * File类有一个listFiles(FileNameFilter filter).
 * 只需要实现一个简单的filter就可以找到满足fileter条件的所有文件。
 * 例如我要在E:\data\file_selector_test 目录下面查找以.txt结尾的文件,我可以这么做。见下例。
 * @author Administrator
 *
 */
public class FileNameSelector implements FilenameFilter {
    String extension = ".";

    public FileNameSelector(String fileExtensionNoDot) {
        extension += fileExtensionNoDot;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extension);
    }
    
    public static void main(String[] args) {
        
        File directory = new File("F:\\exercise\\tt");
        //listFiles()列出所有文件
        File[] files = directory.listFiles();
        System.out.println("\n目录" + directory.getName() + "下的所有文件");
        for (File file : files) {
            System.out.print("  " + file.getName());
        }
        //listFiles(FileNameFilter filter)列出所有.txt文件
        File[] txtFiles = directory.listFiles(new FileNameSelector("txt"));
        System.out.println("\n目录" + directory.getName() + "下的.txt文件");
        for (File file : txtFiles) {
            System.out.print("  " + file.getName());
        }
        //isDirectory()判断是否为文件目录
        if(directory.isDirectory()){
            System.out.println(directory.getPath()+" is Directory");
        }
        //isFile()判断是否为文件
        if(directory.isFile()){
            System.out.println(directory.getPath()+" is File");
        }
        
        //File.separator输出不同的系统中斜杠的方向
        //在windows中斜杠的方向是向右斜的\\
        //在Linux  中斜杠的方向是向左斜的//
        System.out.println("File.separator:"+File.separator);
        File f= new File("F:" + File.separator + "exercise" + File.separator+ "tt"+File.separator+"io.txt");
        try {
            //createNewFile()方法：创建指定文件夹下的文件，如果文件夹不存在会报"找不到指定路径"错误，应该首先手动创建该文件夹。返回值为boolean
            //若没有该文件，创建成功返回true；若又该文件则创建失败返回false
            System.out.println(f.createNewFile());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //delete()方法：删除一个文件或者整个文件夹，返回值为布尔类型
        //如果删除的是文件夹，必须保证是一个空文件夹，否则删除失败
        File f0 = new File("F:\\exercise\\tt");
        boolean boo=f0.delete();
        if(boo){
            System.out.println("成功删除文件");
        }else{
            System.out.println("删除失败");
        }
        //调用创建的方法进行删除文件夹以及该文件夹下的所有文件
       // deleteDirectory(new File("F:\\exercise\\tt\\test3"));
        
        File f1 = new File("F:" + File.separator + "exercise"+File.separator+"io.txt");
        //getPath()方法：将此抽象路径名转换为一个路径名字符串
        System.out.println(f1.getPath());
        //getParent()方法：返回此抽象路径名父目录的路径名字符串；如果此路径名没有指定父目录，则返回 nul
        System.out.println(f1.getParent());
        if(f1.exists()){
            f1.delete();
        }
        else{
            try{
                System.out.println(f1.createNewFile());
            }catch(Exception e){}
        }
        
        //list()方法：返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中的文件和目录，列出的仅是名称。
        File f2 = new File("F:\\exercise\\tt");
        String[] str = f2.list();
        for(int i=0;i<str.length;i++){
            System.out.println(str[i]);
        }
        
        //扫描某个路径下的所有文件
        findAllFiles(new File("F:\\exercise"));
    }
    
    //删除文件夹下的所有文件以及该文件夹
    public static void deleteDirectory(File file){
        File[] fileArray=file.listFiles();
        if(fileArray.length>0){
            for(int i=0;i<fileArray.length;i++){
                if(fileArray[i].isFile()){
                    if(fileArray[i].delete()){
                        System.out.println(fileArray[i]+"文件删除成功");
                    }else{
                        System.out.println(fileArray[i]+"文件删除失败");
                    }
                }else{
                    deleteDirectory(fileArray[i]);
                }
            }
        }
        //删除文件夹
        if(file.delete()){
            System.out.println(file+"文件夹删除成功");
        }else{
            System.out.println(file+"文件夹删除失败");
        }
    }

    //扫描指定文件夹下的所有文件，并输出
    public static void findAllFiles(File f){
        //判断给定的路径是否是目录
        if(f.isDirectory()){
            File[] file = f.listFiles();
            //再依次循环进行判断
            try{
                for(int i = 0;i < file.length;i++){
                    //继续把内容传入到findAllFiles方法之中进行验证
                    findAllFiles(file[i]);
                }
            }catch(Exception e){}
        }
        else{
            System.out.println(f);
        }
    }
    
    
}