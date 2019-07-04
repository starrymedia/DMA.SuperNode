
package org.elastos.record.utility.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;
import java.util.Date;

/**

  * 
  * gen by hn.nie 2019-05-22
  *
  */

@Table(name="sys_logger")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysLogger extends CrudEntity implements Serializable {


	private Integer timeconsuming ;
	private String clientip ;
    /**
     创建人
    */
	private Long createUser ;
	private String httpstatuscode ;
	private String method ;
	private String paramdata ;
	private String returndata ;
	private String sessionid ;
	private String type ;
    /**
     修改人
    */
	private Long updateUser ;
	private String uri ;
	private Date createtime ;
	private Date returntime ;
	private Date time ;
	private Date updatetime ;


}
