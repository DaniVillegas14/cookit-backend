package ar.edu.unq.cookitbackend.service;

import ar.edu.unq.cookitbackend.exception.CreateDocumentationException;

import java.io.IOException;

public interface IDocumentationService {
    String createImageDocumentation(String imageUrl) throws IOException, CreateDocumentationException;
}
