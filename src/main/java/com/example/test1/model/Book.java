package com.example.test1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title không được để trống")
    @Size(min = 5, max = 150, message = "Title phải có độ dài từ 5 đến 150 ký tự")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Author không được để trống")
    @Column(name = "author")
    private String author;

    @NotNull(message = "Date không được để trống")
    @PastOrPresent(message = "Date phải là ngày trong quá khứ hoặc hiện tại")
    @Column(name = "date")
    private LocalDate date;
}
