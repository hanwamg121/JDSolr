package com.hnu;

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List; 
  
public class CopyDemo { 
  private static int a = 5; 
  static List<File> fileList = new ArrayList<File>();
  
  private static void test(String fileDir) {  
	    File file = new File(fileDir); 
	    File[] files = file.listFiles();// 获取目录下的所有文件或文件夹 
	    if (files == null) {// 如果目录为空，直接退出 
	      return; 
	    } 
	   
	    // 遍历，目录下的所有文件 
	    for (File f : files) { 
	      if (f.isFile()) { 
	        fileList.add(f); 
	      } else if (f.isDirectory()) { 
	        test(f.getAbsolutePath()); 
	      } 
	    } 

	  } 
	  
  
  public CopyDemo() { 
	  test("E:\\");
	    for (File f1 : fileList) { 
	      
    //需要复制的目标文件或目标文件夹 
    String pathname = f1.getAbsolutePath(); 
    File file = new File(pathname); 
    //复制到的位置 
    String topathname = "D:\\solr\\solr-7.5.0\\ImportData"; 
    File toFile = new File(topathname); 
    try { 
      copy(file, toFile); 
    } catch (Exception e) { 
      // TODO Auto-generated catch block 
      e.printStackTrace(); 
    } 
  } }
  
  public static void copy(File file, File toFile) throws Exception { 
    byte[] b = new byte[1024]; 
    int a; 
    FileInputStream fis; 
    FileOutputStream fos; 
    if (file.isDirectory()) { 
      String filepath = file.getAbsolutePath(); 
      filepath=filepath.replaceAll("\\\\", "/"); 
      String toFilepath = toFile.getAbsolutePath(); 
      toFilepath=toFilepath.replaceAll("\\\\", "/"); 
      int lastIndexOf = filepath.lastIndexOf("/"); 
      toFilepath = toFilepath + filepath.substring(lastIndexOf ,filepath.length()); 
      File copy=new File(toFilepath); 
      //复制文件夹 
      if (!copy.exists()) { 
        copy.mkdir(); 
      } 
      //遍历文件夹 
      for (File f : file.listFiles()) { 
        copy(f, copy); 
      } 
    } else { 
      if (toFile.isDirectory()) { 
        String filepath = file.getAbsolutePath(); 
        filepath=filepath.replaceAll("\\\\", "/"); 
        String toFilepath = toFile.getAbsolutePath(); 
        toFilepath=toFilepath.replaceAll("\\\\", "/"); 
        int lastIndexOf = filepath.lastIndexOf("/"); 
        toFilepath = toFilepath + filepath.substring(lastIndexOf ,filepath.length()); 
          
        //写文件 
        File newFile = new File(toFilepath); 
        fis = new FileInputStream(file); 
        fos = new FileOutputStream(newFile); 
        while ((a = fis.read(b)) != -1) { 
          fos.write(b, 0, a); 
        } 
      } else { 
        //写文件 
        fis = new FileInputStream(file); 
        fos = new FileOutputStream(toFile); 
        while ((a = fis.read(b)) != -1) { 
          fos.write(b, 0, a); 
        } 
      } 
  
    
    } 
   }
  
}