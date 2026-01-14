package com.spring.project.ContactDetails.controller;

import com.spring.project.ContactDetails.entities.ContactEntity;
import com.spring.project.ContactDetails.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.spring.project.ContactDetails.constants.constant.PHOTO_DIRECTORY;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactEntity>createContact(@RequestBody ContactEntity contactEntity)
    {
        return ResponseEntity.ok(contactService.createContact(contactEntity));
    }

    @GetMapping
    public ResponseEntity<Page<ContactEntity>> getContacts(@RequestParam(value = "page",defaultValue = "0")int page,
                                           @RequestParam(value = "size",defaultValue = "10") int size)
    {
        return ResponseEntity.ok(contactService.getContacts(page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactEntity>getContact(@PathVariable(value = "id")String id)
    {
        return ResponseEntity.ok(contactService.getContact(id));
    }

    @PutMapping("/photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("id")String id, @RequestParam("file") MultipartFile file)
    {
        return ResponseEntity.ok(contactService.uploadPhoto(id,file));
    }

    @GetMapping(value = "/image/{filename}",produces = {IMAGE_PNG_VALUE,IMAGE_JPEG_VALUE})
    public byte[] getPhoto(@PathVariable("filename")String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY+filename));
    }
}
