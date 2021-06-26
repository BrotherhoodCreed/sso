package com.promotion.product.dao.mysql2;

import com.promotion.product.dao.dataobject.ShopDo;
import com.promotion.product.dao.dataobject.StoreAccountInfoDo;
import org.apache.ibatis.annotations.Param;
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
            "\tAMCD AS 'amcd', \n" +
            "\taccountnumber, \n" +
            "\tuid, \n" +
            "\tdepositbank \n" +
            "FROM\n" +
            "\tstoreinfo where AM is not null")
    List<ShopDo> selectShop();


    @Select("Select AMCD from storeinfo where STCD=#{shopAreaId}")
    String selectShopAreaId(@Param("shopAreaId") String shopAreaId);


    List<ShopDo> selectShopList(@Param("list") List<String> stcd);

    @Select("select sai.STCD as stcd,sai.STNM as stnm,sai.type,sai.accountnumber,sai.uid,sai.depositbank,sai.isdefault\n" +
            "from storeaccountinfo sai\n" +
            "left join storeinfo si\n" +
            "on sai.stcd = si.stcd\n" +
            "where 1=1\n" +
            "order by ISNULL(si.am), \n" +
            "case when si.am=\"一区\" then \"1\" \n" +
            "when si.am=\"二区\" then \"2\"\n" +
            "when si.am=\"三区\" then \"3\"\n" +
            "when si.am=\"四区\" then \"4\"\n" +
            "when si.am=\"五区\" then \"5\"\n" +
            "when si.am=\"六区\" then \"6\"\n" +
            "else si.am end \n" +
            ",sai.stcd")
    List<StoreAccountInfoDo> selectStoreAccountinfo();


}
