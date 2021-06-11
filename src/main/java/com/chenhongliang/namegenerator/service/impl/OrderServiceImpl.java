package com.chenhongliang.namegenerator.service.impl;

import com.alibaba.fastjson.JSON;
import com.chenhongliang.namegenerator.controller.NameGeneratorController;
import com.chenhongliang.namegenerator.form.OrderCommentForm;
import com.chenhongliang.namegenerator.form.OrderForm;
import com.chenhongliang.namegenerator.mapper.OrderMapper;
import com.chenhongliang.namegenerator.model.*;
import com.chenhongliang.namegenerator.service.NameGeneratorService;
import com.chenhongliang.namegenerator.service.OrderService;
import com.chenhongliang.namegenerator.util.*;
import com.chenhongliang.namegenerator.vo.OrderListVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private NameGeneratorService nameGeneratorService;

    @Autowired
    private NameGeneratorController nameGeneratorController;

    @Override
    public Map<String, Object> addOrder(OrderForm orderForm) {
        OrderModel orderModel = new OrderModel();
        orderModel.setOrderNumber(orderForm.getOrderNumber());
        orderModel.setSalesman(orderForm.getSalesman());
        orderModel.setWechatMachine(orderForm.getWechatMachine());
        orderModel.setNameGiver(orderForm.getNameGiver());
        orderModel.setBills(orderForm.getBills());
        orderModel.setPlan(orderForm.getPlan());
        orderModel.setLastname(orderForm.getLastname());
        orderModel.setSex(orderForm.getSex());
        orderModel.setNameSize(orderForm.getNameSize());
        orderModel.setBirthday(orderForm.getBirthday());
        orderModel.setBirthdayLunar(solarToLunar(orderForm.getBirthday()));
        orderModel.setBirthdayHour(orderForm.getBirthdayHour());
        orderModel.setBirthdayMinute(orderForm.getBirthdayMinute());
        orderModel.setBannedPinyin(SplitStringUtils.commaInit(PinyinUtils.getPinyin(orderForm.getBannedPinyin())));
        orderModel.setBannedCharacter(SplitStringUtils.commaInit(orderForm.getBannedCharacter()));
        orderModel.setGeneration(orderForm.getGeneration());
        orderModel.setStyle(orderForm.getStyle());
        orderModel.setNotes(orderForm.getNotes());
        orderModel.setWuxing(orderForm.getWuxing());
        orderModel.setStatus("待交付");
        orderModel.setUpdateTime(DateStringUtils.dateToString(new Date()));
        orderModel.setDelivered(false);
//        Map<String, List<String>> xiyongshenMap = null;
//        if (orderModel.getPlan().startsWith("八字")) {
//            Map<String, List<String>> xiyongshen = FortuneTellingUtils.getXiYongShen(orderForm);
//            orderModel.setWuxing(String.join(" ", xiyongshen.get("yongshen")) + " " + String.join(" ", xiyongshen.get("xishen")));
//            if (orderModel.getWuxing().length()>3) {
//                xiyongshenMap = xiyongshen;
//            }
//        }

        Date dateNow = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateNow);
        cal.add(Calendar.HOUR, orderForm.getTillDeliveryTime());
        orderModel.setDeliveryTime(DateStringUtils.dateToStringLong(cal.getTime()));
        orderMapper.insert(orderModel);
        Integer orderId = orderModel.getId();

        if (orderModel.getPlan().startsWith("八字")) {
            Map<String, String> querys = new HashMap<>();
            querys.put("birthday", orderModel.getBirthday());
            querys.put("hour", orderModel.getBirthdayHour());
            querys.put("ming", "");
            querys.put("minute", orderModel.getBirthdayMinute());
            querys.put("pay", "1");
            querys.put("sex", orderModel.getSex());
            querys.put("xing", orderModel.getLastname());

            Map mingpen = getMapFromAPI("/openapi/bazi/getMingpen", querys);
            MingpenModel mingpenModel = new MingpenModel();
            mingpenModel.setOrderId(orderModel.getId().toString());
            mingpenModel.setZhuxing(objToString(mingpen.get("ZhuXing")));
            mingpenModel.setTiangan(objToString(mingpen.get("TianGan")));
            mingpenModel.setDizhi(objToString(mingpen.get("DiZhi")));
            mingpenModel.setDayun(objToString(mingpen.get("DaYun")));
            mingpenModel.setYongshen(objToString(mingpen.get("YongShen")));
            mingpenModel.setXishen(objToString(mingpen.get("XiShen")));
            mingpenModel.setJishen(objToString(mingpen.get("JiShen")));
            mingpenModel.setJiaoyunshijian(objToString(mingpen.get("JiaoYunShiJian")));
            mingpenModel.setQiangruo(objToString(mingpen.get("QiangRuo")));
            mingpenModel.setWuxing(objToString(mingpen.get("WuXing")));
            mingpenModel.setMu(objToString(mingpen.get("Mu")));
            mingpenModel.setJin(objToString(mingpen.get("Jin")));
            mingpenModel.setShui(objToString(mingpen.get("Shui")));
            mingpenModel.setTu(objToString(mingpen.get("Tu")));
            mingpenModel.setHuo(objToString(mingpen.get("Huo")));
            orderMapper.addMingpen(mingpenModel);
            if (!orderModel.getPlan().startsWith("八字起名套餐1")) {
                Map mingju = getMapFromAPI("/openapi/bazi/getMingju", querys);
                MingjuModel mingjuModel = new MingjuModel();
                mingjuModel.setOrderId(orderModel.getId().toString());
                mingjuModel.setMingpen(objToString(mingju.get("MingPen")));
                mingjuModel.setXingge(objToString(mingju.get("XingGe")));
                mingjuModel.setXueli(objToString(mingju.get("XueLi")));
                mingjuModel.setCaifu(objToString(mingju.get("CaiFu")));
                mingjuModel.setCaifushiye(objToString(mingju.get("CaiFuShiYe")));
                mingjuModel.setDiwei(objToString(mingju.get("DiWei")));
                mingjuModel.setLiuqin(objToString(mingju.get("LiuQin")));
                mingjuModel.setJibing(objToString(mingju.get("JiBing")));
                mingjuModel.setShiye(objToString(mingju.get("ShiYe")));
                mingjuModel.setYiji(objToString(mingju.get("YiJi")));
                mingjuModel.setXiongzai(objToString(mingju.get("XiongZai")));
                mingjuModel.setGuansha(objToString(mingju.get("GuanSha")));
                orderMapper.addMingju(mingjuModel);
            }
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("id", orderId);
//        resultMap.put("xiyongshenMap", xiyongshenMap);
        resultMap.put("xiyongshenMap", null);

        return resultMap;
    }

    @Override
    public Boolean updateWuxing(String id, List<String> wuxing) {
        Boolean result = orderMapper.updateWuxing(id, String.join(" ", wuxing));
        nameGeneratorService.removeGeneratedNames(id);
        nameGeneratorController.fromCharacters(id);
        nameGeneratorController.fromNameLibrary(id);
        return result;
    }

    @Override
    public Boolean updateWuxingStr(String id, String wuxing) {
        Boolean result = orderMapper.updateWuxing(id, wuxing);
        nameGeneratorService.removeGeneratedNames(id);
        nameGeneratorController.fromCharacters(id);
        nameGeneratorController.fromNameLibrary(id);
        return result;
    }

    private String objToString(Object obj) {
        return Objects.isNull(obj) ? null : String.valueOf(obj);
    }

    @Override
    public PageInfo<OrderListVo> orderList(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -7);
        List<OrderListVo> orderList = orderMapper.getList(DateStringUtils.dateToString(cal.getTime()));
        PageInfo<OrderListVo> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }

    @Override
    public OrderModel getDetail(String id) {
        return orderMapper.getDetail(id);
    }

    @Override
    public OrderModel getDetailByOrderNumber(String orderNumber) {
        return orderMapper.getDetailByOrderNumber(orderNumber);
    }

    @Override
    public Boolean updateOrder(OrderModel orderModel) {
        orderModel.setBannedPinyin(SplitStringUtils.commaInit(PinyinUtils.getPinyin(orderModel.getBannedPinyin())));
        orderModel.setBannedCharacter(SplitStringUtils.commaInit(orderModel.getBannedCharacter()));
        if (!Objects.isNull(orderModel.getTillDeliveryTime())) {
            Date dateNow = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateNow);
            cal.add(Calendar.HOUR, orderModel.getTillDeliveryTime());
            orderModel.setDeliveryTime(DateStringUtils.dateToStringLong(cal.getTime()));
        }
        orderModel.setUpdateTime(DateStringUtils.dateToString(new Date()));
        orderModel.setBirthdayLunar(solarToLunar(orderModel.getBirthday()));
        return orderMapper.updateOrder(orderModel);
    }

    @Override
    public List<OrderCommentModel> getComments(String orderId) {
        return orderMapper.getComments(orderId);
    }

    @Override
    public List<OrderCommentModel> getCommentsByOrderNumber(String orderNumber) {
        return orderMapper.getCommentsByOrderNumber(orderNumber);
    }

    @Override
    public Boolean addComment(OrderCommentForm orderCommentForm) {
        orderMapper.updateStatus(orderCommentForm.getOrderId(), "待调整-" + orderCommentForm.getCommentCnt(), DateStringUtils.dateToString(new Date()), false);
        return orderMapper.addComment(orderCommentForm);
    }

    @Override
    public PageInfo<OrderListVo> orderDeliveringList(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<OrderListVo> orderList = orderMapper.getListDelivering();
        PageInfo<OrderListVo> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }

    @Override
    public List<OrderGeneratedNameModel> getGeneratedNames(String orderId, Boolean namelibType) {
        return orderMapper.getGeneratedNames(orderId, namelibType);
    }

    @Override
    public Boolean deliverOrder(String id) {
        String status = orderMapper.getStatus(id);
        return orderMapper.updateStatus(id, status.replace("待", "已"), DateStringUtils.dateToString(new Date()), true);
    }

    @Override
    public MingpenModel getMingpen(String orderId) {
        return orderMapper.getMingpen(orderId);
    }

    @Override
    public MingjuModel getMingju(String orderId) {
        return orderMapper.getMingju(orderId);
    }

    @Override
    public Boolean deleteOrder(String orderId) {
        return orderMapper.deleteOrder(orderId);
    }

    @Override
    public Boolean finishOrder(String orderId, String resultName) {
        return orderMapper.finishOrder(orderId, resultName);
    }

    @Override
    public List<OrderRunInfoModel> getOrderRunInfo(String orderId) {
        return orderMapper.getOrderRunInfo(orderId);
    }

    @Override
    public Boolean modifyOrderRunInfo(String orderId,
                                      List<OrderRunInfoModel> runInfoModelList,
                                      HttpServletResponse response) throws IOException {
        orderMapper.removeOrderRunInfo(orderId);
        for (OrderRunInfoModel item : runInfoModelList) {
            orderMapper.addOrderRunInfo(item);
        }
        OrderModel orderModel = orderMapper.getDetail(orderId);
        MingjuModel mingjuModel = orderMapper.getMingju(orderId);
        MingpenModel mingpenModel = orderMapper.getMingpen(orderId);
        Map<String, String> replaceMap = new HashMap<>();


        if (orderModel.getPlan().startsWith("八字")) {
            List<String> wuxingLack = new ArrayList<>();
            if (Integer.valueOf(mingpenModel.getJin().substring(2,3)).equals(0)) {
                wuxingLack.add("金");
            }
            if (Integer.valueOf(mingpenModel.getMu().substring(2,3)).equals(0)) {
                wuxingLack.add("木");
            }
            if (Integer.valueOf(mingpenModel.getShui().substring(2,3)).equals(0)) {
                wuxingLack.add("水");
            }
            if (Integer.valueOf(mingpenModel.getHuo().substring(2,3)).equals(0)) {
                wuxingLack.add("火");
            }
            if (Integer.valueOf(mingpenModel.getTu().substring(2,3)).equals(0)) {
                wuxingLack.add("土");
            }
            if (wuxingLack.isEmpty()) {
                replaceMap.put("${wuxinglack}", "全");
            } else {
                replaceMap.put("${wuxinglack}", "缺" + StringUtils.join(wuxingLack, "、"));
            }

            replaceMap.put("${xiyongwuxing}", Objects.isNull(orderModel.getWuxing())?"":orderModel.getWuxing().replace(" ",""));
        }

        for (Integer i = 0; i < 20; i++) {
            if (i < runInfoModelList.size()) {
                replaceMap.put("${name" + i + "}", orderModel.getLastname() + runInfoModelList.get(i).getName() + "【" + runInfoModelList.get(i).getWuxing() + "】");
                replaceMap.put("${meaning" + i + "}", runInfoModelList.get(i).getMeaning());
            } else {
                replaceMap.put("${name" + i + "}", null);
                replaceMap.put("${meaning" + i + "}", null);
            }
        }

        ClassPathResource resource = new ClassPathResource("wordTemplate/template.docx");
        XWPFDocument doc = new XWPFDocument(resource.getInputStream());
        List<XWPFParagraph> toDeleteList = new ArrayList<>();

        /**
         * 替换段落中指定的文本
         */
        for (int i = doc.getParagraphs().size() - 1; i >= 0; i--) {
            XWPFParagraph p = doc.getParagraphs().get(i);
            String text = p.getText();
            System.out.println("[p]");
            System.out.println(text);
            if (!Objects.isNull(text) && !text.isEmpty()) {
                for (String key : replaceMap.keySet()) {
                    if (text.contains(key)) {
                        if (Objects.isNull(replaceMap.get(key))) {
                            toDeleteList.add(p);
                        } else {
                            List<XWPFRun> runs = p.getRuns();
                            for (XWPFRun r : runs) {
                                if (r.text().contains(key)) {
                                    r.setText(replaceMap.get(key), 0);
                                    if (key.startsWith("${mean")) {
                                        r.addBreak(BreakType.TEXT_WRAPPING);
                                        r.addBreak(BreakType.TEXT_WRAPPING);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (XWPFParagraph toDelete : toDeleteList) {
            doc.removeBodyElement(doc.getPosOfParagraph(toDelete));
        }

        docToResponse(doc, response);

        return true;
    }

    private void docToResponse(XWPFDocument doc, HttpServletResponse response) throws IOException {

        doc.write(response.getOutputStream());
        response.addHeader("Content-Disposition", "attachment; filename=template.docx");
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    private Map getMapFromAPI(String path, Map<String, String> querys) {
        String host = "https://openapi.fatebox.cn";
        String method = "GET";
        String appcode = "32cf3b4f21904b27bd7877354307b724";
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, headers, querys);
            String result = EntityUtils.toString(response.getEntity());
            Map mapType = JSON.parseObject(result, LinkedHashMap.class);
            return mapType;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap();
        }
    }

    private String solarToLunar(String date) {
        if (Objects.isNull(date)) return null;
        Calendar day = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            day.setTime(formatter.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LunarUtils lunar = new LunarUtils(day);
        return lunar.cyclical() + "年" + lunar.toString();
    }

}
