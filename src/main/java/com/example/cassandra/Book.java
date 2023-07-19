package com.example.cassandra;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@AllArgsConstructor
@Table
public class Book {
    @PrimaryKeyColumn(name = "author", type = PrimaryKeyType.PARTITIONED)
    private String author;
    @PrimaryKeyColumn(name = "title", type = PrimaryKeyType.CLUSTERED)
    private String title;
    private int publishingYear;

}
