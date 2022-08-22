//package hello.springmvc.service.serviceImpl;
//
//import lombok.extern.slf4j.Slf4j;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.springframework.stereotype.Service;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//@Slf4j
//public class KakaoServiceImpl {
//
//    public String getToken(String code) throws IOException {
//        // 인가코드로 토큰받기
//        String host = "https://kauth.kakao.com/oauth/token";
//        URL url = new URL(host);
//        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//        String token = "";
//
//        try {
//            urlConnection.setRequestMethod("POST");
//            urlConnection.setDoOutput(true);
//
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
//            StringBuilder sb = new StringBuilder();
//
//            sb.append("grant_type=authorization_code");
//            sb.append("&client_id=8eac5875b73c2252d735aebae61ddaa2");
//            sb.append("&redirect_uri=http://localhost:8080/auth/kakao");
//            sb.append("&code=" + code);
//
//            bw.write(sb.toString());
//            bw.flush();
//
//            int responseCode = urlConnection.getResponseCode();
//            log.info("responseCode = " + responseCode);
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            String line = "";
//            String result = "";
//            while ((line = br.readLine()) != null) {
//                result += line;
//            }
//            log.info("result = " + result);
//
//            // json parsing
//            JSONParser parser = new JSONParser();
//            JSONObject elem = (JSONObject) parser.parse(result);
//
//            String access_token = elem.get("access_token").toString();
//            String refresh_token = elem.get("refresh_token").toString();
//            log.info("access_token = " + access_token);
//            log.info("refresh_token = " + refresh_token);
//
//            token = access_token;
//
//            br.close();
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return token;
//    }
//
//    public Map<String, Object> getUserInfo(String access_token) throws IOException {
//        String host = "https://kapi.kakao.com/v2/user/me";
//        Map<String, Object> result = new HashMap<>();
//
//        try {
//            URL url = new URL(host);
//
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestProperty("Authorization", "Bearer " + access_token);
//            urlConnection.setRequestMethod("GET");
//
//            int responseCode = urlConnection.getResponseCode();
//            log.info("responseCode = " + responseCode);
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            String line = "";
//            String res = "";
//            while((line=br.readLine())!=null)
//            {
//                res+=line;
//            }
//
//            log.info("res = " + res);
//
//            JSONParser parser = new JSONParser();
//            JSONObject obj = (JSONObject) parser.parse(res);
//            JSONObject kakao_account = (JSONObject) obj.get("kakao_account");
//            JSONObject properties = (JSONObject) obj.get("properties");
//
//            String id = obj.get("id").toString();
//            String nickname = properties.get("nickname").toString();
//            String age_range = kakao_account.get("age_range").toString();
//
//            result.put("id", id);
//            result.put("nickname", nickname);
//            result.put("age_range", age_range);
//
//            br.close();
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//}
