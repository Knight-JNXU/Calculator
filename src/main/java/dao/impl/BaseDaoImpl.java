package dao.impl;

import constant.MyException;
import dao.BaseDao;
import model.AddCharacterRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Exchanger;

/**
 * Created by Knigh on 2016/9/3.
 */
public class BaseDaoImpl implements BaseDao {

    protected final String filePath = "../file/character.txt";
    protected final String userFilePath = "../file/users.txt";
    protected final String trashPath = "../file/trash";
    protected final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    protected final String changeLineStr = "\n";

    /**
     * 清空记录
     * @throws Exception
     */
    public void clearFile() throws Exception{
        File file = new File(trashPath);
        if(!file.exists()){
            file.mkdir();
        }
        int fileLength = file.listFiles().length;
        String newFilePath = trashPath+"/trash"+fileLength+".txt";
        copyFile(filePath, newFilePath);
        delFile(filePath);
    }

    /**
     * 拷贝文件
     * @param oldPath
     * @param newPath
     * @throws Exception
     */
    private void copyFile(String oldPath, String newPath) throws Exception{
        int byteread = 0;
        int byteMaxLen = 1024;
        File oldfile = new File(oldPath);
        if(oldfile.exists()){
            try {
                InputStream inputStream = new FileInputStream(oldfile);
                FileOutputStream fileOutputStream = new FileOutputStream(newPath);
                byte[] buffer = new byte[byteMaxLen];
                while ((byteread = inputStream.read(buffer)) != -1){
                    fileOutputStream.write(buffer, 0, byteread);
                }
                inputStream.close();
            }catch (Exception e){
                throw new MyException("copy file error!");
            }
        }else{
            throw new MyException("old file not exist!");
        }
    }

    /**
     * 删除文件
     * @param filePath
     * @throws Exception
     */
    private void delFile(String filePath) throws Exception{
        try {
            File delFile = new File(filePath);
            delFile.delete();
        }catch (Exception e){
            throw new MyException("delete file error!");
        }
    }

    /**
     * 添加用户
     * @param user
     * @param passwd
     * @param userFilePath
     * @return
     * @throws Exception
     */
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

    /**
     * 添加支付
     * @param request
     * @param httpServletRequest
     * @throws Exception
     */
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

    /**
     * 读文件
     * @param filePath
     * @return
     * @throws Exception
     */
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

    /**
     * 操作角色
     * @param characterName
     * @param operateType
     * @throws Exception
     */
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
