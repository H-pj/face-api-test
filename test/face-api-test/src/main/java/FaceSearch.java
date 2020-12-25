
import org.apache.commons.codec.binary.Base64;
import util.GsonUtils;
import util.HttpUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 人脸搜索
 */
public class FaceSearch {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String faceSearch() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
            /*HashMap<String, String> map1 = new HashMap<String, String>();
            map1.put("image",base64("F:\\hg.png"));
            map1.put("image_type","BASE64");
            map1.put("face_type","LIVE");
            map1.put("quality_control","LOW");
            map1.put("liveness_control","NONE");*/
            Map<String, Object> map = new HashMap<>();
            map.put("image", base64("F:\\sxd.jpg"));
            //map.put("liveness_control", "NORMAL");
            map.put("group_id_list", "zjz");
            map.put("image_type", "BASE64");
            //map.put("quality_control", "LOW");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.8fd9e1ce380ce31d0f742ab2d5a0245e.2592000.1611278457.282335-23193640";

            String result = HttpUtil.post(url, accessToken, "application/json", param);

            String score=result.substring(result.indexOf("score")+7,result.length()-4);
            System.out.println(score);
            if(Double.parseDouble(score)>=75){
                System.out.println("您是：");
                System.out.println(result.substring(result.indexOf("user_id")+10,result.indexOf("user_info")-3));
            }else{
                System.out.println("识别失败，请重试！");

            }


            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String base64(String path) {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(data));
    }

    public static void main(String[] args) {
        FaceSearch.faceSearch();
    }
}