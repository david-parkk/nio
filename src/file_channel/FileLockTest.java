package org.example.nio.file_channel;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Scanner;

public class FileLockTest {

    public static void main(String[] args) throws Exception{
        FileChannel fileChannel = null;
        try{
            fileChannel=new RandomAccessFile("src/main/java/org/example/nio/file_channel/test.doc","rw").getChannel();
            FileLock fileLock = fileChannel.lock(0,Long.MAX_VALUE,true);
            
            while(true){
                Scanner scanner=new Scanner(System.in);


                try{
                    if(fileLock!=null){
                        System.out.println("File is locked");
                    }
                }finally {

                }
                int i = scanner.nextInt();
                if(i==0){
                    fileLock.release();
                    break;
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fileChannel!=null)
                fileChannel.close();
        }
    }
}
