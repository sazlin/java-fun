package com.seanazlin.textUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book{
    private int userId;
    private int id;
    private String title;
    private boolean completed;

    public String toCSVString(){
        return new StringBuilder()
                .append(userId).append(",")
                .append(id).append(",")
                .append(title).append(",")
                .append(completed).toString();
    }
}
