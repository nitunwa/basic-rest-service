package com.infosys.librarySystem.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.infosys.librarySystem.models.Book;

@Repository
public class BookDao {
	static List<Book> bookList = new ArrayList(Arrays.asList(new Book(1l, "Fancy", "Fiction"),
			new Book(2l, "Nancy", "non-Fiction"), new Book(3l, "Math", "Fiction"), new Book(4l, "English", "other")));

	public List<Book> getBookList() {

		return bookList;
	}

	public Book findByBookId(Long id) {

		List<Book> bookList = getBookList();
		for (Book book : bookList) {
			if (book.getId() == id) {
				return book;
			}
		}
		return null;
	}

	public boolean isBookExit(Book book1) {

		List<Book> bookList = getBookList();
		for (Book book : bookList) {
			if (book.getBookName().equals(book1.getBookName())) {
				return true;
			}
		}
		return false;
	}

	public List<Book> saveBook(Book book) {
		List<Book> bookList = getBookList();
		book.setId(Long.valueOf(bookList.size() + 1));

		bookList.add(book);
		return bookList;
	}

	public Book update(Book book) {
		List<Book> bookList1 = getBookList();
		int index = 0;
		for (Book book1 : bookList1) {
			if (book1.getId() == book.getId()) {
              break;
			}
			index++;
		}
		bookList1.set(index, book);
		return book;
	}

}
