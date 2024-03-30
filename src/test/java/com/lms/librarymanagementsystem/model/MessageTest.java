package com.lms.librarymanagementsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class MessageTest {

    private Message message;

    @BeforeEach
    public void setUp() {
        message = new Message();
    }

    @Test
    public void testGetId() {
        message.setId(1L);
        assertEquals(1, message.getId());
    }

    @Test
    public void testSetId() {
        message.setId(1L);
        assertEquals(1, message.getId());
    }

    @Test
    public void testGetContent() {
        message.setContent("Test content");
        assertEquals("Test content", message.getContent());
    }

    @Test
    public void testSetContent() {
        message.setContent("Test content");
        assertEquals("Test content", message.getContent());
    }

    @Test
    public void testGetCreatedAt() {
        Date date = new Date();
        message.setCreatedAt(date);
        assertEquals(date, message.getCreatedAt());
    }

    @Test
    public void testSetCreatedAt() {
        Date date = new Date();
        message.setCreatedAt(date);
        assertEquals(date, message.getCreatedAt());
    }

}