package dao;

import model.AddCharacterRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Knigh on 2016/9/3.
 */
public interface CharacterDao extends BaseDao {

    public String getAllCharacters() throws Exception;

    public void addPayMoney(AddCharacterRequest request, HttpServletRequest httpServletRequest) throws Exception;

    public void operateCharacter(String characterName, String operateType) throws Exception;

}
