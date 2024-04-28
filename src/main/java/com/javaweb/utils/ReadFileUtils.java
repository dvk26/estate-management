package com.javaweb.utils;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;


@Component
public class ReadFileUtils extends HttpServlet {

    public void doGet1(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imageUrl = request.getRequestURI();
        int repIndex = imageUrl.indexOf("/repository");
        String relativeImagePath = null;
        if(repIndex != -1) {
            repIndex += "/repository".length();
            relativeImagePath = imageUrl.substring(repIndex);
        }
        ServletOutputStream outStream;
        outStream = response.getOutputStream();
        FileInputStream fin = new FileInputStream("C://home/office" + relativeImagePath);
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(outStream);
        int ch =0;

        while((ch=bin.read())!=-1)
            bout.write(ch);
        bin.close();
        fin.close();
        bout.close();
        outStream.close();
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String imageUrl=request.getRequestURI();
        int repositoryIndex=imageUrl.indexOf("/repository");
        String imagePath="";
        if(repositoryIndex!=-1){
            repositoryIndex+="/repository".length();
            imagePath=imageUrl.substring(repositoryIndex);
        }
        ServletOutputStream outputStream;
        outputStream=response.getOutputStream();

        FileInputStream fin=new FileInputStream("C://home/office"+imagePath);
        BufferedInputStream bin=new BufferedInputStream(fin);
        BufferedOutputStream bout=new BufferedOutputStream(outputStream);

        byte data[]=new byte[256];
        int readBytes;
        while((readBytes= bin.read(data))!=-1){
            bout.write(data);
        }
        bin.close();
        fin.close();
        bout.close();
        outputStream.close();
    }



}
