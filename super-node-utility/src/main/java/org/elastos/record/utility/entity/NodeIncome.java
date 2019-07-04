
package org.elastos.record.utility.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.Table;

import java.math.BigDecimal;
import java.util.Date;

/**

  * 
  * gen by hn.nie 2019-06-20
  *
  */

@Table(name="node_income")
@Data
@EqualsAndHashCode(callSuper = true)
public class NodeIncome extends CrudEntity  {

	private static final long serialVersionUID = 123456789L;



	/**
	 当前块高
	*/
	private Long blockHeight ;
	/**
	 社区总收益金额
	*/
	private BigDecimal communityValue ;
	/**
	 本轮节点总收益金额
	*/
	private BigDecimal incomeValue ;

	/**
	 管理维护金额
	*/
	private BigDecimal managementValue ;
	/**
	 团队收益金额
	*/
	private BigDecimal teamValue ;



}
