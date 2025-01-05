package com.EcomProductService.service;

import com.EcomProductService.Repository.CategoryRepository;
import com.EcomProductService.Repository.OrderRepository;
import com.EcomProductService.Repository.PriceRepository;
import com.EcomProductService.Repository.ProductRepository;
import com.EcomProductService.demo.Author;
import com.EcomProductService.demo.Book;
import com.EcomProductService.demo.AuthorRepository;
import com.EcomProductService.model.Category;
import com.EcomProductService.model.Order;
import com.EcomProductService.model.Price;
import com.EcomProductService.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitServiceImpl implements InitService{
    private ProductRepository productRepository;
    private PriceRepository priceRepository;
    private OrderRepository orderRepository;
    private CategoryRepository categoryRepository;

    private AuthorRepository authorRepository;


    public InitServiceImpl(ProductRepository productRepository, PriceRepository priceRepository, OrderRepository orderRepository, CategoryRepository categoryRepository,AuthorRepository authorRepository) {
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void initialise(){

        Price priceIphone = new Price();
        priceIphone.setAmount(100000);
        priceIphone.setDiscount(0);
        priceIphone.setCurrency("INR");

        Price priceMacBook = new Price();
        priceMacBook.setAmount(200000);
        priceMacBook.setDiscount(0);
        priceMacBook.setCurrency("INR");

        priceIphone = priceRepository.save(priceIphone);
        priceMacBook = priceRepository.save(priceMacBook);



        Category electronics = new Category();
        electronics.setCategoryName("Electronics");
        electronics = categoryRepository.save(electronics);


        Product Iphone = new Product();
        Iphone.setCategory(electronics);
        Iphone.setTitle("15Promax");
        Iphone.setImage("http://image.com");
        Iphone.setPrice(priceIphone);
        Iphone.setDescription("Best Iphone");
        Iphone = productRepository.save(Iphone);

        Product MacBook = new Product();
        MacBook.setCategory(electronics);
        MacBook.setTitle("MacBook");
        MacBook.setImage("http://image.com");
        MacBook.setPrice(priceMacBook);
        MacBook.setDescription("Best MacBook");
        MacBook = productRepository.save(MacBook);

        //Category electronics = new Category();
        //electronics.setCategoryName("Electronics");
        //electronics.setProducts(List.of(Iphone,MacBook));
        //Category savedCategory = categoryRepository.save(electronics);
        //electronics = categoryRepository.save(electronics);

        Order order = new Order();
        order.setProducts(List.of(Iphone,MacBook));
        order = orderRepository.save(order);

        Author author = new Author("Kumar",null);
        Book book1 = new Book("book1",author);
        Book book2 = new Book("book2",author);
        Book book3 = new Book("book3",author);
        author.setBooks(List.of(book1,book2,book3));
        authorRepository.save(author);

        Author savedAuthor = authorRepository.findById(1).get();
        List<Book> books = savedAuthor.getBooks();
        System.out.println(books);

    }
}
