
package org.elastos.record.utility.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;

/**

  * 
  * gen by hn.nie 2019-05-22
  *
  */

@Table(name="sys_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends CrudEntity implements Serializable {


	/**
	 *
	 */
	private Boolean flag ;
    /**
     创建者
    */
	private Long  createUser ;
	/**
	 * 邮箱
	 */
	private String email ;
	/**
	 * 用户名
	 */
	private String name ;
    /**
     密码
    */
	private String password ;
	/**
	 * 关联店铺
	 */
	private Long storeId ;
    /**
     修改人
    */
	private Long updateUser ;
	/**
	 * 登录时间
	 */
	private Date loginTime ;
	/**
	 * 角色[vo]
	 */
	private String role;
}
