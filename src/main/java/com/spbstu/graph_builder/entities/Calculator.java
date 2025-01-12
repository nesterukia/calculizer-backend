package com.spbstu.graph_builder.entities;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Calculator {

    private static final int DEFAULT_PRECISION = 3;
    public static Point calculate(FunctionType functionType, double xCoordinate){
        double yCoordinate = 0.0;
        switch (functionType){
            case SINE -> yCoordinate = Math.sin(xCoordinate);
            case COSINE -> yCoordinate = Math.cos(xCoordinate);
            case LINE -> yCoordinate = xCoordinate;
            case HEART -> yCoordinate = Math.cbrt(xCoordinate * xCoordinate) + 0.9 * Math.sqrt(3.3 - xCoordinate * xCoordinate) * Math.sin(18.2  * Math.PI * xCoordinate);
        }
        return new Point(xCoordinate, yCoordinate);
    }

    public static double roundWithPrecision(double number, int precision){
        double modifier = Math.pow(10, precision);
        double result = Math.round(number * modifier) / (modifier);
        return result;
    }

    public static double roundWithPrecision(double number){
        return roundWithPrecision(number, DEFAULT_PRECISION);
    }
}
