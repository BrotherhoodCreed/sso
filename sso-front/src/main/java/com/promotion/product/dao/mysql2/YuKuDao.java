package com.promotion.product.dao.mysql2;

import com.promotion.product.dao.dataobject.ShopDo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface YuKuDao {
    @Select("SELECT\n" +
            "\tSTCD AS 'stcd',\n" +
            "\tSTNM AS 'stnm',\n" +
            "\tASNM AS 'asnm',\n" +
            "\tMNMNCCD AS 'mnmnccd',\n" +
            "\tSTTPCD AS 'sttpcd',\n" +
            "\tSTS AS 'sts',\n" +
            "\tBRNDCD AS 'brndcd',\n" +
            "\tZNCD AS 'zncd',\n" +
            "\tAM AS 'am',\n" +
            "\tSU AS 'su',\n" +
            "\tSEATNUM AS 'seatnum',\n" +
            "\tTABLESNUM AS 'tablesnum',\n" +
            "\tOPRTAR AS 'opetar',\n" +
            "\tSTMGR AS 'stmgr',\n" +
            "\tBISTD AS 'bistd',\n" +
            "\tBIEND AS 'biend',\n" +
            "\tPROVINCE AS 'province',\n" +
            "\tCITY AS 'city',\n" +
            "\tDSTRCT AS 'dstrct',\n" +
            "\tADRS AS 'adrs',\n" +
            "\tDEF5 AS 'def5',\n" +
            "\tSYSINITDATE AS 'sysinitdate',\n" +
            "\tiscomparable AS 'iscomparable', \n" +
            "\tAMCD AS 'amcd' \n" +
            "FROM\n" +
            "\tstoreinfo where AM is not null")
    List<ShopDo> selectShop();



}
