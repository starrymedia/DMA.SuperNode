package org.elastos.record.manage.dto;

import lombok.Getter;
import lombok.Setter;
import org.elastos.record.utility.entity.CrudEntity;
import org.elastos.record.utility.util.TransApi;


import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SessionUser extends CrudEntity implements Serializable {
    private TransApi.Language lang  ;

    private String name ;
    private Date loginTime ;



}
