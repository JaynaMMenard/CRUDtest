package com.hypeform.hypeformTest.service;

import com.hypeform.hypeformTest.datatransobj.HypeformDTO;

import java.util.List;


//Defined in the Controller class,defined here
public interface HypeformManagmentService {

    //Holds data objects from DTO class in in a list
List<HypeformDTO> formList();

// T or F. Must be T to allow controller Http Requests to work
Boolean add(HypeformDTO hypeform);

// Editing form. Search through unique ID
Boolean edit(String id, HypeformDTO hypeform);

// Deleting form from unique ID
Boolean delete(String id);



}
