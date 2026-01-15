package com.spring.project.ContactDetails.services;


import com.spring.project.ContactDetails.entities.ContactEntity;
import com.spring.project.ContactDetails.entities.UserEntity;
import com.spring.project.ContactDetails.exception.ResourceNotFoundException;
import com.spring.project.ContactDetails.repository.ContactRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;



@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {

    private final ContactRepo contactRepository;

    @Autowired
    private S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.region}")
    private String region;

    private final SecurityService securityService;

    public Page<ContactEntity> getContacts(int page, int size) {
        UserEntity user = (UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return contactRepository.findByUser(user,PageRequest.of(page,size, Sort.by("name")));
    }

    public ContactEntity getContact(String id) {
        UserEntity user = (UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return (ContactEntity) contactRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));
    }



    public ContactEntity createContact(ContactEntity contactEntity) {
        UserEntity user = (UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        contactEntity.setUser(user);
        return contactRepository.save(contactEntity);
    }

    public String uploadPhoto(String id, MultipartFile file) {
        UserEntity user = (UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        log.info("Saving picture for user ID: {}", id);
        ContactEntity contact = (ContactEntity) contactRepository
                .findByIdAndUser(id,user)
                .orElseThrow(()-> new RuntimeException("Not your contact"));


        String photoUrl = photoFunction.apply(id, file);
        contact.setPhotoUrl(photoUrl);
        contactRepository.save(contact);
        return photoUrl;
    }

    private final Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1)).orElse(".png");

    private final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
        String filename = id + fileExtension.apply(image.getOriginalFilename());
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .contentType(image.getContentType())
                    .build();


            s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(image.getInputStream(),image.getSize()));

            return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + filename;
        }catch (Exception e) {
            e.printStackTrace();   // THIS shows AWS error
            throw new RuntimeException(e.getMessage());
        }
    };
}
