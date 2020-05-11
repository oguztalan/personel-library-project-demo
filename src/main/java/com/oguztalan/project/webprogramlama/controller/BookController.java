package com.oguztalan.project.webprogramlama.controller;


import com.oguztalan.project.webprogramlama.entity.BookEntity;
import com.oguztalan.project.webprogramlama.exception.RecordNotFoundException;
import com.oguztalan.project.webprogramlama.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    BookService bookService;

    // Kitapları listeleyen method
    @RequestMapping
    public String getAllBooks(Model model){
        List<BookEntity> list = bookService.listAllBooks();
        model.addAttribute("allBooks", list);
        return "list-books";
    }

    //yeni kitap kaydı
    @RequestMapping("/new")
    public String showNewOwnerPage(Model model) {
        BookEntity book = new BookEntity();
        model.addAttribute("createBook",book);
        return "new-books";
    }


    //kitap kayıt
    @RequestMapping(value = "/save", method = RequestMethod.POST)
        public String saveBook(@ModelAttribute("book") BookEntity book) {
        bookService.createOrUpdateBook(book);
        return "redirect:/";
    }

    //kitap editleme
    @RequestMapping(path = {"/edit/{id}"})
    public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) throws RecordNotFoundException {
        ModelAndView mav = new ModelAndView("edit-books");
        BookEntity book = bookService.getBookById(id);
        mav.addObject("book", book);

        return mav;
    }

    //kitap kaydını silen fonksiyon
    @RequestMapping(path = "/delete/{id}")
    public String deleteBookById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException{

        bookService.deleteBookById(id);
        return "redirect:/";
    }


    @RequestMapping(path = "/createBook", method = RequestMethod.POST)
    public String createOrUpdateEmployee(BookEntity book)
    {
        bookService.createOrUpdateBook(book);
        return "redirect:/";
    }




}
