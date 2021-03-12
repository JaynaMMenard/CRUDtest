package com.hypeform.hypeformTest.controller;

import com.hypeform.hypeformTest.datatransobj.HypeformDTO;
import com.hypeform.hypeformTest.service.HypeformManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*Springboot will identify this
 * project as a RestAPI using this
 * class and import @RestController */

//RESTful API basics written

@RestController
//Use of endpoints
@RequestMapping(value = "/hypeform")
//Base URL path ^
public class HypeformController {


    // Responses now referenced directly with this instance + its desired HTTP response identified  in the hfImpl class
    @Autowired
    private HypeformManagementService service;



    /*first endpoint-mircoservice */

    //Get Request
    @GetMapping(value = "/formlist")
    public ResponseEntity formList(){

        return new ResponseEntity(service.formList(), HttpStatus.OK);
    }
//Post Request
    @PostMapping(value ="/addform")
    public ResponseEntity addForm(@RequestBody HypeformDTO hypeform){


        return new ResponseEntity(service.add(hypeform), HttpStatus.OK);
    }

//Put Request
    @PutMapping(value ="/{id}/update")
    public ResponseEntity edit (@PathVariable(value = "id") String id,@RequestBody HypeformDTO hypeform){
        return  new ResponseEntity(service.edit(id,hypeform), HttpStatus.OK);
    }

    // Delete Request
@DeleteMapping(value ="{id}/delete")
    public ResponseEntity delete (@PathVariable(value = "id") String id){
        return new ResponseEntity(null, HttpStatus.OK);
}
}
