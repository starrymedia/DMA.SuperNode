
package org.elastos.record.utility.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**

  * 
  * gen by hn.nie 2019-05-22
  *
  */

@Table(name="sys_menu")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends CrudEntity implements Serializable {


	private Integer display ;
	private Boolean flag ;
    /**
     菜单类型 1、菜单   2、权限
    */
	private Integer menuType ;
    /**
     创建者
    */
	private Long  createUser ;
	private Long creator ;
	private String icon ;
	private String name ;
	private Long parentId ;
	private String permission ;
    /**
     修改人
    */
	private Long updateUser ;
	private String url ;


}
