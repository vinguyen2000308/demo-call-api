import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String testRequest = "http://183.182.100.174:8280/sendRequest";
        StringEntity stringEntity = new StringEntity(String.valueOf(getRequest("02092493162")), ContentType.APPLICATION_JSON);
        HttpPost request = new HttpPost(testRequest);
        request.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(request);
        InputStream inputStream = response.getEntity().getContent();

        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }

        ObjectMapper om = new ObjectMapper();
        Root root = om.readValue(resultStringBuilder.toString(), Root.class);

    }

    public static JSONObject getRequest(String phoneNumberInput) {
        JSONObject jsonObject = new JSONObject();
        JSONArray fieldMap = new JSONArray();
        JSONObject mti = new JSONObject();
        mti.put("fieldID", 0);
        mti.put("value", "0200");

        JSONObject processCode = new JSONObject();
        processCode.put("fieldID", 3);
        processCode.put("value", "311202");

        JSONObject actionCode = new JSONObject();
        actionCode.put("fieldID", 22);
        actionCode.put("value", "7");

        JSONObject phoneNumber = new JSONObject();
        phoneNumber.put("fieldID", 34);
        phoneNumber.put("value", phoneNumberInput);

        JSONObject partnerCode = new JSONObject();
        partnerCode.put("fieldID", 41);
        partnerCode.put("value", "BCCS");

        JSONObject serviceCode = new JSONObject();
        serviceCode.put("fieldID", 42);
        serviceCode.put("value", "CHECK_INFO_CANCEL");

        fieldMap.put(mti);
        fieldMap.put(processCode);
        fieldMap.put(actionCode);
        fieldMap.put(phoneNumber);
        fieldMap.put(partnerCode);
        fieldMap.put(serviceCode);
        jsonObject.put("fieldMap", fieldMap);
        return jsonObject;
    }
}


