package org.elastos.record.manage.controller.income.dto;

import lombok.Getter;
import lombok.Setter;
import org.elastos.record.manage.constant.Constant;
import org.elastos.record.utility.entity.NodeIncomeSend;
import org.elastos.record.utility.util.DateUtils;
import org.elastos.record.utility.util.DecimalFormatUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hb.nie
 * @versioin 1.0.0
 * @createTime 2019/6/24 9:56
 */

@Setter
@Getter
public class NodeIncomeSendDto extends NodeIncomeSend implements Serializable {

    private String searchTimeRange;
    private Date searchTimeStartDate;
    private Date searchTimeEndDate;

    public void setSearchTimeRange(String searchTimeRange) {
        this.searchTimeStartDate = searchTimeRange == null ? null : DateUtils.parseDateString(searchTimeRange.split(Constant.SearchTime.separator)[0]);
        this.searchTimeEndDate = searchTimeRange == null ? null : DateUtils.parseDateString(searchTimeRange.split(Constant.SearchTime.separator)[1]);
        this.searchTimeRange = searchTimeRange;
    }

    /**
     * 发送地址
     */
    private String sendValueString;

    public String getSendValueString() {
        return DecimalFormatUtil.format(getSendValue(), 8);
    }
}
