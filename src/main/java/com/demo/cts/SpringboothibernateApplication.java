package com.demo.cts;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.cts.dao.BookRepository;
import com.demo.cts.domain.Book;

@SpringBootApplication
public class SpringboothibernateApplication implements CommandLineRunner{

   @Autowired
private BookRepository bookRepository;
	
	//@Autowired
	// private BookService bookService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringboothibernateApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		  Scanner scanner=new Scanner(System.in); 
		  int ch=0;
		  while(true)
		  {
			  System.out.println("1) ADD BOOK");
			  System.out.println("2 SEARCHING A SPECIFIC BOOK");
			  System.out.println("3) DISPLAY BOOKS");
			  System.out.println("4) DELETE BOOKS");
			  System.out.println("5) UPDATE BOOK");
			  System.out.println("6) EXIT");
			  System.out.println("Enter your choice ");
			  ch=scanner.nextInt();
			  switch(ch)
			  {
			  case 1 :        Book book=new Book();
		  		              System.out.println("Enter the tile");
		                      String title=scanner.next(); 
		                      System.out.println("Enter the author"); 
		                      String author=scanner.next(); 
		                      System.out.println("Enter the price"); 
		                      int price=scanner.nextInt();
		                      book.setTitle(title);
		                      book.setAuthor(author);
		                      book.setPrice(price); 
		                      bookRepository.save(book);
		                      System.out.println("Book added!!!!!!!!!!");
		                      break;
		 
			  case 2 :  	 System.out.println("Searching a specific book"); 
			                 System.out.println("Enter the book to be searched");
			                 int searchId=scanner.nextInt();
			                 Optional<Book>  bookSearched=bookRepository.findById(searchId); 
			                 if(bookSearched.isPresent()) {
		                     System.out.println(bookSearched.get());
		                     }
			                 else {
		                         System.out.println("Book not found!!!!!!");
		                          }
		                    
			                break;
			                
			  case 3 :    System.out.println("All the books");
	                      List<Book> bookList=bookRepository.findAll();
		                  for(Book book1 : bookList)
		                   {
		                	System.out.println(book1.getBookId()+" "+book1.getTitle()+" "+book1.getPrice());
		                   }
		                   break;
		                   
			  case 4 : 	  System.out.println("Demo of delete!!!!!");
			              System.out.println("Enter the book to be deleted");
			              int idToDelete=scanner.nextInt();
		 		          Optional<Book> bookSearchedForDelete=bookRepository.findById(idToDelete); 
		 		      
		 		          if(bookSearchedForDelete.isPresent()) {
		 		        	 		          
		                  Book booktoBeDeleted=bookSearchedForDelete.get(); // Get will return the object found
		                  bookRepository.delete(booktoBeDeleted); 
		                  } else {
		                      System.out.println("No book with the id entered"); 
		                      }
		                  break;
			  case 5  :    System.out.println("Demo for update");            
	  	                   System.out.println("Enter the id for the book to be updated");
	                       int idToUpdate=scanner.nextInt();
	    
	                      Optional<Book> bookSearchedForUpdate=bookRepository.findById(idToUpdate);
	                      if(bookSearchedForUpdate.isPresent())
	                         {
	                         Book booktoBeUpdated=bookSearchedForUpdate.get();
	                         System.out.println("Enter the new price");
	                         booktoBeUpdated.setPrice(scanner.nextFloat());
	                         bookRepository.save(booktoBeUpdated);
	                         System.out.println("Book update!!!!!");
	                         }
	                      else
	                       {
	      	                 System.out.println("Book not found!!!!!!");
	                       }
	                       break;
			  case 6 : System.exit(0);
			  
			  } // end of switch
	     }  // end of while
	} // end of main
}  // end of class
	



