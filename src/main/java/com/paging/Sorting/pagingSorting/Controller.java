package com.paging.Sorting.pagingSorting;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private Code code;


    @PostMapping("/save")
    public ResponseEntity<Codebook> saved(@RequestBody Codebook codebook){
    Codebook book =  code.save(codebook);
     return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Codebook>>getAll( ){

     List<Codebook> codes =  code.findAll();

        return new ResponseEntity<>(codes,HttpStatus.FOUND);
    }

    @GetMapping("/sort/{field}")
    public ResponseEntity<List<Codebook>>getAllsorted(@PathVariable String field ){

        List<Codebook> codes =  code.findAll(Sort.by(Sort.Direction.DESC, field));

        return new ResponseEntity<>(codes,HttpStatus.FOUND);
    }
    @GetMapping("/page/{offset}/{size}")
    public ResponseEntity<Page<Codebook>> page(@PathVariable int offset,@PathVariable int size){
        Page<Codebook> pageCodes = code.findAll(PageRequest.of(offset, size));
        return new ResponseEntity<>(pageCodes,HttpStatus.FOUND);
    }

     @GetMapping("/sortPage")
    public ResponseEntity<Page<Codebook>> pageSorted( int pageNo, int size, String field){
        Page<Codebook>pageCodes = code.findAll(PageRequest.of(pageNo,size).withSort(Sort.by(Sort.Direction.DESC,field)));
        return  new ResponseEntity<>(pageCodes,HttpStatus.FOUND);
     }





}
