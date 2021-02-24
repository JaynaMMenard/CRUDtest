package com.hypeform.hypeformTest.controller;

import com.hypeform.hypeformTest.datatransobj.HypeformDTO;
import org.apache.coyote.Response;
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
public class hypeformController {

 /*first endpoint-mircoservice */

    //Get Request
    @GetMapping(value = "/formlist")
    public ResponseEntity formList(){
        return new ResponseEntity(null, HttpStatus.OK);
    }
//Post Request
    @PostMapping(value ="/addform")
    public ResponseEntity addForm(@RequestBody HypeformDTO hypeform){
        return new ResponseEntity(null, HttpStatus.OK);
    }
//Put Request
    @PutMapping(value ="/{id}/update")
    public ResponseEntity edit (@PathVariable(value = "id") String id,@RequestBody HypeformDTO hypeform){
        return  new ResponseEntity(null, HttpStatus.OK);
    }
    // Delete Request
@DeleteMapping(value ="{id}/delete")
    public ResponseEntity delete (@PathVariable(value = "id") String id){
        return new ResponseEntity(null, HttpStatus.OK);
}
}
