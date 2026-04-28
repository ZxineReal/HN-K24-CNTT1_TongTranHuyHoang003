package com.example.test1.controller;

import com.example.test1.model.Book;
import com.example.test1.service.IBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class BookController {
    private final IBookService bookService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping("/add")
    public String add(
            @Valid @ModelAttribute("book") Book book,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "book-form";
        }
        bookService.save(book);
        redirectAttributes.addFlashAttribute("message", "Thêm sách thành công");
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "book-form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("book") Book book,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "book-form";
        }
        book.setId(id);
        bookService.save(book);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Xóa sách thành công");
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", "Không thể xóa sách có id " + id);
        }
        return "redirect:/";
    }
}
