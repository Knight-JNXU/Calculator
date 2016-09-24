package controller;

import model.AddCharacterRequest;
import model.CharacterModel;
import model.PayRelationShip;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CharacterService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Knigh on 2016/9/3.
 */
@Controller
@RequestMapping(value = "/characterController")
public class CharacterController extends BaseController{

    @Autowired
    private CharacterService characterService;

    @RequestMapping(value = "/operateCharacter", method = RequestMethod.POST)
    public String operateCharacter(HttpServletRequest request) throws Exception{
        String characterName = request.getParameter("characterName");
        String operateType = request.getParameter("operateType");
        System.out.println("addCharacter");
        System.out.println("characterName:" + characterName);
        System.out.println("operateType:" + operateType);
        characterService.operateCharacter(characterName, operateType);
        return "redirect:/characterController/showDetails";
    }

    @RequestMapping(value = "/addPayMoney", method = RequestMethod.POST)
    public String addPayMoney(AddCharacterRequest request, HttpServletRequest httpServletRequest) throws Exception{
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
        BigDecimal avg = new BigDecimal(all).divide(new BigDecimal(list.size()), 2, RoundingMode.HALF_DOWN);
        model.addAttribute("avg", avg);
        model.addAttribute("characterlist", list);
        model.addAttribute("characters", JSONArray.fromObject(list).toString());
        System.out.println(JSONArray.fromObject(list).toString());
        return "details";
    }

}
