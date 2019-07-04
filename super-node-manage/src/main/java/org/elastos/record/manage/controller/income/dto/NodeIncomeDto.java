package org.elastos.record.manage.controller.income.dto;

import lombok.Getter;
import org.elastos.record.utility.entity.NodeIncome;
import org.elastos.record.utility.util.DecimalFormatUtil;

import java.io.Serializable;


@Getter
public class NodeIncomeDto extends NodeIncome implements Serializable {

    /**
     社区总收益金额
     */
    private String communityValueString ;
    /**
     本轮节点总收益金额
     */
    private String incomeValueString ;

    /**
     管理维护金额
     */
    private String managementValueString ;
    /**
     团队收益金额
     */
    private String teamValueString ;


    public String getCommunityValueString() {
        return DecimalFormatUtil.format(super.getCommunityValue(), 8);
    }

    public String getIncomeValueString() {
        return DecimalFormatUtil.format(super.getIncomeValue(), 8);
    }

    public String getManagementValueString() {
        return DecimalFormatUtil.format(super.getManagementValue(), 8);
    }

    public String getTeamValueString() {
        return DecimalFormatUtil.format(super.getTeamValue(), 8);
    }
}
