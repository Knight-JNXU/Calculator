package service;

import model.AddCharacterRequest;
import model.CharacterModel;
import model.PayRelationShip;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Knigh on 2016/9/3.
 */
public interface CharacterService extends BaseService {
    public List<CharacterModel> getAllCharacters() throws Exception;
    public List<PayRelationShip> calculatEveryPay(List<CharacterModel> characterModels);
    public void addPayMoney(AddCharacterRequest request, HttpServletRequest httpServletRequest) throws Exception;
    public void operateCharacter(String characterName, String operateType) throws Exception;
}
