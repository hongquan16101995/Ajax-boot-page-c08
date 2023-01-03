package com.example.ajax_boot_c08.controller;

import com.example.ajax_boot_c08.model.Customer;
import com.example.ajax_boot_c08.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/customers")
@PropertySource("classpath:application.properties")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Value("${upload.path}")
    private String link;

    @Value("${display.path}")
    private String displayLink;

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Customer>> findAll(@PageableDefault(size = 2) Pageable pageable) {
        return new ResponseEntity<>(customerService.findAllPage(pageable), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> findAll(@RequestParam("search") String search) {
        return new ResponseEntity<>(customerService.findAllByName(search), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@RequestBody Customer customer,
                                           @PathVariable Long id) {
        Customer customerUpdated = customerService.findById(id);
        if (customerUpdated != null) {
            return new ResponseEntity<>(customerService.save(customer), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        customerService.delete(id);
        return new ResponseEntity<>("Delete done!", HttpStatus.OK);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<Customer> createUpload(@RequestPart(value = "file", required = false) MultipartFile file,
                                                 @RequestPart("customer") Customer customer) {
        if (file != null) {
            String fileName = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(link + fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            customer.setAvatar(displayLink + fileName);
        } else {
            customer.setAvatar(displayLink + "avatar.jpg");
        }
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.CREATED);
    }

    @PostMapping(value = "/upload1")
    public ResponseEntity<?> createUpload1(@RequestPart("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
