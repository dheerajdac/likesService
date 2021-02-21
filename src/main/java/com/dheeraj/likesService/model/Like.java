package com.dheeraj.likesService.model;

import java.util.Date;

import lombok.Data;

@Data
public class Like {
    
    String parentId;

    String userId;

    String type;

    boolean liked = true;

    Date createdOn;

    Date lastModifieDate;
}
