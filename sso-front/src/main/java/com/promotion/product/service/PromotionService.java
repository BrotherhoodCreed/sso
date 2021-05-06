package com.promotion.product.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Joiner;
import com.promotion.product.common.ModelCopier;
import com.promotion.product.dao.dataobject.*;
import com.promotion.product.dao.mysql.DictionaryDao;
import com.promotion.product.dao.mysql.PromotionBaseInfoDao;
import com.promotion.product.dao.mysql.PromotionMapperDao;
import com.promotion.product.dao.mysql2.FineUserDao;
import com.promotion.product.dao.mysql2.UserStoreDao;
import com.promotion.product.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
@Slf4j
@Service
public class PromotionService {
    @Autowired
    private PromotionBaseInfoDao promotionBaseInfoDao;

    @Autowired
    private PromotionMapperDao promotionMapperDao;
    @Autowired
    private DictionaryDao dictionaryDao;

    @Autowired
    private  ShopService shopService;

    @Autowired
    private FineUserDao fineUserDao;

    @Autowired
    private UserStoreDao userStoreDao;


    /**
     *根据钉钉手机号查询用户信息
     */
    public FineUserDo queryFineUser(String mobile){
        FineUserDo fineUserDo=fineUserDao.query(mobile);
        return fineUserDo;
    }
    /**
     *根据用户名查询区域
     */
    public UserStoreDo queryUserStore(String userName){
        UserStoreDo userStoreDo=userStoreDao.query(userName);
        return userStoreDo;
    }
    public  List<ExeclRespone>  exportExcel(List<String> codes){
        List<ExeclRespone> resultList = new ArrayList<>();
        List<ExeclDto> exportExcel =promotionBaseInfoDao.exportExcel(codes);
        Map<String,String> map =dictionaryDao.selectAll().stream().collect(Collectors.toMap(DictionaryDo::getDescriptionCode,DictionaryDo::getDescription));;
        if(CollectionUtils.isNotEmpty(exportExcel)){
            for (ExeclDto execlDto : exportExcel) {
                ExeclRespone execlRespone = new ExeclRespone();
                execlRespone.setArea(execlDto.getArea());
                execlRespone.setCity(execlDto.getCity());
                execlRespone.setAmount(String.valueOf(execlDto.getAmount()));
                execlRespone.setActivityCode(execlDto.getActivityCode());
                if(execlDto.getActivityType()!=null){
                    if(map.containsKey(execlDto.getActivityType())){
                        execlRespone.setActivityType(map.get(execlDto.getActivityType()));;
                    }
                }
                execlRespone.setRestaurantCode(execlDto.getRestaurantCode());
                execlRespone.setRestaurantName(execlDto.getRestaurantName());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                if(execlDto.getSalesStartTime()!=null){
                    execlRespone.setSalesStartTime(formatter.format(execlDto.getSalesStartTime()));
                }
                if(execlDto.getSalesEndTime()!=null){
                    execlRespone.setSalesEndTime(formatter.format(execlDto.getSalesEndTime()));
                }
                execlRespone.setDescription(execlDto.getDescription());
                execlRespone.setAmount(String.valueOf(execlDto.getAmount()));
                execlRespone.setBillCycle("T+"+String.valueOf(execlDto.getBillCycle()));
                execlRespone.setIntroduction(execlDto.getIntroduction());
                if(execlDto.getUsageStartTime()!=null){
                    execlRespone.setUsageStartTime(formatter.format(execlDto.getUsageStartTime()));
                }
                if(execlDto.getUsageEndTime()!=null){
                    execlRespone.setUsageEndTime(formatter.format(execlDto.getUsageEndTime()));
                }
                execlRespone.setSharedActivity(execlDto.getSharedActivity());
                execlRespone.setSellingPrice(String.valueOf(execlDto.getSellingPrice()));
                execlRespone.setBillPrice(String.valueOf(execlDto.getBillPrice()));
                execlRespone.setHandlingFee(String.valueOf(execlDto.getHandlingFee()));
                execlRespone.setTaxRate(String.valueOf(execlDto.getTaxRate())+"%");
                resultList.add(execlRespone);
            }
        }

        return  resultList;
    }


    public PromotionBaseInfoRespone queryPromotionBaseInfo(String activityCode) {
        PromotionBaseInfoDo promotionBaseInfoDo =promotionBaseInfoDao.selectOneData(activityCode);
        PromotionBaseInfoRespone promotionBaseInfoRespone=ModelCopier.copy(promotionBaseInfoDo,PromotionBaseInfoRespone.class);
//        if(StringUtils.isNotEmpty(promotionBaseInfoDo.getSharedActivity())){
//            promotionBaseInfoRespone.setSharedActivity(Arrays.asList(promotionBaseInfoDo.getSharedActivity().split(",")));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            if(promotionBaseInfoDo.getSalesStartTime()!=null){
                String salesStartTime = formatter.format(promotionBaseInfoDo.getSalesStartTime());
                promotionBaseInfoRespone.setSalesStartTime(salesStartTime);
            }
            if(promotionBaseInfoDo.getSalesEndTime()!=null){
                String salesEndTime = formatter.format(promotionBaseInfoDo.getSalesEndTime());
                promotionBaseInfoRespone.setSalesEndTime(salesEndTime);
            }
            if(promotionBaseInfoDo.getUsageStartTime()!=null){
                String usageStartTime = formatter.format(promotionBaseInfoDo.getUsageStartTime());
                promotionBaseInfoRespone.setUsageStartTime(usageStartTime);

            }
            if(promotionBaseInfoDo.getUsageEndTime()!=null){
                String usageEndTime = formatter.format(promotionBaseInfoDo.getUsageEndTime());
                promotionBaseInfoRespone.setUsageEndTime(usageEndTime);
            }
//        }
        return promotionBaseInfoRespone;
    }

    @Transactional
    public Boolean deletePromotionBaseById(List<Long> id){
        Integer row=0;
        for (Long aLong : id) {
            promotionBaseInfoDao.deletePromotionBaseById(aLong);
        }
        return row>0;
    }

    public Boolean deletePromotionById(Long id){
        return promotionMapperDao.deletePromotionById(id)>0;
    }

    public   BasePageResponse<QueryPromotionListRespone>queryPromotionList(QueryPromotionListRequest request,UserDao userDao){
        //todo 根据钉钉手机号查询用户信息，用户信息不存在报错提示联系it ，用户信息存在查询数据权限
        String mobile = userDao.getMobile();
        if (null == mobile){
            log.info("查询活动列表 | 用户手机号为空[{}]",userDao);
            return BasePageResponse.failure(BizErrorEnum.NO_PROMISE.getDesc(),BasePageResponse.class);
        }
        FineUserDo fineUserDo =  fineUserDao.query(mobile);
        if (null == fineUserDo){
            log.info("查询活动列表 | 用户手机号未找到对应数据",mobile);
            return BasePageResponse.failure(BizErrorEnum.NO_PROMISE.getDesc(),BasePageResponse.class);
        }
        BasePageResponse<QueryPromotionListRespone> response=BasePageResponse.success(BasePageResponse.class);
        Page pageInfo = PageHelper.startPage(request.getPageIndex(), request.getPageSize());
        PageHelper.orderBy("created_time desc");
        request.setCreatedUser(mobile);
        List<QueryPromotionListDo> queryPromotionList=promotionBaseInfoDao.queryPromotionList(request);
        if(CollectionUtils.isEmpty(queryPromotionList)){
            return response;
        }
//        UserStoreDo userStoreDo = userStoreDao.query(fineUserDo.getUserName());
//        if (null == userStoreDo){
//            log.info("查询活动列表 | 用户未找到对应StCd",fineUserDo.getUserName());
//            return BasePageResponse.failure(BizErrorEnum.NO_PROMISE.getDesc(),BasePageResponse.class);
//        }
//       List<String> stcds = new ArrayList<>(Arrays.asList(userStoreDo.getStCd().split(",")));
//        Iterator<QueryPromotionListDo> iterator = queryPromotionList.iterator();
//        while (iterator.hasNext()){
//            QueryPromotionListDo promotionListDo = iterator.next();
//            List<PromotionMapperDo> list = promotionMapperDao.selectByActivityCode(promotionListDo.getActivityCode());
//            for (PromotionMapperDo promotionMapperDo:list) {
//                if (!stcds.contains(promotionMapperDo.getRestaurantCode())){
//                    log.info("查询活动列表 | StCd[{}]不在用户[{}]权限内[{}]",promotionMapperDo.getRestaurantCode(),fineUserDo.getUserName(),stcds);
//                    iterator.remove();
//                    break;
//                }
//            }
//        }

        Map<String,String> map =dictionaryDao.selectAll().stream().collect(Collectors.toMap(DictionaryDo::getDescriptionCode,DictionaryDo::getDescription));;
        response.setTotal(pageInfo.getTotal());
        response.setPages(pageInfo.getPageNum());
        List<QueryPromotionListRespone> queryPromotionListResponeList =new ArrayList<>();
        for (QueryPromotionListDo QueryPromotionListDo : queryPromotionList) {
            QueryPromotionListRespone QueryPromotionListRespone =new QueryPromotionListRespone();
            QueryPromotionListRespone.setId(QueryPromotionListDo.getId());

            QueryPromotionListRespone.setActivityCode(QueryPromotionListDo.getActivityCode());
            if(map.get(QueryPromotionListDo.getActivityType())!=null){
                QueryPromotionListRespone.setActivityType(map.get(QueryPromotionListDo.getActivityType()));
            }
            QueryPromotionListRespone.setSalesStartTime(QueryPromotionListDo.getSalesStartTime());
            QueryPromotionListRespone.setSalesEndTime(QueryPromotionListDo.getSalesEndTime());
            QueryPromotionListRespone.setIntroduction(QueryPromotionListDo.getIntroduction());
            queryPromotionListResponeList.add(QueryPromotionListRespone);
        }
         response.setData(queryPromotionListResponeList);
       return response;
    }

    public SavePromotionBaseInfoRespone savePromotionBaseInfo(SavePromotionBaseInfoRequery savePromotionBaseInfoRequery,List<PromotionMapperDo> promotionMapperDo)  throws Exception{
        SavePromotionBaseInfoRespone savePromotionBaseInfoRespone =new SavePromotionBaseInfoRespone();
        PromotionBaseInfoDo promotionBaseInfoDo =ModelCopier.copy(savePromotionBaseInfoRequery,PromotionBaseInfoDo.class);

        Calendar calendar = Calendar.getInstance();
        Date date=calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("YYYYMM");
        Integer index= promotionBaseInfoDao.querySerialNumber();
        String area="";
        if(promotionMapperDo.get(0)!=null){
            area=promotionMapperDo.get(0).getArea();
        }
        String codePrefix="00";
        if(CollectionUtils.isNotEmpty(promotionMapperDo)){
            if(!(promotionMapperDo.stream().map(item->item.getRestaurantCode()).distinct().count()>1)){
                codePrefix=  shopService.queryShopAreaId(promotionMapperDo.get(0).getRestaurantCode());
            }
        }
        //01 区域号
        String code =codePrefix+ FormTypeEnums.TAKE_OUT.getIndex()+ format.format(date)+String.format("%03d",index);
        saveData(savePromotionBaseInfoRequery, promotionMapperDo, savePromotionBaseInfoRespone, promotionBaseInfoDo, code,FormTypeEnums.TAKE_OUT);
        return savePromotionBaseInfoRespone;
    }

    public SavePromotionBaseInfoRespone savePromotionBaseInfo_ts(SavePromotionBaseInfoRequery savePromotionBaseInfoRequery,List<PromotionMapperDo> promotionMapperDo)  throws Exception{
        SavePromotionBaseInfoRespone savePromotionBaseInfoRespone =new SavePromotionBaseInfoRespone();
        PromotionBaseInfoDo promotionBaseInfoDo =ModelCopier.copy(savePromotionBaseInfoRequery,PromotionBaseInfoDo.class);

        Calendar calendar = Calendar.getInstance();
        Date date=calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("YYYYMM");
        Integer index= promotionBaseInfoDao.querySerialNumber();
        String area="";
        if(promotionMapperDo.get(0)!=null){
            area=promotionMapperDo.get(0).getArea();
        }
        String codePrefix="00";
        if(CollectionUtils.isNotEmpty(promotionMapperDo)){
            if(!(promotionMapperDo.stream().map(item->item.getRestaurantCode()).distinct().count()>1)){
                codePrefix=  shopService.queryShopAreaId(promotionMapperDo.get(0).getRestaurantCode());
            }
        }
        //01 区域号
        String code =codePrefix+ FormTypeEnums.EAT_IN.getIndex()+ format.format(date)+String.format("%03d",index);
        saveData(savePromotionBaseInfoRequery, promotionMapperDo, savePromotionBaseInfoRespone, promotionBaseInfoDo, code,FormTypeEnums.EAT_IN);
        return savePromotionBaseInfoRespone;
    }

    private void saveData(SavePromotionBaseInfoRequery savePromotionBaseInfoRequery, List<PromotionMapperDo> promotionMapperDo, SavePromotionBaseInfoRespone savePromotionBaseInfoRespone, PromotionBaseInfoDo promotionBaseInfoDo, String code,FormTypeEnums type) {
        promotionBaseInfoDo.setActivityCode(code);
        promotionBaseInfoDo.setCreatedUser(savePromotionBaseInfoRequery.getCreatedUser());
        promotionBaseInfoDo.setCreatedTime(new Date());
        promotionBaseInfoDo.setUpdatedTime(new Date());
        promotionBaseInfoDo.setSubmit(SubmitEnums.SAVE.getCode());
        promotionBaseInfoDo.setType(type.getIndex());
        if(CollectionUtils.isNotEmpty(savePromotionBaseInfoRequery.getSharedActivity())){
            String string=Joiner.on(",").join(savePromotionBaseInfoRequery.getSharedActivity());
            promotionBaseInfoDo.setSharedActivity(string);
        }
        Boolean result=  promotionBaseInfoDao.insert(promotionBaseInfoDo)>0;
        if(result){
            savePromotionBaseInfoRespone.setActivityCode(code);
            if(CollectionUtils.isNotEmpty(promotionMapperDo)){
                List<PromotionMapperDo> promotionMapperDos=promotionMapperDao.selectByActivityCode(code);
                if(CollectionUtils.isNotEmpty(promotionMapperDos)){
                    List<String> stringListi = promotionMapperDos.stream().map(item -> item.getRestaurantCode()).collect(Collectors.toList());
                    promotionMapperDo.removeIf(item->stringListi.contains(item.getRestaurantCode()));
                }
                for (PromotionMapperDo mapperDo : promotionMapperDo) {
                    mapperDo.setActivityCode(code);
                    promotionMapperDao.insert(mapperDo);
                }
            }
        }
    }

    public Boolean savePromotionMapperInfo(SavePromotionMapperInfoRequest req){
        String activityCode=req.getPromotionMapperDos().get(0).getActivityCode();
        if(StringUtils.isNotEmpty(activityCode)){
            List<PromotionMapperDo> promotionMapperDos=promotionMapperDao.selectByActivityCode(activityCode);
            if(CollectionUtils.isNotEmpty(promotionMapperDos)){
                List<String> list = promotionMapperDos.stream().map(item -> item.getRestaurantCode()).collect(Collectors.toList());
                req.getPromotionMapperDos().removeIf(item->list.contains(item.getRestaurantCode()));
            }
        }
        Integer row=0;
        for (PromotionMapperDo promotionMapperDo : req.getPromotionMapperDos()) {
            row+= promotionMapperDao.insert(promotionMapperDo);
        }
        return  row>0;

    }


    public Boolean updatePromotionBaseInfo(UpdatePromotionBaseInfoRequery requery) {
        PromotionBaseInfoDo promotionBaseInfoDo=ModelCopier.copy(requery.getPromotionBaseInfoDo(),PromotionBaseInfoDo.class);
        if(CollectionUtils.isNotEmpty( requery.getPromotionBaseInfoDo().getSharedActivity())){
            promotionBaseInfoDo.setSharedActivity(String.join(",",requery.getPromotionBaseInfoDo().getSharedActivity()));
        }
         Boolean result  = promotionBaseInfoDao.update(promotionBaseInfoDo) > 0;
         return result;
    }


    public  List<PromotionBaseInfoDo> startProcess(List<String> activityCode){
        List<PromotionBaseInfoDo> promotionBaseInfoDoList= promotionBaseInfoDao.selectByAivityCodes(activityCode);
        return promotionBaseInfoDoList;
    }
}
