package com.controller;


import com.bean.Product;
import com.services.ProductCaringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.core.ControllerEntityLinks;
import org.springframework.hateoas.server.core.ControllerEntityLinksFactoryBean;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    Product product;
    @Autowired
    ProductCaringService productCaringService;

    private final Logger log = LoggerFactory.getLogger(ProductController.class);
    @GetMapping("/product")
    public List<Product> getList(){

        log.info("Product getList service invoked");

        return productCaringService.getProductList();

    }

    @GetMapping("/product/{name}")
    public Product getProduct(@PathVariable String name){

        log.info("Prodcut getProduct service invoked");


        return productCaringService.getProduct(name);

    }

    @GetMapping("/product/download")
    public ResponseEntity download(){

        log.info("downloading file");
        Path path = Paths.get("/Users/gauravchauhan/KafkaCasandra/spring-boot-examples/test.csv");
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String contentType = "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);


    }

    @PostMapping("/product/add")
    public Product addProduct(@RequestBody Product product){
        log.info("add product service invoked");

        productCaringService.add(product);

        //product = WebMvcLinkBuilder.methodOn(ProductController.class).getProduct(product.getName());
        //Link link = WebMvcLinkBuilder.linkTo(ProductController.class).slash("/product").withRel("all-product");

        //Product newPr = new Product();
        //product.add(link);

        /*products = products.stream().map((product1)->{
            product1.add(link);
            return product1;
        }).collect(Collectors.toList());*/
        //product.add(link);

        try {
            Method method = ProductController.class.getMethod("getList");
            Link link = WebMvcLinkBuilder.linkTo(method).withSelfRel();
            product.add(link);
        }catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }
}

