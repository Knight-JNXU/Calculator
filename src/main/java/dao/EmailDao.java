package dao;

import model.CharacterModel;
import model.PayDateAuthorModel;

import java.util.List;

/**
 * Created by Knigh on 2016/11/13.
 */
public interface EmailDao {
    public void sentTextEmail(String mes) throws Exception;
    public void sendHtmlEmail(List<CharacterModel> list);
}
