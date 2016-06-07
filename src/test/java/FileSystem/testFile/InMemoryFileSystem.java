package FileSystem.testFile;

import static org.junit.Assert.*;

import java.io.IOException;
import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;


public class InMemoryFileSystem {
  FileSystem fs = FileUtil.createMemoryFileSystem();
  FileObject root = fs.getRoot();
  
  /*
   *  Develop and test in-memory virtual filesystem, in the context of JSR-203
   */
  @Before
  public void setUp() {
      try {

          //Generate a virtual folder:
          FileObject testDataFolder = root.createFolder("Test");

          //Generate a virtual file:
          FileObject testData1 = testDataFolder.createData("testData1");
          //Create three virtual attributes for the file:
          testData1.setAttribute("name", "sumanta");
          testData1.setAttribute("age", 28);
          testData1.setAttribute("employed", true);

          //Generate a second virtual file:
          FileObject testData2 = testDataFolder.createData("testData2");
          //create three virtual attributes for the file:
          testData2.setAttribute("name", "sachin");
          testData2.setAttribute("age", 34);
          testData2.setAttribute("employed", false);
          
          //copy files
          testData1.copy(testData2, "age", "employment");
          
          //Display file contents
          testData1.asText();
          
          //Search for a file by name
          testData1.getName();

      } catch (IOException ex) {
          Exceptions.printStackTrace(ex);
      }
  }

  //Positive test case with test data.
  @Test
  public void testData1() {
      FileObject testData1 = root.getFileObject("TestData/testData1");
      Assert.assertTrue(testData1.isValid());
      assertEquals(testData1.getAttribute("name"), "John");
      assertEquals(testData1.getAttribute("age"), 27);
      assertEquals(testData1.getAttribute("employed"), true);
  }

  //Failure test case with test data.
  @Test
  public void testData2() {
      FileObject testData2 = root.getFileObject("TestData/testData2");
      assertEquals(testData2.getAttribute("name"), "Jane");
      assertEquals(testData2.getAttribute("age"), 33);
      assertEquals(testData2.getAttribute("employed"), false);
  }
}
