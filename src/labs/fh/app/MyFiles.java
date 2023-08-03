/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package labs.fh.app;

import labs.fh.data.FileReadAndWrite;

/**
 *
 * @author bhagc
 */
public class MyFiles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        FileReadAndWrite frd = new FileReadAndWrite();
          frd.binaryReadAndWrite("E:\\NeatBeansProjects\\FileHandling\\src\\labs\\fh\\data\\some.txt",
                "E:\\NeatBeansProjects\\FileHandling\\src\\labs\\fh\\data\\other.txt");

             System.out.println("Closed");
       frd.characterReadAndWrite("E:\\NeatBeansProjects\\FileHandling\\src\\labs\\fh\\data\\some.txt",
               "E:\\NeatBeansProjects\\FileHandling\\src\\labs\\fh\\data\\other.txt");
frd.streamReadAndWrite("E:\\NeatBeansProjects\\FileHandling\\src\\labs\\fh\\data\\some.txt",
                   "E:\\NeatBeansProjects\\FileHandling\\src\\labs\\fh\\data\\other.txt");
       frd.serializeAndDeserialize(
               "E:\\NeatBeansProjects\\FileHandling\\src\\labs\\fh\\data\\swap");

frd.fileSystem();
    }

}
