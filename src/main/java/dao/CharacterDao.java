package dao;

import model.AddPayMoneyRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Knigh on 2016/9/3.
 */
public interface CharacterDao extends BaseDao {

    public String getAllCharacters() throws Exception;

    public void addPayMoney(AddPayMoneyRequest request, HttpServletRequest httpServletRequest) throws Exception;

    public void operateCharacter(String characterName, String operateType) throws Exception;

    public void clear() throws Exception;

    public void deleteRecord(String fileContent) throws Exception;

}
