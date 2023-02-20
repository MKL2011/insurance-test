package com.tinubu.test.adapters.api;

import java.time.LocalDate;

public record PolicyRequest(String name, LocalDate startCoverDate, LocalDate endCoverDate, String status){
}
