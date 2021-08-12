package com.renmoney_ha.payloads.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInfo {
    private String title;
    private String description;
    private String author;
    private LocalDate dueDate;
    private String docUrl;
}
