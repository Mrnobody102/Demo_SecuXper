package com.example.secuxper.apilog;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "apilog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "url")
    private String url;

    @Column(name = "ip")
    private String ip;

    public ApiLog(String timestamp, String url, String ip) {
        this.timestamp = timestamp;
        this.url = url;
        this.ip = ip;
    }
}

