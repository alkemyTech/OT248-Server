package com.alkemy.ong.exception;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Data
public class MessageResponse {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private String message;
    private String uri;

    public MessageResponse(LocalDateTime date, Exception e, HttpServletRequest request) {
        this.date = date;
        this.message = e.getMessage();
        this.uri = request.getRequestURI();
    }

}
