
package org.elastos.record.utility.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.Table;
import org.elastos.record.utility.util.DecimalFormatUtil;

import java.math.BigDecimal;

/**

  * 
  * gen by hn.nie 2019-06-20
  *
  */

@Table(name="share")
@Data
@EqualsAndHashCode(callSuper = true)
public class Share extends CrudEntity  {

	private static final long serialVersionUID = 123456789L;


	/**
	 是否发生分成（0：未发送，1：已发送）
	*/
	private Boolean isSend ;

	/**
	 投票地址
	*/
	private String address ;
	/**
	 应分配ela数量
	*/
	private BigDecimal balance ;
	/**
	 当前块高
	*/
	private Long blockHeight ;

	/**
	 当前投票总数
	*/
	private BigDecimal voteTotalValue ;
	/**
	 投票数
	*/
	private BigDecimal voteValue ;




}
