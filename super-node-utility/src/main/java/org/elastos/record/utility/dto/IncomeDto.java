package org.elastos.record.utility.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: YangChuanTong
 * @Date: 2019/6/21 10:35
 * @Description:
 */

@Setter
@Getter
public class IncomeDto implements Serializable {

    private BigDecimal incomeValue;//总收益
    private BigDecimal managementValue;//维护费用
    private BigDecimal teamValue;//团队费用
    private BigDecimal communityValue;//社区费用
}
