package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    private Product book1 = new Book(1, "Evgenyi Onegin", 550, "Alexander Pushkin");
    private Product book2 = new Book(2, "Mcyri", 850, "Mikhail Lermontov");
    private Product book3 = new Book(3, "Viy", 450, "Nikolai Gogol");
    private Product phone1 = new Smartphone(4, "Honor10", 27500, "Xiaomi");
    private Product phone2 = new Smartphone(5, "SamsungA6", 22600, "Samsung");
    private Product phone3 = new Smartphone(6, "Samsung Galaxy", 12000, "Samsung");
    private Product phone4 = new Smartphone(7, "Nokia3310", 5500, "Nokia");
    private Product clothes = new Product(8, "clothes", 330);

    @BeforeEach
    void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);
        manager.add(phone4);
        manager.add(clothes);
    }

    @Test
    void searchIfAuthorNotFound() {
        Product[] actual = manager.searchBy("Zybkov");
        Product[] expected = new Product[0];

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchIfAuthorFound() {
        Product[] actual = manager.searchBy("Nikolai Gogol");
        Product[] expected = new Product[]{book3};

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchIfBookNameFound() {
        Product[] actual = manager.searchBy("Mcyri");
        Product[] expected = new Product[]{book2};

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchIfVendorNotFound() {
        Product[] actual = manager.searchBy("Apple");
        Product[] expected = new Product[0];

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchIf3PhonesFound() {
        Product[] actual = manager.searchBy("Samsung Galaxy");
        Product[] expected = new Product[]{phone3};

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchIfPhoneNameFound() {
        Product[] actual = manager.searchBy("Nokia");
        Product[] expected = new Product[]{phone4};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindByString() {
        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("Not Found");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByID() {
        manager.removeById(4);
        Product[] expected = new Product[]{book1, book2, book3, phone2, phone3, phone4, clothes};
        Product[] actual = manager.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldDeleteNotExistingItem() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> repository.removeById(9));
    }

}