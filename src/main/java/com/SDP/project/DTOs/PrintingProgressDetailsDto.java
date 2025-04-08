package com.SDP.project.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrintingProgressDetailsDto {
    private int taskId;
    private LocalDate submittedDate;
    private LocalDate deadline;
    private int expectedQuantity;
    private int remainingToPrintQuantity;

    @JsonProperty("isStarted")
    private boolean isStarted;

    @JsonProperty("isSentToInventory")
    private boolean isSentToInventory;
}
