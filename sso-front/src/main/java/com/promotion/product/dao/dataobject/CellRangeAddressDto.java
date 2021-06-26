package com.promotion.product.dao.dataobject;

import lombok.Data;

@Data
public class CellRangeAddressDto {
    private Integer firstRow;
    private Integer lastRow;
    private Integer firstCol=8;
    private Integer lastCol=8;
}
