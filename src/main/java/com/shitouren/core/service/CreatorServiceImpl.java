package com.shitouren.core.service;


import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.bean.eums.ImgEnum;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class CreatorServiceImpl implements CreatorService {

    @Autowired
    CreaterDao createrDao;
    @Autowired
    UsersDao usersDao;
    @Autowired
    ClassificationDao classificationDao;
    @Autowired
    CreatorlistDao creatorlistDao;
    @Autowired
    DictService dictService;
    @Autowired
    ApliaymhDao apliaymhDao;

    @Override
    public void creator(int logUserPid,String name,String content) {
        Users users=usersDao.selectByPrimaryKey(logUserPid);
        if(users.getIscreater()==1){
            throw new CloudException(ExceptionConstant.你已经是创作者);
        }

        CreaterExample createrExample=new CreaterExample();
        createrExample.createCriteria().andUseridEqualTo(logUserPid);
        List<Creater>createrList=createrDao.selectByExample(createrExample);
        if(createrList.size()>0){
           Creater creater=createrList.get(0);
           creater.setName(name);
           creater.setContent(content);
           createrDao.updateByPrimaryKey(creater);
        }else{
            Creater creater=new Creater();
            creater.setName(name);
            creater.setContent(content);
            creater.setUserid(logUserPid);
            creater.setTime(new Date());
            createrDao.insertSelective(creater);
        }
    }

    @Override
    public List classification(int logUserPid) {
        List <Classification>classificationList=classificationDao.selectByExample(new ClassificationExample());
        return classificationList;
    }

    @Override
    public void addcreator(int logUserPid, String name, String img, String contentimg, String content, String copyright, BigDecimal price, int number, int classid,int id) {
        if(id==0){
            Creatorlist creatorlist=new Creatorlist();
            creatorlist.setUserid(logUserPid);
            creatorlist.setName(name);
            creatorlist.setImg(img);
            creatorlist.setContentimg(contentimg);
            creatorlist.setContent(content);
            creatorlist.setCopyright(copyright);
            creatorlist.setPrice(price);
            creatorlist.setNumber(number);
            creatorlist.setClassid(classid);
            creatorlist.setCreattime(new Date());
            creatorlistDao.insertSelective(creatorlist);
        }else{
            Creatorlist creatorlist=creatorlistDao.selectByPrimaryKey(id);
            creatorlist.setUserid(logUserPid);
            creatorlist.setName(name);
            if(img!=""){
                creatorlist.setImg(img);
            }
            if(contentimg!=""){
                creatorlist.setContentimg(contentimg);
            }
            if(copyright!=""){
                creatorlist.setCopyright(copyright);
            }

            creatorlist.setType(0);
            creatorlist.setContent(content);

            creatorlist.setPrice(price);
            creatorlist.setNumber(number);
            creatorlist.setClassid(classid);
            creatorlist.setCreattime(new Date());
            creatorlistDao.updateByPrimaryKeySelective(creatorlist);
        }

    }

    @Override
    public void cancelcreat(int logUserPid, int id) {
        Creatorlist creatorlist=creatorlistDao.selectByPrimaryKey(id);
        if(creatorlist.getType()==3){
            throw new CloudException(ExceptionConstant.藏品已上架);
        }
        creatorlistDao.deleteByPrimaryKey(id);

    }

    @Override
    public Map editcreat(int logUserPid, int id) {
        Map map=new HashMap();
        Creatorlist creatorlist=creatorlistDao.selectByPrimaryKey(id);
        if(creatorlist.getType()==3){
            throw new CloudException(ExceptionConstant.藏品已上架);
        }


        map.put("name",creatorlist.getName());
        map.put("id",creatorlist.getId());
        map.put("img",  creatorlist.getImg());
        map.put("contentimg",creatorlist.getContentimg());
        map.put("content",creatorlist.getContent());
        map.put("copyright",creatorlist.getCopyright());
        map.put("price",creatorlist.getPrice());
        map.put("number",creatorlist.getNumber());
        map.put("classid",creatorlist.getClassid());

        return map;
    }

    @Override
    public List creatlist(int logUserPid) {
        List list=new ArrayList();

        CreatorlistExample creatorlistExample=new CreatorlistExample();
        creatorlistExample.createCriteria().andUseridEqualTo(logUserPid);
        List<Creatorlist>creatorlistList=creatorlistDao.selectByExample(creatorlistExample);
        if(creatorlistList.size()>0){
            for (Creatorlist creatorlist : creatorlistList) {
                Map map=new HashMap();
                map.put("name",creatorlist.getName());
                map.put("img", ImgEnum.img.getUrl()+ creatorlist.getImg());
                map.put("price",creatorlist.getPrice());
                map.put("type",creatorlist.getType());
                map.put("number",creatorlist.getNumber());
                map.put("id",creatorlist.getId());
                list.add(map);
            }
        }

        return list;
    }

    @Override
    public Map puton(int logUserPid, int id) {
        Map map=new HashMap();
        Creatorlist creatorlist=creatorlistDao.selectByPrimaryKey(id);
        map.put("id",creatorlist.getId());
        map.put("name",creatorlist.getName());
        map.put("img",ImgEnum.img.getUrl()+creatorlist.getImg());
        CreaterExample createrExample=new CreaterExample();
        createrExample.createCriteria().andUseridEqualTo(creatorlist.getUserid());
        List<Creater>createrList=createrDao.selectByExample(createrExample);
        if(createrList.size()>0){
            Creater creater=createrList.get(0);
            map.put("creator",creater.getName());
            map.put("content",creater.getContent());
        }
        map.put("price",creatorlist.getPrice());
        BigDecimal bond=new BigDecimal(dictService.getValue("bond"));
        BigDecimal number=new BigDecimal(creatorlist.getNumber());
        BigDecimal forge=new BigDecimal(dictService.getValue("forge"));
        BigDecimal totalforge=number.multiply(forge).setScale(2, BigDecimal.ROUND_CEILING);
        map.put("bond",bond);
        map.put("totalforge",totalforge);
        map.put("total",bond.add(totalforge));
        return map;
    }

    @Override
    public Map Payorder(int logUserPid, int id, int type) {
        Map map=new HashMap();
        return map;
    }
}
