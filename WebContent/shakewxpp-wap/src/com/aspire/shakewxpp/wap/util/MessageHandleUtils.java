package com.aspire.shakewxpp.wap.util;

import com.aspire.portal.web.security.client.GenerateSignature;
import com.aspire.portal.web.security.client.VerifySignature;
import com.aspire.shakewxpp.wap.service.InterHelpService;
import com.aspire.shakewxpp.wap.servlet.interf.Address;
import com.aspire.shakewxpp.wap.servlet.interf.MarketCreditNotifyReq;
import com.aspire.shakewxpp.wap.servlet.interf.Property;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Title: TechTest
 * @Package com.techtest.monitor.servlet
 * @Description: 校验xml消息
 * @Author wuyuehui_a
 * @Date 2014/8/23
 * @Version V1.0
 * Update Logs:
 */
public class MessageHandleUtils {
    private static final Logger logger = LoggerFactory.getLogger(MessageHandleUtils.class);
    private static final String xmlSchema = "/com/aspire/shakewxpp/wap/servlet/interf/app_msg_schema.xsd";

    /**
     * 依据XSD检查XML
     *
     * @param stringXml
     * @throws Exception
     */
    public static void checkXml(String stringXml) throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(MessageHandleUtils.class.getResource(xmlSchema));
        Validator validator = schema.newValidator();
        SAXSource source = new SAXSource(new InputSource(new ByteArrayInputStream(stringXml.getBytes("UTF-8"))));
        validator.validate(source);
        logger.debug("依据XSD[" + xmlSchema + "]文件检查XML内容[" + stringXml + "]通过.");
    }

    /**
     * 解析JAVA对象为XML字符串
     *
     * @param t
     * @return
     * @throws Exception
     */
    public static <T extends Object> String marshaller(T t) throws Exception {
        StringWriter stringWriter = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
            Marshaller m = jaxbContext.createMarshaller();
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(t, stringWriter);
        } catch (IllegalArgumentException ex) {
            logger.error("解析XML字符串为JAVA对象出现异常", ex);
        } catch (JAXBException ex) {
            logger.error("解析XML字符串为JAVA对象出现异常", ex);
        }
        return stringWriter.toString();
    }

    /**
     * 解析XML字符串为JAVA对象
     *
     * @param stringXml
     * @return
     * @throws Exception
     */
    public static <T extends Object> Object unMarshaller(String stringXml, T t) throws Exception {
        T returnObj = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
            Unmarshaller um = jaxbContext.createUnmarshaller();
            returnObj = (T) um.unmarshal(new ByteArrayInputStream(stringXml.getBytes("UTF-8")));
        } catch (IllegalArgumentException ex) {
            logger.error("解析XML字符串为JAVA对象出现异常", ex);
        } catch (JAXBException ex) {
            logger.error("解析XML字符串为JAVA对象出现异常", ex);
        }
        return returnObj;
    }

    /**
     * POST方式提交字符串内容
     *
     * @param message
     * @return
     * @throws Exception
     */
    public static String postMessage(String toUrl, String message) throws Exception {
        String resultString = "";
        if (StringUtils.isEmpty(toUrl)) {
            logger.warn("POST提交的URL为空");
            return resultString;
        }
        PostMethod post = new PostMethod(toUrl);
        HttpClient httpclient = new HttpClient();
        try {
            RequestEntity entity = new StringRequestEntity(message);
            post.setRequestEntity(entity);
            post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");
            int result = httpclient.executeMethod(post);
            if (result == HttpStatus.SC_OK) {
                resultString = post.getResponseBodyAsString();
            } else {
                logger.warn("网络XML消息POST返回错误，HTTP返回码[" + result + "]");
            }
        } catch (Exception ex) {
            logger.error("网络XML消息POST出现异常", ex);
        } finally {
            post.releaseConnection();
        }
        logger.debug("收到的响应消息：{}", resultString);
        return resultString;
    }

    /**
     * 消息组织 - 获取地址
     *
     * @param deviceType
     * @return
     */
    public static Address getAddress(String deviceType) {
        Address address = new Address();
        address.setDeviceType(deviceType);
        address.setDeviceId(deviceType);
        return address;
    }

    /**
     * 消息组织 - 获取属性列表
     *
     * @return
     */
    public static List<Property> getProperties(Map<String, String> param) {
        List<Property> list = new ArrayList<Property>();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            list.add(getProperty(entry.getKey(), entry.getValue()));
        }
        //
        return list;
    }

    /**
     * 消息组织 - 获取属性
     *
     * @param name
     * @param value
     * @return
     */
    private static Property getProperty(String name, String value) {
        Property property = new Property();
        property.setName(name);
        property.setValue(value);
        return property;
    }

    /**
     * 消息组织 - 获取编号
     *
     * @return
     */
    public static synchronized String getTransactionID() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String result = sdf.format(Calendar.getInstance().getTime());
        //从数据库中获取8位序号
        InterHelpService interHelpService = (InterHelpService) BeanLocator.getInstance().getBean("interHelpService");
        String seq = interHelpService.getSequeneForTransactionId();
        result = result.concat(seq);
        //
        return result;
    }

    public static Boolean checkSign(MarketCreditNotifyReq marketCreditNotifyReq) {
        Boolean isVer = Boolean.FALSE;
        try {
            List<Property> propertyList = marketCreditNotifyReq.getParams();
            Map<String, String> param = new HashMap<String, String>();
            for (Property property : propertyList) {
                param.put(property.getName(), property.getValue());
            }
            //
            String publickey = GetConfigFile.getInstance().getProperties("llh_publickey");
            String privateKeyPath = System.getProperty("user.dir");
            String privateKeyFile = privateKeyPath.concat(File.separator).concat("shakewxpp-wap").concat(File.separator)
                    .concat("conf").concat(File.separator).concat("key").concat(File.separator).concat(publickey);
            File keyFile = new File(privateKeyFile);
            if (!keyFile.exists()) {
                logger.warn("流量汇验签文件不存在，中断此次任务");
                return false;
            }
            //
            StringBuffer signText = new StringBuffer();
            signText.append("TransType=").append(param.get("TransType")).append("&")
                    .append("Msisdn=").append(param.get("Msisdn")).append("&")
                    .append("ActivityCode=").append(param.get("ActivityCode")).append("&")
                    .append("TransactionID=").append(marketCreditNotifyReq.getTransactionID()).append("&")
                    .append("Credit=").append(param.get("Credit")).append("&")
                    .append("DateTime=").append(param.get("DateTime")).append("&")
                    .append("Result=").append(param.get("Result"));
            //
            logger.debug("验签前字符：" + signText.toString());
            VerifySignature verSign = new VerifySignature();
            isVer = verSign.verify(signText.toString(), param.get("sign"), keyFile.getAbsolutePath());
            logger.debug("验签后结果：" + isVer);
        } catch (Exception ex) {
            logger.debug("验签出现异常", ex);
        }
        return isVer;
    }

    public static Boolean checkSign(Map<String, String> param, String[] signPropertyNames) {
        Boolean isVer = Boolean.FALSE;
        try {
            String publickey = GetConfigFile.getInstance().getProperties("llh_publickey");
            String privateKeyPath = System.getProperty("user.dir");
            String privateKeyFile = privateKeyPath.concat(File.separator).concat("shakewxpp-wap").concat(File.separator)
                    .concat("conf").concat(File.separator).concat("key").concat(File.separator).concat(publickey);
            File keyFile = new File(privateKeyFile);
            if (!keyFile.exists()) {
                logger.warn("流量汇验签文件不存在，中断此次任务");
                return false;
            }
            //
            String origSign = param.remove("Sign");
            //
            StringBuffer signText = new StringBuffer();
            for (int i=0; i<signPropertyNames.length; i++) {
                String propertyName = signPropertyNames[i].trim();
                if (StringUtils.isNotEmpty(propertyName)) {
                    if (i > 0) {
                        signText.append("&");
                    }
                    signText.append(propertyName).append("=").append(param.get(propertyName));
                }
            }
            //
            logger.debug("验签前字符：" + signText.toString());
            VerifySignature verSign = new VerifySignature();
            isVer = verSign.verify(signText.toString(), origSign, keyFile.getAbsolutePath());
            logger.debug("验签后结果：" + isVer);
        } catch (Exception ex) {
            logger.debug("验签出现异常", ex);
        }
        return isVer;
    }

    public static String sign(Map<String, String> param, String[] signPropertyNames) throws Exception{
        String stringSign = "";
        try {
            String privatekey = GetConfigFile.getInstance().getProperties("wx_privatekey");
            String privateKeyPath = System.getProperty("user.dir");
            String privateKeyFile = privateKeyPath.concat(File.separator).concat("shakewxpp-wap").concat(File.separator)
                    .concat("conf").concat(File.separator).concat("key").concat(File.separator).concat(privatekey);
            File keyFile = new File(privateKeyFile);
            if (!keyFile.exists()) {
                logger.warn("流量汇签名文件不存在，中断此次任务");
                throw new Exception("流量汇签名文件不存在");
            }
            //
            StringBuffer signText = new StringBuffer();
            for (int i=0; i<signPropertyNames.length; i++) {
                String propertyName = signPropertyNames[i].trim();
                if (StringUtils.isNotEmpty(propertyName)) {
                    if (i > 0) {
                        signText.append("&");
                    }
                    signText.append(propertyName).append("=").append(param.get(propertyName));
                }
            }
            //
            logger.debug("验签前字符：" + signText.toString());
            GenerateSignature sign = new GenerateSignature();
            stringSign = sign.sign(signText.toString(), keyFile.getAbsolutePath());
            logger.debug("验签后结果：" + stringSign);
        } catch (Exception ex) {
            logger.error("验签出现异常", ex);
            throw ex;
        }
        return stringSign;
    }

}
