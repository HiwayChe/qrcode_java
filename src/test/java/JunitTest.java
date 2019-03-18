import com.cheersson.qrcode.util.CodeUtil;
import com.cheersson.qrcode.util.MessageUtil;
import org.junit.Test;

public class JunitTest {
    @Test
    public void test1(){
        System.out.println("«".length());
        System.out.println("...«»>".indexOf("«"));
    }

    @Test
    public void test2(){
        String code = "a" + CodeUtil.leftSeparator + "RS" + CodeUtil.rightSeparator + "123" + CodeUtil.rightSeparator;
        CodeUtil.split(code).forEach(System.out::println);
    }

    @Test
    public void test3(){
        System.out.println(MessageUtil.getMessage("{0}{1}", "hello","world"));
    }

    @Test
    public void test4(){
        String rule = "ab«RS»c«GS»{yearWeek}«RS»{yearMonthDay}«EOT»";
        String code = "ab«RS»c«GS»1912«RS»20190318«EOT»";
        System.out.println(CodeUtil.validate(code, rule, 123L));
    }
}

