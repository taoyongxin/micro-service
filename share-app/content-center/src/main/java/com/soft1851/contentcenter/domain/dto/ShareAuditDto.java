package com.soft1851.contentcenter.domain.dto;

import com.soft1851.contentcenter.domain.enums.AuditStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ShareAuditDto
 * @Description 分享审核数据传输对象
 * @date 2020-10-08 9:27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareAuditDto {
    /**
     * 审核状态
     */
    private AuditStatusEnum auditStatusEnum;

    /**
     * 原因
     */
    private String reason;
}
