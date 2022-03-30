package ru.netology.domain;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductTest {

    private Product book1 = new Book(1, "Evgenyi Onegin", 550, "Alexander Pushkin");
    private Product book2 = new Book(2, "Mcyri", 850, "Mikhail Lermontov");
    private Product phone1 = new Smartphone(4, "Honor10", 27500, "Xiaomi");


    @Test
    void bookNameMatch() {
        boolean actual = book2.matches("Mcyri");
        assertTrue(actual);
    }

    @Test
    void bookNameDoesntMatch() {
        boolean actual = book1.matches("Viy");
        assertFalse(actual);
    }

    @Test
    void phoneNameMatch() {
        boolean actual = phone1.matches("Honor10");
        assertTrue(actual);
    }

}