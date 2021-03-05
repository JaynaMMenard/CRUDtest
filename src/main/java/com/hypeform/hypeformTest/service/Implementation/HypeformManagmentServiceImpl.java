package com.hypeform.hypeformTest.service.Implementation;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.hypeform.hypeformTest.datatransobj.HypeformDTO;
import com.hypeform.hypeformTest.service.HypeformManagmentService;
import com.hypeform.hypeformTest.util.FirebaseInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service

//Implementations of Interface methods
public class HypeformManagmentServiceImpl implements HypeformManagmentService {


    //Used to inject a particular instance in a method. Spring finds specific property
    @Autowired
    private FirebaseInitializer firebase;


    @Override
    public List<HypeformDTO> formList() {
        List <HypeformDTO> formList = new ArrayList<>();
        HypeformDTO hypeform;

        //QueryS contains zero or more DocumentSnapshot objects representing the results of a query
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
        try {
            //Attempts to retrieve/contains data read from a document in your Firestore database
            for (DocumentSnapshot doc:querySnapshotApiFuture.get().getDocuments()) {

            // Looking for docs with specific ids and adding them to the List formList
                hypeform = doc.toObject(HypeformDTO.class);
                hypeform.setId(doc.getId());
                formList.add(hypeform);
            }
            return formList;
        } catch (Exception e) {
            return null;
        }


    }

    @Override
    public Boolean add(HypeformDTO hypeform) {

//  Created seperate method to re-use for add, edit, delete requests
//        Map<String,Object> docData = new HashMap<>();
//
//        //getters predefined in @Data import in DTO class
//        docData.put("formTitle", hypeform.getFormTitle());
//        docData.put("author", hypeform.getAuthor());
//        docData.put("description", hypeform.getDescription());
//        docData.put("formContent", hypeform.getFormContent());


        Map<String, Object> docData = getDocData(hypeform);


        // Object used for adding documents, getting document references, and querying for documents, Firebase boiler code
        //CollectionReference hypeforms = firebase.getFirestore().collection("hypeformTest");

        // New Method created for minimal ctrl+c/v. Line 77
        //CollectionReference hypeforms = getCollection();
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document().create(docData);

        try {
            if(null != writeResultApiFuture.get()){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
        catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean edit(String id, HypeformDTO hypeform) {
        Map<String, Object> docData = getDocData(hypeform);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).set(docData);
        try {
            if(null != writeResultApiFuture.get()){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
        catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean delete(String id) {
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).delete();
        try {
            if(null != writeResultApiFuture.get()){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    private CollectionReference getCollection() {
        return firebase.getFirestore().collection("hypeformTest");
    }

    private Map<String, Object> getDocData(HypeformDTO hypeform) {
        Map<String,Object> docData = new HashMap<>();

        //getters predefined in @Data import in DTO class
        docData.put("formTitle", hypeform.getFormTitle());
        docData.put("author", hypeform.getAuthor());
        docData.put("description", hypeform.getDescription());
        docData.put("formContent", hypeform.getFormContent());
        return docData;
    }
}
