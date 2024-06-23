package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(BootstrapData.class);

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Publisher addisonWesley = new Publisher();
        addisonWesley.setPublisherName("Addison-Wesley Professional");
        addisonWesley.setCity("America");
        addisonWesley.setState("CA");
        addisonWesley.setZip("95472");
        addisonWesley.setAddress("1005 Gravenstein Highway North Sebastopol, CA 95472");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);
        Publisher addisonWesleySaved = publisherRepository.save(addisonWesley);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("3254891");

        Publisher wrox = new Publisher();
        wrox.setPublisherName("Wrox");
        wrox.setCity("England");
        wrox.setState("Birmingham");
        wrox.setZip("?????");
        wrox.setAddress("????????????????????");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);
        Publisher wroxSaved = publisherRepository.save(wrox);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);

        dddSaved.setPublisher(addisonWesleySaved);
        noEJBSaved.setPublisher(wroxSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

        System.out.println("In Bootstrap");
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());
    }
}
