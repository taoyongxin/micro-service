package com.soft1851.contentcenter.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ResponseResult
 * @Description TODO
 * @date 2020-09-24 21:08
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseResult {
    private static final long serialVersionUID = -3948389268046368059L;

    private Integer code;

    private String msg;

    private Object data;
}
