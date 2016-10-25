package controller;

import model.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.CharacterService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by Knigh on 2016/9/3.
 */
@Controller
@RequestMapping(value = "/characterController")
public class CharacterController extends BaseController{

    @Autowired
    private CharacterService characterService;

    @RequestMapping(value = "/deleteRecord")
    public String deleteRecord(Record record) throws Exception{
        System.out.println("deleteRecord");
        System.out.println(record);
        characterService.deleteRecord(record);
        return "redirect:/characterController/showDetails";
    }

    @RequestMapping(value = "/clear")
    public String clear(HttpServletRequest request, Model model) throws Exception{
        characterService.clear();
        String resultStr = "清空成功!";
        model.addAttribute("resultStr", resultStr);
        model.addAttribute("targetUrl", (request.getContextPath()+"/userController/goManager"));
        return "result";
    }

    @RequestMapping(value = "/operateCharacter", method = RequestMethod.POST)
    public String operateCharacter(OperateCharacterRequest request) throws Exception{
//        String characterName = request.getParameter("characterName");
//        String operateType = request.getParameter("operateType");
        String characterName = request.getCharacterName();
        String operateType = request.getOperateType();
        System.out.println("addCharacter");
        System.out.println("characterName:" + characterName);
        System.out.println("operateType:" + operateType);
        characterService.operateCharacter(characterName, operateType);
        return "redirect:/characterController/showDetails";
    }

    @RequestMapping(value = "/addPayMoney", method = RequestMethod.POST)
    public String addPayMoney(AddPayMoneyRequest request, HttpServletRequest httpServletRequest) throws Exception{
        characterService.addPayMoney(request, httpServletRequest);
        return "redirect:/characterController/showDetails";
    }

    @RequestMapping(value = "/getAllCharacter")
    public String getAllCharacters(Model model) throws Exception{
        System.out.println("getAllCharacter");
        List<CharacterModel> list = characterService.getAllCharacters();
        List<PayRelationShip> payList = characterService.calculatEveryPay(list);
        System.out.println("list:");
        for(CharacterModel m : list){
            System.out.println(m);
        }
        model.addAttribute("characterlist", list);
        model.addAttribute("payList", payList);
        model.addAttribute("characters", JSONArray.fromObject(list).toString());
        return "home";
    }

    @RequestMapping(value = "/showDetails")
    public String showDetails(Model model) throws Exception{
        List<CharacterModel> list = characterService.getAllCharacters();
        double all = 0;
        for(CharacterModel item : list){
            all += item.getTotal();
        }
        BigDecimal avg;
        if(all != 0){
            avg = new BigDecimal(all).divide(new BigDecimal(list.size()), 2, RoundingMode.HALF_DOWN);
        }else{
            avg = new BigDecimal(all).divide(new BigDecimal(1), 2, RoundingMode.HALF_DOWN);
        }
        double total = new BigDecimal(all).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
        model.addAttribute("avg", avg);
        model.addAttribute("total", total);
        model.addAttribute("characterlist", list);
        model.addAttribute("characters", JSONArray.fromObject(list).toString());
        System.out.println(JSONArray.fromObject(list).toString());
        return "details";
    }

}
