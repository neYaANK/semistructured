package com.neyaank;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            XmlToHtmlConverter.createHtmlAt("..\\takeout.xml", "..\\takeout.html");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
