package com.neyaank;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        DomParser dom = new DomParser();
        File in = new File("..\\ex6.xml");
        File out = new File("..\\ex6-out.xml");
        dom.createDom(in,out);

    }
}