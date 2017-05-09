package com.example.android.bakingappnanodegree.helper;


import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by micky on 07-May-17.
 */

public class Logger {
        static private Logger _instance = null;
        private String loggerFileName = "Base_Logger.txt";
        private BufferedWriter write = null;
        private boolean canWrite = false;

        private int limitedFileSize = 500000;
        private File root;
        private File toWriteFile;

        static public Logger instance() {
            if (_instance == null) _instance = new Logger();
            return _instance;
        }

        private Logger() {
            // root = Environment.getExternalStorageDirectory();
            // if(root.canRead() && root.canWrite())
            // {
            // toWriteFile = new File(root, loggerFileName);
            // try
            // {
            // write = new BufferedWriter(new FileWriter(toWriteFile,true));
            // canWrite = true;
            // }
            // catch (IOException e)
            // {
            // Log.v("Create Logger File",e.getMessage());
            // }
            // }
        }

        public String getLoggingTime() {
            String currentTime = "";
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
            currentTime = sdf.format(cal.getTime());
            return currentTime;
        }

        public void v(String tag, Object msg) {
            v(tag, msg, false);
        }

        public void v(String tag, Object msg, boolean writeToFile) {
            Log.v(tag, msg + "");
//		 if(canWrite && writeToFile)
            // {
            // try
            // {
            // //updateLogFile(toWriteFile,limitedFileSize);
            // write.write(tag+"\t"+getLoggingTime()+"\t\t"+msg);
            // write.newLine();
            // write.flush();
            // }
            // catch (IOException e)
            // {
            // // Log.v("verbose log",e.getMessage());
            // }
            // }
        }


        public void e(String tag, Object msg, boolean writeToFile) {
//		Log.e(tag, msg + "");
        }

        public static void updateLogFile(File fileName, int newLength) {
            BufferedWriter write = null;
            RandomAccessFile randomAccessFile = null;
            try {
                randomAccessFile = new RandomAccessFile(fileName, "rw");
                long length = randomAccessFile.length();
                if (length > (newLength)) {
                    System.out.println(getFileSize(fileName) + " " + length);
                    // randomAccessFile.setLength(length - 50 );
                    randomAccessFile.seek(newLength);
                    // Declare a buffer with the same length as the second line
                    byte[] buffer = new byte[((int) length - newLength)];
                    // Read data from the file
                    randomAccessFile.read(buffer);

                    // Print out the buffer contents
                    String newStr = new String(buffer);
                    System.out.println(newStr);
                    randomAccessFile.close();

                    String[] temp = newStr.split("\n");
                    System.out.println("temp " + temp.length);

                    write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
                    write.write(newStr.replaceFirst(temp[0], ""));
                    write.flush();
                    write.close();
                }
                randomAccessFile.close();
                // serverConnection();
            } catch (FileNotFoundException ex) {
//			Log.v("UpdateFile", ex.getMessage());
            } catch (IOException ex) {
//			Log.v("UpdateFile", ex.getMessage());
            }
        }

        public static long getFileSize(File file) {
            if (!file.exists() || !file.isFile()) {
                System.out.println("File doesn\'t exist");
                return -1;
            }
            // Here we get the actual size
            return file.length();
        }

        public void logFullMessage(String tag, Object message) {
            if (!TextUtils.isEmpty(message.toString()) && message.toString().length() > 4000) {
                int chunkCount = message.toString().length() / 4000; // integer division
                for (int i = 0; i <= chunkCount; i++) {
                    int max = 4000 * (i + 1);
                    if (max >= message.toString().length()) {
                        v(tag, message.toString().substring(4000 * i), false);
                    } else {
                        v(tag, message.toString().substring(4000 * i, max), false);
                    }
                }
                return;
            }
            v(tag, message + "", false);
        }
}
