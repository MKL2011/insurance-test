package com.tinubu.test.adapters.api;


import java.time.LocalDate;

public record PolicyResponse (Integer id, String name, LocalDate startCoverDate, LocalDate endCoverDate,
         LocalDate creationDate, LocalDate updateDate, String status){
}
