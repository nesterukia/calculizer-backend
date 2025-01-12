package com.spbstu.graph_builder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.spbstu.graph_builder.entities.Calculator.roundWithPrecision;

@Setter
@Getter
@NoArgsConstructor
public class Point {

    @JsonProperty("xCoordinate")
    private double xCoordinate;
    @JsonProperty("yCoordinate")
    private double yCoordinate;

    public Point(double xCoordinate, double yCoordinate){
        this.xCoordinate = roundWithPrecision(xCoordinate);
        this.yCoordinate = roundWithPrecision(yCoordinate);
    }
}
