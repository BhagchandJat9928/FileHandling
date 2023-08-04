/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package labs.fh.app;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import labs.fh.data.FileSystem;

/**
 *
 * @author bhagc
 */
public class MyFiles {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        try {
            //        FileReadAndWrite frd = new FileReadAndWrite();
//          frd.binaryReadAndWrite("E:\\NeatBeansProjects\\FileHandling\\src\\labs\\fh\\data\\some.txt",
//                "E:\\NeatBeansProjects\\FileHandling\\src\\labs\\fh\\data\\other.txt");
//
//             System.out.println("Closed");
//       frd.characterReadAndWrite("E:\\NeatBeansProjects\\FileHandling\\src\\labs\\fh\\data\\some.txt",
//               "E:\\NeatBeansProjects\\FileHandling\\src\\labs\\fh\\data\\other.txt");
//frd.streamReadAndWrite("E:\\NeatBeansProjects\\FileHandling\\src\\labs\\fh\\data\\some.txt",
//                   "E:\\NeatBeansProjects\\FileHandling\\src\\labs\\fh\\data\\other.txt");
//       frd.serializeAndDeserialize(
//               "E:\\NeatBeansProjects\\FileHandling\\src\\labs\\fh\\data\\swap");
//
//frd.fileSystem();
//frd.ConsoleInputOutput();

FileSystem fs=new FileSystem();
//fs.fileSystemPath();
//fs.fileNavigation();
//fs.fileAttrinbutes();
//fs.modifyFileAttributes();
//fs.fileOperations();
//fs.fileCopyAndMove();
//fs.deletePath();
//fs.zipHandling();
fs.httpHandle();
        } catch (InterruptedException ex) {
            Logger.getLogger(MyFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
