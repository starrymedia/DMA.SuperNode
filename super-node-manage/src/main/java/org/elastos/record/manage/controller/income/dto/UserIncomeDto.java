package org.elastos.record.manage.controller.income.dto;

import lombok.Getter;
import lombok.Setter;
import org.elastos.record.utility.entity.Share;
import org.elastos.record.utility.util.DecimalFormatUtil;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
public class UserIncomeDto extends Share implements Serializable {
    public UserIncomeDto() {
        super();
    }

    /**
     * 应分配ela数量
     */
    private BigDecimal balanceString;

    /**
     * 当前投票总数
     */
    private BigDecimal voteTotalValueString;
    /**
     * 投票数
     */
    private BigDecimal voteValueString;

    public String getBalanceString() {
        return DecimalFormatUtil.format(super.getBalance(), 8);
    }

    public String getVoteTotalValueString() {
        return DecimalFormatUtil.format(super.getVoteTotalValue(), 8);
    }

    public String getVoteValueString() {
        return DecimalFormatUtil.format(super.getVoteValue(), 8);
    }
}
