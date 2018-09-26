package testwebserver;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class test {

    public static void main(String[] args) {
    
        try {
            //����tomcat�˿�Ĭ��Ϊ8081,������wsdl��ַ��һ����
        	//testwebserver.test
        	///testaxis2webservice/src/testwebserver/test.java
            EndpointReference targetEPR = new EndpointReference("http://localhost:8080/testaxis2webservice/services/Testservice");  
            RPCServiceClient sender = new RPCServiceClient();  
            Options options = sender.getOptions();  
            options.setTimeOutInMilliSeconds(2*20000L);//��ʱʱ��20s  
            options.setTo(targetEPR);  
            /**
             * ����:
             * 1������ҳ��ִ�� wsdl��xs:schema��ǩ��targetNamespace·��
             * <xs:schema  targetNamespace="http://test.axiswevservice.com">
             * 2��<xs:element name="test"> ======�����ǩ��name��ֵ
             * 
             */
            QName qname = new QName("http://test.axiswevservice.com", "test"); 
            String str = "�ͻ��˵��óɹ�";  //���������
            Object[] param = new Object[]{str};  
            Class<?>[] types = new Class[]{String.class};  //������Է�ֵ���͵�  
            /** 
             * RPCServiceClient���invokeBlocking����������WebService�еķ����� 
             * invokeBlocking�������������� 
             * ��һ��������������QName���󣬱�ʾҪ���õķ������� 
             * �ڶ���������ʾҪ���õ�WebService�����Ĳ���ֵ����������ΪObject[]�� 
             * ������������ʾWebService�����ķ���ֵ���͵�Class���󣬲�������ΪClass[]�� 
             *  
             * ������û�в���ʱ��invokeBlocking�����ĵڶ�������ֵ������null����Ҫʹ��new Object[]{}�� 
             */  
            Object[] response1 = sender.invokeBlocking(qname, param, types);  
            System.out.println(response1[0]);
        } catch (AxisFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
