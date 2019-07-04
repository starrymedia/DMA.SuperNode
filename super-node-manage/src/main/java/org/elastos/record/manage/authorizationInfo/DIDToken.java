package org.elastos.record.manage.authorizationInfo;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.elastos.record.utility.util.TransApi;


import java.io.Serializable;

@Getter
@Setter
public class DIDToken extends UsernamePasswordToken implements Serializable {
    private Long chainId;
    private TransApi.Language lang;
}
