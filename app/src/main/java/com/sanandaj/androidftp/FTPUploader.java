package com.sanandaj.androidftp;

/**
 * Created by a on 8/23/2017.
 */
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.sanandaj.androidftp.models.FtpFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import static android.content.ContentValues.TAG;

public class FTPUploader {

    FTPClient mFTPClient;

    public boolean ftpConnect(String host, String username, String password, int port) {
        try {
            mFTPClient = new FTPClient();
            // connecting to the host
            mFTPClient.connect(host, port);
            // now check the reply code, if positive mean connection success
            if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {
                // login using username & password
                boolean status = mFTPClient.login(username, password);
           /*
               * Set File Transfer Mode
               * To avoid corruption issue you must specified a correct
               * transfer mode, such as ASCII_FILE_TYPE, BINARY_FILE_TYPE,
               * EBCDIC_FILE_TYPE .etc. Here, I use BINARY_FILE_TYPE for
               * transferring text, image, and compressed files.
            */
                mFTPClient.setFileType(FTP.BINARY_FILE_TYPE);
                mFTPClient.enterLocalPassiveMode();
     //    List<String>  files=     getListOfFile();
                return status;
            }
        } catch (Exception e) {
            Log.d(TAG, "Error: could not connect to host " + host);
            Log.e(TAG, "Error: could not connect to host " + host+"e."+e.toString());
        }
        return false;
    }


    public boolean ftpDisconnect() {
        try {
            mFTPClient.logout();
            mFTPClient.disconnect();
            return true;
        } catch (Exception e) {
            Log.d(TAG, "Error occurred while disconnecting from ftp server.");
        }
        return false;
    }


    private int getType(String fileName){
        int lastDot = fileName.lastIndexOf('.');
        String extension =   fileName.substring(lastDot+1);

int resId=R.drawable.ftp;
        if (extension.equals("mp3")||extension.equals("MP3")){

            resId=R.drawable.mpthree;
        }

        else if (extension.equals("png")||extension.equals("PNG")||extension.equals("jpg")||extension.equals("jpeg")){
          //  extension.setImageResource(R.drawable.pic);
            resId=R.drawable.pic;
        }



        return resId;

    }

    public boolean ftpUpload(String srcFilePath, String desFileName) {
        boolean status = false;
        try {
            FileInputStream srcFileStream = new FileInputStream(srcFilePath);
            // change working directory to the destination directory
            // if (ftpChangeDirectory(desDirectory)) {
            status = mFTPClient.storeFile(desFileName, srcFileStream);
            // }
            srcFileStream.close();
            Log.e("finitooo", "finish: " );

            return status;
        } catch (Exception e) {
            Log.e("error=",e.toString());
            e.printStackTrace();
            Log.d(TAG, "upload failed: " + e);
        }
        return status;
    }





  public List<FtpFile> getListOfFile(){

      FTPFile[] files = new FTPFile[0];
      try {
          files = mFTPClient.listFiles();
      } catch (IOException e) {
          e.printStackTrace();
      }

// iterates over the files and prints details for each
        DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
List<FtpFile> fileList=new ArrayList<>(files.length);



        for (FTPFile file : files) {

            String details = file.getName();
         //   Log.e("fi=",f)
         int resId=   getType(details);
            FtpFile ftpFile=new FtpFile();
            ftpFile.setName(details);
            ftpFile.setImageresource(resId);
            fileList.add(ftpFile);
            if (file.isDirectory()) {
                details = "[" + details + "]";
            }
            details += "\t\t" + file.getSize();
            details += "\t\t" + dateFormater.format(file.getTimestamp().getTime());
            System.out.println(details);
        }

       // downloadFile(fileList.get(0));
        return fileList;
    }




    public boolean downloadFile(String remoteFile) {
        OutputStream outputStream = null;
        boolean success = false;
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), remoteFile);

        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(
                    file));
            success = mFTPClient.retrieveFile(remoteFile, outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }




        return success;
    }




     public boolean deleteFile(String remoteFile) {

        boolean success = false;


        try {

            success = mFTPClient.deleteFile(remoteFile);
        } catch (IOException e) {
            e.printStackTrace();
        }




        return success;
    }








}
