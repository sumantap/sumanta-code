package FileSystem.testFile;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class InSystemFileSystem {
  
  /*
   * Develop and test a in system file operation
   *
   */
  @BeforeClass
  public static void setUp(){
    System.out.println("Start test");
  }
  /*
   * Create a folder or directory
   */
  @Test
  public void testFolder() {
    String str = "C:\\Directory1";
    File file = new File(str);
    if (!file.exists()) {
      if (file.mkdir()) {
        System.out.println("Directory is created!");
        assertEquals(str, "C:\\Directory1");
        Assert.assertTrue(file.exists());
      } else {
        System.out.println("Failed to create directory!");
      }
    }
  }

  /*
   * Create a file
   */
  @Test
  public void testFile() {
    try {
      String str = "C:\\Directory1\\newFile.txt";
      File file = new File(str);
      if (file.createNewFile()) {
        System.out.println("File is created!");
        assertEquals(str, "C:\\Directory1\\newFile.txt");
        Assert.assertTrue(file.exists());
      } else {
        System.out.println("File already exists.");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /*
   * Append to a file
   */
  @Test
  public void testContent() {
    // TODO Auto-generated method stub
    try {
      String data = "This content will append to the end of the file";
      String str = "C:\\Directory1\\newFile1.txt";
      File file = new File(str);
      
      //if file doesnt exists, then create it
      if(!file.exists()){
        file.createNewFile();
      }
      
      // true = append file
      FileWriter fileWritter = new FileWriter(file.getName(), true);
      BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
      bufferWritter.write(data);
      bufferWritter.newLine();
      bufferWritter.close();

      System.out.println("Done");
      assertEquals(data, "This content will append to the end of the file");
      Assert.assertTrue(file.exists());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void delete(File file) throws IOException {

    if (file.isDirectory()) {

      // directory is empty, then delete it
      if (file.list().length == 0) {

        file.delete();
        System.out.println("Directory is deleted : " + file.getAbsolutePath());

      } else {

        // list all the directory contents
        String files[] = file.list();

        for (String temp : files) {
          // construct the file structure
          File fileDelete = new File(file, temp);

          // recursive delete
          delete(fileDelete);
        }

        // check the directory again, if empty then delete it
        if (file.list().length == 0) {
          file.delete();
          System.out.println("Directory is deleted : " + file.getAbsolutePath());
        }
      }

    } else {
      // if file, then delete it
      file.delete();
      System.out.println("File is deleted : " + file.getAbsolutePath());
    }
  }

  @AfterClass
  public static void teraDown() {
    final String SRC_FOLDER = "C:\\Directory1";
    File directory = new File(SRC_FOLDER);
    
    if (!directory.exists()) {
      System.out.println("Directory does not exist.");
      System.exit(0);
    } else {
      try {
        delete(directory);

      } catch (IOException e) {
        e.printStackTrace();
        System.exit(0);
      }
    }

    System.out.println("Done");
  }

}
