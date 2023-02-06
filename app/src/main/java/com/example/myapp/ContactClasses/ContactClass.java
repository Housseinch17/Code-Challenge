package com.example.myapp.ContactClasses;

public class ContactClass {
    String name, message,email;

    public ContactClass(String name, String message, String email) {
        this.name = name;
        this.message = message;
        this.email=email;
    }

    public ContactClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String email) {
        this.email = email;
    }
}