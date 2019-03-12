package com.order.web.modules.config.service;


import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    SAXReader reader = new SAXReader();

    public SAXReader getReader() {
        return reader;
    }

    public void setReader(SAXReader reader) {
        this.reader = reader;
    }
}
