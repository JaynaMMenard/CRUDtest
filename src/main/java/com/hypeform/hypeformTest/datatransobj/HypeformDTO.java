package com.hypeform.hypeformTest.datatransobj;

import lombok.Data;

@Data
//@Data generates getters for all fields
public class HypeformDTO {


    //model
    private String id;
    private String formTitle;
    private String author;
    private String description;
    private String formContent;

}
