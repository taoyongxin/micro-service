package com.soft1851.contentcenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ConversionDto
 * @Description 兑换页面返回参数
 * @date 2020-10-14 9:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConversionDto {

    /**
     * id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 否原创 0：否 1：是
     */
    private Boolean isOriginal;

    /**
     * 资源作者
     */
    private String author;

    /**
     * 资源封面图URL
     */
    private String cover;

    /**
     * 资源摘要
     */
    private String summary;

    /**
     * 下载需要的积分
     */
    private Integer price;

    /**
     * 下载地址
     */
    private String downloadUrl;


}
