package online.wangzzleo.fun.name;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import online.wangzzleo.http.util.HttpClientUtil;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzz
 * @date 2019年11月26日11:10:53
 */
public class NameProducer {

    private void createName() {
        int i = 1;
        while (true) {
            String url = "https://hanyu.baidu.com/hanyu/ajax/search_list?wd=%E5%B8%A6%E5%A5%B3&device=pc&from=home&userid=238816338&pn=" + (++i) +"&_=1555914238360";
            String charset = "UTF-8";
            String mimeType = "application/x-www-form-urlencoded";
            List<BasicNameValuePair> pairList = new ArrayList<>();
//            pairList.add(new BasicNameValuePair("wd", "带女"));
//            pairList.add(new BasicNameValuePair("device", "pc"));
//            pairList.add(new BasicNameValuePair("from", "home"));
//            pairList.add(new BasicNameValuePair("userid", "238816338"));
//            pairList.add(new BasicNameValuePair("pn", String.valueOf(i++)));
//            pairList.add(new BasicNameValuePair("_", "1555914238359"));

            List<Header> headersList = new ArrayList<>();
            headersList.add(new BasicHeader("Cookie", "BIDUPSID=E4E3F546D8DFE526F9CE0CB7D0E4BF40; PSTM=1554272179; BAIDUID=9AFAA641F6E3FB26AB382AD7B79ABA7C:FG=1; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; BDUSS=jRpeTV2T0dtSFhjSGZGQlJkYUFSa01EQXFlSmVFa1p6dmxDSk9kRmJNTkxiTjljRVFBQUFBJCQAAAAAAAAAAAEAAABSDDwO1vHJyNHauuzR1QAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEvft1xL37dcM; PSINO=2; locale=zh; ZD_ENTRY=baidu; H_PS_PSSID=1458_21123_28723_28558_28836_28584_26350_28603; delPer=1; Hm_lvt_010e9ef9290225e88b64ebf20166c8c4=1555483336,1555483969,1555485589,1555659482; Hm_lpvt_010e9ef9290225e88b64ebf20166c8c4=1555659897"));
            headersList.add(new BasicHeader("Referer", "https://hanyu.baidu.com/s?wd=%E4%BA%94%E8%A1%8C%E5%B1%9E%E6%B0%B4%E7%9A%84%E5%AD%97&from=poem"));
            headersList.add(new BasicHeader("Host", "hanyu.baidu.com"));
            headersList.add(new BasicHeader("Accept", "application/json, text/javascript, */*; q=0.01"));
            headersList.add(new BasicHeader("Accept-Encoding", "gzip, deflate, br"));
            headersList.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.9"));
            headersList.add(new BasicHeader("Connection", "keep-alive"));
            headersList.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36"));
            headersList.add(new BasicHeader("X-Requested-With", "zh-CN,zh;q=0.9"));
            Header[] headers = new Header[headersList.size()];
            String baiduWx = HttpClientUtil.sendPostSSLRequest(url, headersList.toArray(headers),pairList, charset, mimeType);
            JSONArray retArray = JSON.parseObject(baiduWx).getJSONArray("ret_array");

            retArray.forEach((j) -> {
                String name = "子"+((JSONObject)j).get("name").toString().charAt(2);
                NameScore.nameScoreTest(charset, mimeType, name);
            });
        }
        //System.out.println(retArray);
    }

}
