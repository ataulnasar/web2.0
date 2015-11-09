/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Zaid
 */

@ManagedBean(name="form")
@SessionScoped
public class Form {
    
    private String name;
    private String email;
    private String phoneNumber;
    private String Subject;
    private String message;
    private String successMessage;

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Form{" + "name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", Subject=" + Subject + ", message=" + message + '}';
    }
    
    public void clearData() {
        this.name = null;
        this.Subject = null;
        this.email = null;
        this.message = null;
        this.phoneNumber = null;
       
    }
    
    
}
