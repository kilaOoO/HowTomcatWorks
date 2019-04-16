package com.howtomcatwork.ex01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("index.html");
        System.out.println( "Hello World!" );
    }
}
