package dao.impl;

import constant.MyException;
import dao.BaseDao;
import dao.EmailDao;
import model.AddPayMoneyRequest;
import model.CharacterModel;
import model.PayDateAuthorModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Knigh on 2016/9/3.
 */
@ComponentScan
public class BaseDaoImpl implements BaseDao {

//    protected final String filePath = "../file/character.txt";
//    protected final String userFilePath = "../file/users.txt";
//    protected final String trashPath = "../file/trash";
    @Value("#{mysettings.urlHeader}")
    protected String urlHeader;
    protected final String filePath = "/file/character.txt";
    protected final String userFilePath = "/file/users.txt";
    protected final String trashPath = "/file/trash";
    protected final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    protected final String changeLineStr = "\n";
    private static Logger logger = Logger.getLogger(BaseDaoImpl.class);
    @Autowired
    private EmailDao emailDao;

    /**
     * 创建文件
     * @param list
     * @throws Exception
     */
    protected void createFile(List<CharacterModel> list) throws Exception {
        try {
            File file = new File(urlHeader+filePath);
            String fileStr = "";
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String dateStr = dateFormat.format(date);
            StringBuffer logInfo = new StringBuffer("pushdown:\n");
            logInfo.append("Name\t Expenditure\t Date\t Author\t Remarks\t\n");
            for(CharacterModel model : list){
                /*logInfo.append(model.getName()+":"+model.getTotal()+",");*/
                fileStr += model.getName()+":"+model.getTotal()+","+dateStr.toString()+",admin,遗留,\n";
                logInfo.append(model.getName()+"\t "+model.getTotal()+"\t admin\t "+(new SimpleDateFormat("yyyy-MM-dd")).format(new Date())+"\t total\t\n");
                List<PayDateAuthorModel> payList = model.getPayDateList();
                for(PayDateAuthorModel pay : payList){
                    logInfo.append(pay.getName()+"\t "+pay.getPay()+"\t "+pay.getDate()+"\t "+pay.getAuthor()+"\t "+pay.getRemark()+"\t\n");
                }
            }
//            logInfo.deleteCharAt(logInfo.length()-1);
            logInfo.append("pushdown end!");
//            emailDao.sentTextEmail(logInfo.toString());
            emailDao.sendHtmlEmail(list);
            logger.info(logInfo.toString());
            FileWriter fw = new FileWriter(file);
            fw.write(fileStr);
            file.createNewFile();
            fw.flush();
            fw.close();
        }catch (IOException e) {
            throw new MyException("create file or send emails error!");
        }
    }

    /**
     * 保存文件
     * @param fileContent
     * @throws Exception
     */
    protected void save(String fileContent) throws Exception{
        try {
            File file = new File(urlHeader+filePath);
            FileWriter fw = new FileWriter(file);
            fw.write(fileContent);
            fw.close();
        }catch (Exception e){
            throw new MyException("save file error!");
        }
    }

    /**
     * 清空记录
     * @throws Exception
     */
    public void clearFile() throws Exception{
        File file = new File(urlHeader+trashPath);
        if(!file.exists()){
            file.mkdir();
        }
        int fileLength = file.listFiles().length;
        String newFilePath = urlHeader+trashPath+"/trash"+fileLength+".txt";
        copyFile(urlHeader+filePath, newFilePath);
        delFile(urlHeader+filePath);
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
    public void addPayMoneyInFile(AddPayMoneyRequest request, HttpServletRequest httpServletRequest) throws Exception {
        String fileStr = readFileByLines(urlHeader+filePath);
        String fileStrs[] = fileStr.split("\n");
        fileStr = "";
        String author = (String) httpServletRequest.getSession().getAttribute("user");
        for (String s : fileStrs) {
            if (s.indexOf(request.getUsername()) == 0) {
                String moneys[] = request.getPaymoney().split(";");
                String remarks[] = request.getRemarks().split(";");
                for (int i=0; i<moneys.length; i++){
                    s += (Double.parseDouble(moneys[i]) + "," + dateFormat.format(Calendar.getInstance().getTime()) + ","
                            + author + "," + remarks[i] + ",");
                }
            }
            fileStr += (s + changeLineStr);
        }
        try {
            File file = new File(urlHeader+filePath);
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
        String fileStr = readFileByLines(urlHeader+filePath);
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
            File file = new File(urlHeader+filePath);
            FileWriter fw = new FileWriter(file);
            fw.write(fileStr);
            fw.close();
        } catch (Exception e) {
            throw new MyException("addCharacterInFile exception!");
        }

    }
}
