package com.main;

import business.Controller;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        long beginTime = 0, endTime = 0;
        beginTime = System.currentTimeMillis();
        Controller controller = new Controller();
        try {
            controller.controller();
        } catch (Exception e) {
            e.printStackTrace();
        }

        endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + ((double) (endTime - beginTime)) / 1000);
    }
}
