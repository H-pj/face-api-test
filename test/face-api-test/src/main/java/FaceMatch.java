/*import com.baidu.ai.aip.utils.HttpUtil;
import com.baidu.ai.aip.utils.GsonUtils;*/

import org.apache.commons.codec.binary.Base64;
import util.GsonUtils;
import util.HttpUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

/**
 * 人脸对比
 */
public class FaceMatch {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String faceMatch() {
        //生成map列表
        List<Map<String,String>> list1= new ArrayList<>();
        //生成第一张照片对应的map
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("image",base64("F:\\hg.png"));
        map1.put("image_type","BASE64");
        map1.put("face_type","LIVE");
        map1.put("quality_control","LOW");
        map1.put("liveness_control","NONE");
        list1.add(map1);

        //生成第二张照片对应的map
        HashMap<String, String> map2 = new HashMap<String, String>();
        map2.put("image",base64("F:\\pyy.png"));
        map2.put("image_type","BASE64");
        map2.put("face_type","LIVE");
        map2.put("quality_control","LOW");
        map2.put("liveness_control","NONE");

        list1.add(map2);

        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            //Map map=new HashMap();
            String param = GsonUtils.toJson(list1);
            //System.out.println("param:------"+param);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.8fd9e1ce380ce31d0f742ab2d5a0245e.2592000.1611278457.282335-23193640";

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
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
        FaceMatch.faceMatch();
    }
}