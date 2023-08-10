package net.safethoughts.blog.exceptions;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Getter
public class ErrorDetails {

    @CreationTimestamp
    private Date timestamp;

    private String message ;


    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {

        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

}
