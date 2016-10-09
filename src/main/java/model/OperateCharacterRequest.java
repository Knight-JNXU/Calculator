package model;

/**
 * Created by Knight_JXNU on 2016/10/9.
 */
public class OperateCharacterRequest {
    private String characterName;
    private String operateType;

    public OperateCharacterRequest() {
    }

    public OperateCharacterRequest(String characterName, String operateType) {
        this.characterName = characterName;
        this.operateType = operateType;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    @Override
    public String toString() {
        return "OperateCharacterRequest{" +
                "characterName='" + characterName + '\'' +
                ", operateType='" + operateType + '\'' +
                '}';
    }
}
