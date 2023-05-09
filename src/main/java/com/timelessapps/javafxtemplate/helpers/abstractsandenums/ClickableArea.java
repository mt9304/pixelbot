/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums;

import java.util.Random;

/**
 *
 * @author Max
 */
public class ClickableArea
{
    private int xStart1;
    private int yStart1;
    private int xEnd1;
    private int yEnd1;
    
    private int xStart2;
    private int yStart2;
    private int xEnd2;
    private int yEnd2;

    private int xStart3;
    private int yStart3;
    private int xEnd3;
    private int yEnd3;
    
    public ClickableArea(int xStart1, int yStart1, int xEnd1, int yEnd1, int xStart2, int yStart2, int xEnd2, int yEnd2, int xStart3, int yStart3, int xEnd3, int yEnd3) {
        this.xStart1 = xStart1;
        this.yStart1 = yStart1;
        this.xEnd1 = xEnd1;
        this.yEnd1 = yEnd1;
    
        this.xStart2 = xStart2;
        this.yStart2 = yStart2;
        this.xEnd2 = xEnd2;
        this.yEnd2 = yEnd2;

        this.xStart3 = xStart3;
        this.yStart3 = yStart3;
        this.xEnd3 = xEnd3;
        this.yEnd3 = yEnd3;
    }
    
    public int[] GetRandomXY() {
        Random random = new Random();
        int[] result = new int[2];
        
        double r = random.nextDouble();
        if (r < 0.73) {
            result[0] = generateRandomNumberBetween(xStart1, xEnd1);
            result[1] = generateRandomNumberBetween(yStart1, yEnd1);
        } else if (r < 0.91) {
            result[0] = generateRandomNumberBetween(xStart2, xEnd2);
            result[1] = generateRandomNumberBetween(yStart2, yEnd2);
        } else {
            result[0] = generateRandomNumberBetween(xStart3, xEnd3);
            result[1] = generateRandomNumberBetween(yStart3, yEnd3);
        }
        return result;
    }
    
    private int generateRandomNumberBetween(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt((max - min) + 1) + min;
        return randomNumber;
    }
}
