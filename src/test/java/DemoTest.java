import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haotian.development.sso.server.component.JsonWebTokenUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class DemoTest extends BaseTest {

    @Autowired
    JsonWebTokenUtils jsonWebTokenUtils;

    @Test
    public void initTest() {
        String jwtString = jsonWebTokenUtils.getJWTString("123", new HashMap<>());
        System.out.println(jwtString);
    }

}
