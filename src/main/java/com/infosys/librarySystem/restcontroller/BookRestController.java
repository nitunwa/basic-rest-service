package com.infosys.librarySystem.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.librarySystem.dao.BookDao;
import com.infosys.librarySystem.models.Book;

@RestController
@RequestMapping("/rest-service")
public class BookRestController {
	@Autowired
	BookDao dao;

	private static final Logger logger = LoggerFactory.getLogger(BookRestController.class);

	@GetMapping(value = "/getBookById/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long id) {
		Book book = dao.findByBookId(id);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	@GetMapping(value = "/addBook/{bookName}/{category}")
	public ResponseEntity<List<Book>> addBook(@PathVariable(value="bookName")String bookName,@PathVariable(value="category")String category) {
		System.out.println("BookRestController.addBook()");
		List<Book> bookList = null;
		Book book = new Book(null , bookName, category);
		if (dao.isBookExit(book)) {
			System.out.println("Unable to create. A Book with name {} already exist");
		} else {
			bookList = dao.saveBook(book);
		}

		return new ResponseEntity<List<Book>>(bookList, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/updateBook/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable(value="id") Long id){
		Book currentBook = dao.findByBookId(id);
		currentBook.setBookName("Art");
		currentBook.setCategory("art");
		Book eBook = dao.update(currentBook);
		return new ResponseEntity<Book>(eBook , HttpStatus.OK);
	}
	
	 @RequestMapping(value = "/book", method = RequestMethod.POST)
	 public ResponseEntity<Void> createBook(@RequestBody Book book) {
	        System.out.println("Creating User " + book.toString());
	        return new ResponseEntity<Void>(HttpStatus.CREATED);
	 }
}
