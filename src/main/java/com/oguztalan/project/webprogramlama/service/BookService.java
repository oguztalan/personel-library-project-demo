package com.oguztalan.project.webprogramlama.service;


import com.oguztalan.project.webprogramlama.entity.BookEntity;
import com.oguztalan.project.webprogramlama.exception.RecordNotFoundException;
import com.oguztalan.project.webprogramlama.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    //Tüm kitap listeleme fonk.
    public List<BookEntity> listAllBooks(){
        List<BookEntity> result = bookRepository.findAll();

        if (result.size() > 0 )
            return  result;
        else
            return new ArrayList<BookEntity>();

    }

    //Id ile kitap sorgulama
    public BookEntity getBookById(Long id) throws RecordNotFoundException {
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isPresent())
            return book.get();
        else
            throw new RecordNotFoundException("Verilen id ile kayıt bulunamadı!");
    }

    //Yeni kitap kaydı methodu
    public BookEntity createOrUpdateBook(BookEntity entity){

        if (entity.getId() == null){
            entity = bookRepository.save(entity);
            return entity;
        }
        else {
            Optional<BookEntity> book = bookRepository.findById(entity.getId());

            if (book.isPresent()){
                BookEntity newEntity = book.get();
                newEntity.setAuthor(entity.getAuthor());
                newEntity.setBookName(entity.getBookName());
                newEntity.setReleaseYear(entity.getReleaseYear());

                newEntity = bookRepository.save(newEntity);

                return newEntity;
            }
            else {
                entity = bookRepository.save(entity);

                return entity;
            }
        }
    }

    // Id ile kitap silme fonk
    public void deleteBookById(Long id)throws RecordNotFoundException{

        Optional<BookEntity> book = bookRepository.findById(id);

        if (book.isPresent()){
            bookRepository.deleteById(id);
        }else {
            throw new RecordNotFoundException("Verilen id ile bir kayıt bulunamadı!");
        }
    }

}
