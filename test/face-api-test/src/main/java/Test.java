

import com.baidu.aip.face.AipFace;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author zhuzhe
 * @date 2019/7/15 17:40
 * @email zhuzhe_mail@163.com
 */
public class Test {

    //设置APPID/AK/SK
    public static final String APP_ID = "23193640";
    public static final String API_KEY = "IHFB8LzGq6H0wb402v0F6UOy";
    public static final String SECRET_KEY = "9v0jyjVKwW0TPGx9St9BU4cNRrKyGbBa";

    public static void main(String[] args) {
//        文档地址
//        http://ai.baidu.com/docs#/Face-Java-SDK/top
        String image = base64();
        String imageType = "BASE64";

        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 传入可选参数调用接口，根据需求自行设置
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age,beauty,expression,face_shape,gender,glasses,race,eye_status,emotion,face_type");
//        options.put("max_face_num", "2");
//        options.put("face_type", "LIVE");
//        options.put("liveness_control", "LOW");

        JSONObject res = client.detect(image, imageType, options);
        if (res.getString("error_msg") != null && res.getString("error_msg").equals("SUCCESS")) {
            JSONArray faceList = res.getJSONObject("result").getJSONArray("face_list");
            JSONObject jsonObject = faceList.getJSONObject(0);

            System.out.println("年龄：" + jsonObject.getInt("age"));
            // 美丑打分，范围0-100，越大表示越美。
            System.out.println("美丑打分：" + jsonObject.getDouble("beauty"));
            // none:不笑；smile:微笑；laugh:大笑
            System.out.println("表情：" + jsonObject.getJSONObject("expression").getString("type"));
            // square: 正方形 triangle:三角形 oval: 椭圆 heart: 心形 round: 圆形
            System.out.println("脸型：" + jsonObject.getJSONObject("face_shape").getString("type"));
            // male:男性 female:女性
            System.out.println("性别：" + jsonObject.getJSONObject("gender").getString("type"));

            // yellow: 黄种人 white: 白种人 black:黑种人 arabs: 阿拉伯人
            System.out.println("人种：" + jsonObject.getJSONObject("race").getString("type"));

            // [0,1]取值，越接近0闭合的可能性越大
            System.out.println("右眼状态（睁开/闭合）：" + jsonObject.getJSONObject("eye_status").getInt("right_eye"));
            System.out.println("左眼状态（睁开/闭合）：" + jsonObject.getJSONObject("eye_status").getInt("left_eye"));
            System.out.println("人脸置信度，范围【0~1】：" + jsonObject.getInt("face_probability"));
            // none:无眼镜，common:普通眼镜，sun:墨镜
            System.out.println("是否带眼镜：" + jsonObject.getJSONObject("glasses").getString("type"));
            // angry:愤怒 disgust:厌恶 fear:恐惧 happy:高兴 sad:伤心 surprise:惊讶 neutral:无情绪
            System.out.println("情绪：" + jsonObject.getJSONObject("emotion").getString("type"));
            // human: 真实人脸 cartoon: 卡通人脸
            System.out.println("真实人脸/卡通人脸：" + jsonObject.getJSONObject("face_type").getString("type"));
            System.out.println("face_token：" + jsonObject.getString("face_token"));

        } else {
            System.out.println(res.toString());
        }
    }

    public static String base64() {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream("F:\\wh.jpg");
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(data));
    }
}