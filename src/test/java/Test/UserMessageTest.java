package Test;

import com.haimian.bl.bean.UserBean;
import com.haimian.bl.dao.impl.UserMessageImpl;
import org.junit.Test;

public class UserMessageTest {
    private UserMessageImpl userMessageImpl = new UserMessageImpl();

    @Test
    public void usetTest() {
        UserBean userBean = new UserBean();
        userBean.setId_card("wolegequ");
        userBean.setEth_address("wolegequ");
        userBean.setEth_private_key("wolegequ");
        userBean.setEth_public_key("wolegequ");
        userBean.setEth_key_store("wolegequ");
        userBean.setPassword("wolegequ");
        userBean.setScore(100);
        userMessageImpl.insertUserMessage(userBean);
    }
}
