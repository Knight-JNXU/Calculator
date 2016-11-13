package service;

import model.AddPayMoneyRequest;
import model.CharacterModel;
import model.PayRelationShip;
import model.Record;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Knigh on 2016/9/3.
 */
public interface CharacterService extends BaseService {
    public List<CharacterModel> getAllCharacters() throws Exception;
    public List<PayRelationShip> calculatEveryPay(List<CharacterModel> characterModels);
    public void addPayMoney(AddPayMoneyRequest request, HttpServletRequest httpServletRequest) throws Exception;
    public void operateCharacter(String characterName, String operateType) throws Exception;
    public void pushdown(HttpServletRequest request, Model model) throws Exception;
    public void clear() throws Exception;
    public void deleteRecord(Record record) throws Exception;
}
