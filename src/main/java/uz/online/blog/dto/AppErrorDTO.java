package uz.online.blog.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AppErrorDTO {
    private String errorMessage;
    private Integer errorCode;
    private String errorPath;
    private LocalDateTime timestamp;
}
