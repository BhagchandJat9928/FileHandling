/*
 * Copyright (C) 2023 bhagc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package labs.fh.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import labs.fh.app.MyFiles;

/**
 *
 * @author bhagc
 */
public class FileReadAndWrite {

    public void binaryReadAndWrite(String inputFile, String outputFile) {
        try ( InputStream in = new FileInputStream(inputFile);  OutputStream out = new FileOutputStream(outputFile)) {
            byte[] buffer = new byte[24];
            int length = 0;
            while ((length = in.read(buffer)) != -1) {
                System.out.println(length);

                out.write(buffer, 0, length);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyFiles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void characterReadAndWrite(String inputFile, String outputFile) {
        Charset utf8 = Charset.forName("UTF-8");
        try ( Reader in = new FileReader(inputFile, utf8);  Writer out = new FileWriter(outputFile, utf8)) {
            char[] buffer = new char[24];
            int length = 0;

            while ((length = in.read(buffer)) != -1) {
                System.out.println(length);
                //  out.append(Arrays.toString(buffer),0, length);
                out.write(buffer, 0, length);

            }
        } catch (IOException ex) {
            Logger.getLogger(FileReadAndWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void streamReadAndWrite(String inp, String outFile) {
        Charset utf8 = Charset.forName("UTF-8");
        try ( BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inp), utf8));  PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outFile), utf8))) {
            in.lines().forEach(s -> {
                System.out.println(s);
                out.println(s);
            });
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReadAndWrite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileReadAndWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void standardInputOutput() {
        Scanner sc = new Scanner(System.in);
        String line = null;
        System.out.println("To quit press: exit");
        while (!(line = sc.nextLine()).equals("exit")) {
            System.out.println(line);
        }
    }

    public void serializeAndDeserialize(String file) {
        Item item = new Item();
        item.addProduct(new Product(101, "Tea"));
        item.addProduct(new Product(102, "Pizza"));

        try ( ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(item);
                item=null;
        } catch (IOException ex) {
            Logger.getLogger(FileReadAndWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try( ObjectInputStream in=new ObjectInputStream(new FileInputStream(file))){
            item=(Item)in.readObject();
          System.out.println(item);
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReadAndWrite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FileReadAndWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fileSystem(){
     
            FileSystem fs=FileSystems.getDefault();
            fs.getFileStores().forEach(s->{
                try {
                    System.out.println(s.getTotalSpace()+"  "+s.type());
                } catch (IOException ex) {
                    Logger.getLogger(FileReadAndWrite.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            fs.getRootDirectories().forEach(e->System.out.println(e));
            String sperator=fs.getSeparator();
            System.out.println(sperator);
        
        
    }
    }
