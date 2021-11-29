package com.example.template.controllers;


import com.example.template.dtos.CatDTO;
import com.example.template.models.Cat;
import com.example.template.services.CatService;
import com.example.template.util.Constants;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/cats")
public class CatController {
  private final CatService catService;

  @Autowired
  public CatController(CatService catService) {
        this.catService = catService;
  }

  @GetMapping
  @ApiOperation(value = "Get all cats",notes = "Get all cats' information",response = Cat.class)
  public Page<Cat> getAllCats(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                              @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size){
      return this.catService.getAllCats(page, size);
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "Cat id")
  public Cat getCat(@ApiParam(value = "ID value for a cat you are looking for",required = true)
                       @PathVariable UUID id){
      return this.catService.getCat(id);
  }

  @PostMapping
  public Cat addCat(@RequestBody CatDTO cat){
   return catService.addCat(cat);
  }
}
