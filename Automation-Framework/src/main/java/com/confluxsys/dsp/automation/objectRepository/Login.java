package com.confluxsys.dsp.automation.objectRepository;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public interface Login{

    //string of xpath locators
    String userName="//*[@placeholder=\"User Name\"]";
    String password= "//*[@placeholder=\"Password\"]";
    String loginButton="//*[@value=\"Log in\"]";
}