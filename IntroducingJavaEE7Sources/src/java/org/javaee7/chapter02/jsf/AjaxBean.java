/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee7.chapter02.jsf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

/**
 *
 * @author Juneau
 */
@ManagedBean(name = "ajaxBean")
@RequestScoped
public class AjaxBean implements Serializable {

    private Part upFile = null;
    private String value1;
    private String value2;
    private String status;

    /**
     * Creates a new instance of AjaxBean
     */
    public AjaxBean() {
    }

    public void sendMessage() {
        System.out.println("Message Sent Successfully");
    }

    public void uploadFile() {
        try(InputStream is = upFile.getInputStream()) {
           
            int i = is.available();
            byte[] b = new byte[i];
            is.read(b);
            String fileName = getFileName(upFile);
            System.out.println(upFile);
            FileOutputStream os = new FileOutputStream("/Java_Dev/" + fileName);
            os.write(b);
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(AjaxBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private String getFileName(Part part) {
    String partHeader = part.getHeader("content-disposition");
   
    for (String cd : part.getHeader("content-disposition").split(";")) {
      if (cd.trim().startsWith("filename")) {
        return cd.substring(cd.indexOf('=') + 1).trim()
            .replace("\"", "");
      }
    }
    return null;

  }
    
  
    /**
     * @return the file
     */
    public Part getUpFile() {
        return upFile;
    }

    /**
     * @param file the file to set
     */
    public void setUpFile(Part file) {
        this.upFile = file;
    }

    /**
     * @return the value1
     */
    public String getValue1() {
        return value1;
    }

    /**
     * @param value1 the value1 to set
     */
    public void setValue1(String value1) {
        this.value1 = value1;
    }

    /**
     * @return the value2
     */
    public String getValue2() {
        return value2;
    }

    /**
     * @param value2 the value2 to set
     */
    public void setValue2(String value2) {
        this.value2 = value2;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        status = "Value 1: " + value1 + " and Value2: " + value2;
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
