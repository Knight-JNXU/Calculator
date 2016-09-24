package dao.impl;

import constant.MyException;
import dao.BaseDao;
import model.AddCharacterRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Exchanger;

/**
 * Created by Knigh on 2016/9/3.
 */
public class BaseDaoImpl implements BaseDao {

    protected final String filePath = "../file/character.txt";
    protected final String userFilePath = "../file/users.txt";
    protected final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    protected final String changeLineStr = "\n";

    public boolean addUserInFile(String user, String passwd, String userFilePath) throws Exception {
        String str = readFileByLines(userFilePath);
        String strs[] = str.split("\\\n");
        if (user == null || passwd == null) {
            throw new MyException("user or passwd is null!");
        }
        if (strs.length < 2) {
            str += (user + ",\n" + passwd + ",");
        } else {
            if (strs[0].contains(user)) {
                throw new MyException("user exist!");
            } else {
                strs[0] += (user + ",");
                strs[1] += (passwd + ",");
                str = (strs[0] + "\n" + strs[1]);
            }
        }
        try {
            File file = new File(userFilePath);
            FileWriter fw = new FileWriter(file);
            fw.write(str);
            fw.close();
        } catch (Exception e) {
            throw new MyException("addUserInFile exception!");
        }
        return true;
    }

    public void addPayMoneyInFile(AddCharacterRequest request, HttpServletRequest httpServletRequest) throws Exception {
        String fileStr = readFileByLines(filePath);
        String fileStrs[] = fileStr.split("\n");
        fileStr = "";
        String author = (String) httpServletRequest.getSession().getAttribute("user");
        for (String s : fileStrs) {
            if (s.indexOf(request.getUsername()) == 0) {
                String moneys[] = request.getPaymoney().split(";");
                for (String item : moneys){
                    s += (item + "," + dateFormat.format(Calendar.getInstance().getTime()) + ","
                            + author + ",");
                }
            }
            fileStr += (s + changeLineStr);
        }
        try {
            File file = new File(filePath);
            FileWriter fw = new FileWriter(file);
            fw.write(fileStr);
            fw.close();
        } catch (Exception e) {
            throw new MyException("addPayMoneyInFile exception!");
        }
    }

    public String readFileByLines(String filePath) throws Exception {
        File file = new File(filePath);
        BufferedReader reader = null;
        String resultStr = "";
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if (tempString.length() > 1) {
                    resultStr += tempString + "\n";
                }
            }
            reader.close();
        } catch (Exception e) {
            throw new MyException("readFileByLines exception!");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    throw new MyException("readFileByLines exception!");
                }
            }
        }
        return resultStr;
    }

    public void operateCharacterInFile(String characterName, String operateType) throws Exception {
        String fileStr = readFileByLines(filePath);
        if (operateType.equals("add")) {
            if (fileStr.contains(characterName)) {
                throw new MyException("character exist!");
            } else {
                fileStr += characterName + ":";
            }
        } else {
            if (operateType.equals("delete")) {
                String strs[] = fileStr.split("\\\n");
                fileStr = "";
                for (String item : strs) {
                    if (!item.contains(characterName)) {
                        fileStr += (item + "\n");
                    }
                }
            }
        }
        try {
            File file = new File(filePath);
            FileWriter fw = new FileWriter(file);
            fw.write(fileStr);
            fw.close();
        } catch (Exception e) {
            throw new MyException("addCharacterInFile exception!");
        }

    }
}
