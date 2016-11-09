package service.impl;

import constant.MyException;
import dao.CharacterDao;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CharacterService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Knigh on 2016/9/3.
 */
@Service
public class CharacterServiceImpl extends BaseServiceImpl implements CharacterService {

    @Autowired
    private CharacterDao characterDao;

    private int noteNum = 4;  //每个单元所包含的信息

    public List<CharacterModel> getAllCharacters() throws Exception{
        String characterStr = characterDao.getAllCharacters();
        String strs[] = characterStr.split("\\\n");
        List<CharacterModel> characterModels = new ArrayList<CharacterModel>();
        for(String str : strs){
            String nameStrs[] = str.split(":");
            if(nameStrs.length>0 && nameStrs[0].length()>0){
                String name = nameStrs[0];
                List<Double> money = new ArrayList<Double>();
                List<String> dates = new ArrayList<String>();
                List<PayDateAuthorModel> payDateAuthorModelList = new ArrayList<PayDateAuthorModel>();
                double total = 0;
                String date = "null";
                String author = "null";
                BigDecimal tempBigDecimal;
                if(nameStrs.length > 1){
                    String moneyStrs[] = nameStrs[1].split(",");
                    PayDateAuthorModel payDateAuthorModel = null;
                    for(int i=0; i<moneyStrs.length; i++){
                        String m = moneyStrs[i];
                        if(i%noteNum == 0){
                            payDateAuthorModel = new PayDateAuthorModel();
                            money.add(new Double(m));
                            total += Double.parseDouble(m);
                            payDateAuthorModel.setPay(new Double(m));
                        }else{
                            if(i%noteNum == 1){
                                date = m;
                                dates.add(date);
                                payDateAuthorModel.setDate(m);
                            }else{
                                if(i%noteNum == 2){
                                    author = m;
                                    payDateAuthorModel.setAuthor(author);
                                }else{
                                    payDateAuthorModel.setRemark(m);
                                    payDateAuthorModelList.add(payDateAuthorModel);
                                }
                            }

                        }
                    }
                }
                tempBigDecimal = new BigDecimal(total);
                CharacterModel model = new CharacterModel(name, money, tempBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(), dates, payDateAuthorModelList);
                characterModels.add(model);
            }
        }

        Collections.sort(characterModels, new Comparator<CharacterModel>() {
            public int compare(CharacterModel model0, CharacterModel model1) {
                return new Double(model1.getTotal()).compareTo(new Double(model0.getTotal()));
            }
        });

        return characterModels;
    }

    public List<PayRelationShip> calculatEveryPay(List<CharacterModel> characterModels){
        //算平均值
        BigDecimal totalMoney = new BigDecimal("0");
        BigDecimal avgMoney;
        for (CharacterModel item : characterModels){
            totalMoney = totalMoney.add(new BigDecimal(item.getTotal()));
        }

        avgMoney = totalMoney.divide(new BigDecimal(""+characterModels.size()), 2, BigDecimal.ROUND_HALF_UP);
        //算每个人应该或者得到的钱数（receive为得到，是+，pay为付出，是-）
        List<NameMoney> receiveMoneys = new ArrayList<NameMoney>();
        List<NameMoney> payMoneys = new ArrayList<NameMoney>();
        for(CharacterModel item : characterModels){
            BigDecimal diff = new BigDecimal(""+item.getTotal()).subtract(new BigDecimal(""+avgMoney));
            if(diff.compareTo(new BigDecimal("0")) > 0){
                NameMoney temp = new NameMoney(item.getName(), diff);
                receiveMoneys.add(temp);
            }
            if(diff.compareTo(new BigDecimal("0")) < 0){
                NameMoney temp = new NameMoney(item.getName(), diff);
                payMoneys.add(temp);
            }
        }
        int receivePoint = 0;
        int payPoint = 0;
        List<PayRelationShip> payRelationShipList = new ArrayList<PayRelationShip>();
        while (true){
            if(payPoint==payMoneys.size() && receivePoint==receiveMoneys.size()){
                break;
            }
            BigDecimal tempTotalMoney = payMoneys.get(payPoint).getMoney().add(
                    receiveMoneys.get(receivePoint).getMoney());
            PayRelationShip ship = null;
            if(tempTotalMoney.abs().compareTo(new BigDecimal("0.1")) > 0){
                if(tempTotalMoney.compareTo(new BigDecimal("0")) > 0){
                    ship = new PayRelationShip(payMoneys.get(payPoint).getName(),
                            receiveMoneys.get(receivePoint).getName(), payMoneys.get(payPoint).getMoney().abs());
                    receiveMoneys.get(receivePoint).setMoney(tempTotalMoney);
                    payPoint++;
                }else{
                    if(tempTotalMoney.compareTo(new BigDecimal("0")) < 0){
                        ship = new PayRelationShip(payMoneys.get(payPoint).getName(),
                                receiveMoneys.get(receivePoint).getName(), receiveMoneys.get(receivePoint).getMoney().abs());
                        payMoneys.get(payPoint).setMoney(tempTotalMoney);
                        receivePoint++;
                    }else{
                        ship = new PayRelationShip(payMoneys.get(payPoint).getName(),
                                receiveMoneys.get(receivePoint).getName(), payMoneys.get(payPoint).getMoney().abs());
                        receivePoint++;
                        payPoint++;
                    }
                }
            }else{
                ship = new PayRelationShip(payMoneys.get(payPoint).getName(),
                        receiveMoneys.get(receivePoint).getName(), payMoneys.get(payPoint).getMoney().abs());
                receivePoint++;
                payPoint++;
            }
            payRelationShipList.add(ship);
        }
        for(PayRelationShip s : payRelationShipList){
            System.out.println(s);
        }
        return payRelationShipList;
    }

    /**
     * 返回 str 中 ; 的数量
     * @param str
     * @return
     */
    private int getSemicolonNum(String str){
        char chars[] = str.toCharArray();
        int sum = 0;
        for(int i=0; i<chars.length; i++){
            if(chars[i] == ';'){
                sum++;
            }
        }
        return sum;
    }

    public void addPayMoney(AddPayMoneyRequest request, HttpServletRequest httpServletRequest) throws Exception{
        if(request.getPaymoney().contains(";") || request.getRemarks().contains(";")){
            if(getSemicolonNum(request.getPaymoney()) != getSemicolonNum(request.getRemarks())){
                throw new MyException("semicolon is not equal in paymony and remarks!");
            }
        }
        characterDao.addPayMoney(request, httpServletRequest);
    }

    public void operateCharacter(String characterName, String operateType) throws Exception {
        if(characterName==""){
            throw new MyException("characterName is null!");
        }
        if(!operateType.equals("add") && !operateType.equals("delete")){
            throw new MyException("operation is null!");
        }
        characterDao.operateCharacter(characterName, operateType);
    }

    public void clear() throws Exception {
        characterDao.clear();
    }

    public void deleteRecord(Record record) throws Exception {
        String characterStr = characterDao.getAllCharacters();
        String lineStrs[] = characterStr.split("\\\n");
        String line = "";
        String resultStr = "";
        for(String item : lineStrs){
            if(item.contains(record.getCharactername())){
                line = item;
            }else{
                resultStr += item + "\n";
            }
        }
        String nameData[] = line.split(":");
        String recordStrs[] = nameData[1].split(",");
        String temp = nameData[0]+":";
        boolean isDelete = false;
        for(int i=0; i<recordStrs.length; i+=4){
            if(recordStrs[i].equals(record.getMoney())
                    && recordStrs[i+1].equals(record.getDate())
                    && recordStrs[i+2].equals(record.getAuthor())
                    && recordStrs[i+3].equals(record.getMark())
                    && isDelete==false){
                isDelete = true;
            }else{
                temp += (recordStrs[i]+","+recordStrs[i+1]+","+recordStrs[i+2]+","+recordStrs[i+3]+",");
            }
        }
        resultStr += temp;
        System.out.println("deleted:");
        System.out.println(resultStr);
        characterDao.deleteRecord(resultStr);
    }
}
