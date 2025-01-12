package com.spbstu.graph_builder.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CalculationRequest {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("function_type")
    private String functionType;
    @JsonProperty("x_coordinate")
    private Double xCoordinate;
}
