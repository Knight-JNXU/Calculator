package dao.impl;

import dao.CharacterDao;
import model.AddPayMoneyRequest;
import model.CharacterModel;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Knigh on 2016/9/3.
 */
@Repository
public class CharacterDaoImpl extends BaseDaoImpl implements CharacterDao {

    public String getAllCharacters() throws Exception{
        return readFileByLines(urlHeader+filePath);
    }

    public void addPayMoney(AddPayMoneyRequest request, HttpServletRequest httpServletRequest) throws Exception{
        addPayMoneyInFile(request, httpServletRequest);
    }

    public void operateCharacter(String characterName, String operateType) throws Exception{
        operateCharacterInFile(characterName, operateType);
    }

    public void clear() throws Exception {
        clearFile();
    }

    public void deleteRecord(String fileContent) throws Exception {
        save(fileContent);
    }

    public void create(List<CharacterModel> list) throws Exception {
        createFile(list);
    }

}
