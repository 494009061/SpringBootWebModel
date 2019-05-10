package com.haotian.core.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * xml工具包
 *
 * @author 张昊天
 */
public class XmlUtil {

    private XStream xstream;

    // 对所有xml节点的转换都增加CDATA标记

    /**
     * 默认构造添加CDATA标签 推荐使用有参构造 本方法为旧方法遗留
     */
    public XmlUtil() {

        /*
         * 扩展xstream，使其支持CDATA块
         */
        xstream = new XStream(new XppDriver(new NoNameCoder()) {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {

                    @Override
                    @SuppressWarnings("rawtypes")
                    public void startNode(String name, Class clazz) {
                        super.startNode(name, clazz);
                    }

                    @Override
                    public String encodeNode(String name) {
                        return name;
                    }

                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }
                };
            }
        });

        // Auto-detect Annotations 自动侦查注解
        xstream.autodetectAnnotations(true);
    }

    /**
     * 指定某些属性节点使用CDATA标签
     */
    public XmlUtil(final String... nodesCDATA) {

        //拼接正则表达式
        StringBuilder reg = new StringBuilder();
        //如果传入了需要添加的节点
        if (nodesCDATA != null && nodesCDATA.length > 0) {

            for (String node : nodesCDATA) {
                reg.append("(").append(node).append(")").append("|");
            }

            reg.setLength(reg.length() - 1);
        }

        final String regCDATA = reg.length() > 0 ? reg.toString().toLowerCase() : null;

        /*
         * 扩展xstream，使其支持CDATA块
         */
        xstream = new XStream(new XppDriver(new NoNameCoder()) {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {

                    String top = null;

                    @Override
                    @SuppressWarnings("rawtypes")
                    public void startNode(String name, Class clazz) {
                        super.startNode(name, clazz);
                    }

                    @Override
                    public String encodeNode(String name) {
                        top = name.toLowerCase();
                        return name;
                    }

                    @Override
                    protected void writeText(QuickWriter writer, String text) {

                        if (regCDATA != null && top.matches(regCDATA)) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        } else {
                            writer.write(text);
                        }
                        top = null;
                    }
                };
            }
        });

        // Auto-detect Annotations 自动侦查注解
        xstream.autodetectAnnotations(true);
    }

    /**
     * 是否全部使用CDATA标签
     *
     * @param cdata
     */
    public XmlUtil(final boolean cdata) {

        /*
         * 扩展xstream，使其支持CDATA块
         */
        xstream = new XStream(new XppDriver(new NoNameCoder()) {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {

                    @Override
                    @SuppressWarnings("rawtypes")
                    public void startNode(String name, Class clazz) {
                        super.startNode(name, clazz);
                    }

                    @Override
                    public String encodeNode(String name) {
                        return name;
                    }

                    @Override
                    protected void writeText(QuickWriter writer, String text) {

                        if (cdata) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        });

        // Auto-detect Annotations 自动侦查注解
        xstream.autodetectAnnotations(true);
    }

    private final String lineSeparator = System.getProperty("line.separator", "\n");// 获取系统换行符
    private String encoding = "UTF-8";

    /**
     * 解析字符串（XML） 到map
     *
     * @return Map<String, String>
     * @throws Exception
     */
    public Map<String, String> xmlString2Map(String msg) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        InputStream inputStream = new ByteArrayInputStream(msg.getBytes("UTF-8"));


        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }

        // 释放资源
        inputStream.close();
        inputStream = null;

        return map;
    }

    private XStream inclueUnderlineXstream = new XStream(new DomDriver(null, new XmlFriendlyNameCoder("_-", "_")));

    public XStream getXstreamInclueUnderline() {
        return inclueUnderlineXstream;
    }

    /**
     * java bean 转XmlString
     *
     * @param alias
     * @param bean
     * @return
     */
    public String javaBean2XmlString(String alias, Object bean) {
        this.xstream.alias(alias, bean.getClass());
        return this.xstream.toXML(bean);
    }

    public void alias(String alias, Class<?> classz) {
        this.xstream.alias(alias, classz);
    }

    /**
     * 将javaBean以xml格式 输出到OutputStream中Stream之后将会被刷新
     *
     * @param alias
     * @param bean
     * @param out
     */
    public void javaBean2Stream(String alias, Object bean, OutputStream out) {
        this.xstream.alias(alias, bean.getClass());
        this.xstream.toXML(bean, out);
    }

    /**
     * 将javaBean以xml格式 输出到Writer中Writer之后将会被刷新
     *
     * @param alias
     * @param bean
     * @param out
     */
    public void javaBean2Writer(String alias, Object bean, Writer out) {
        this.xstream.alias(alias, bean.getClass());
        this.xstream.toXML(bean, out);
    }

    /**
     * 创建一个当前类的实例
     */
    public static XmlUtil newInstance() {
        return new XmlUtil();
    }

    /**
     * 创建一个当前类的实例 是否开启CDATA
     */
    public static XmlUtil newInstance(boolean cdata) {
        return new XmlUtil(cdata);
    }

    /**
     * map转 XMLString
     *
     * @param params
     * @param alias  根节点名称
     */
    public String map2XmlString(Map<String, String> params, String alias) {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(alias).append(">");
        sb.append(lineSeparator);
        for (Iterator<String> i = params.keySet().iterator(); i.hasNext(); ) {
            String key = i.next();
            sb.append("<").append(key).append(">").append("<![CDATA[").append(params.get(key)).append("]]>")
                    .append("</").append(key).append(">");
            sb.append(lineSeparator);
        }
        sb.append("</").append(alias).append(">");
        return sb.toString();

    }

    /**
     * 将Map转换为XMLString 并输出到给定的Stream ,Stream之后将会被刷新, 以防发生异常。
     *
     * @param params
     * @param alias
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    public void map2XmlStream(Map<String, String> params, String alias, OutputStream out)
            throws UnsupportedEncodingException, IOException {
        out.write(map2XmlString(params, alias).getBytes(this.encoding));
        out.flush();
    }

    /**
     * 将Map转换为XMLString 并输出到给定的 Writer之后将会被刷新, 以防发生异常。
     *
     * @param params
     * @param alias
     * @throws IOException
     */
    public void map2XmlWriter(Map<String, String> params, String alias, Writer out) throws IOException {
        out.write(map2XmlString(params, alias));
        out.flush();
    }

    /**
     * 设置输出流的统一编码 默认为ASCII
     *
     * @param encoding
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * xml转bean
     *
     * @param xml
     * @return
     */
    public Object toBean(String xml) {
        return this.xstream.fromXML(xml);
    }

    public <T> T toBean(String xml, Class<T> classz) {
        this.xstream.processAnnotations(classz);
        @SuppressWarnings("unchecked")
        T t = (T) this.toBean(xml);
        return t;
    }

}
