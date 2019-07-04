
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

@Table(name="node_income_send")
@Data
@EqualsAndHashCode(callSuper = true)
public class NodeIncomeSend extends CrudEntity  {

	private static final long serialVersionUID = 123456789L;



	/**
	 发放的结束块高
	*/
	private Long blockHeightEnd ;
	/**
	 发放的起始块高
	*/
	private Long blockHeightStart ;

	/**
	 接收地址
	*/
	private String receiveAddress ;
	/**
	 发放地址
	*/
	private String sendAddress ;
	/**
	 发送金额
	*/
	private BigDecimal sendValue ;
	/**
	 交易txid
	*/
	private String txid ;



}
