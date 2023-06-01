package bestwaiting.controller;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import cn.bjca.daps.encrypt.spi.EncryptAlgorithm;
import cn.bjca.daps.org.apache.commons.lang3.ArrayUtils;
import cn.bjca.daps.org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bestwaiting.model.UserBean;
import bestwaiting.service.ILoginService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired(required = true)
    private ILoginService loginService;
    @Autowired
    private DataSource dataSource;

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, UserBean user) {
        logger.info(user);

        // jdbcQuery();
        ModelAndView mv = new ModelAndView();
        System.out.println(user.toString());
        UserBean userBean = loginService.Login(user.getUser_name(), user.getUser_pwd());
        if (userBean != null) {
            request.getSession().setAttribute("user", userBean);
            mv.addObject("id", userBean.getId());
            mv.addObject("name", userBean.getUser_name());
            mv.addObject("pwd", userBean.getUser_pwd());
            mv.addObject("phone", userBean.getUser_phone());
            mv.addObject("email", userBean.getUser_email());
            mv.addObject("note", userBean.getUser_note());
            System.out.println(userBean.toString());
        }
        mv.setViewName("index");
        return mv;
    }

    private void jdbcQuery() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement("select * from users");
            rs = pstmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            String[] columns = new String[columnCount + 1];
            columns[0] = "#";
            for (int i = 1; i <= columnCount; i++) {
                try {
                    columns[i] = meta.getColumnLabel(i);
                } catch (Exception e) {
                    logger.error(String.format("meta {%s} columnCount {%s} i {%d}", meta, columnCount, i), e);
                }
            }

            List<String[]> tl = new ArrayList<String[]>();

            for (int rowNum = 1; rs.next(); rowNum++) {
                String[] rowCells = new String[columnCount + 1];
                rowCells[0] = "" + rowNum;
                for (int i = 1; i <= columnCount; i++) {
                    Object obj = rs.getObject(i);
                    if (obj == null || rs.wasNull()) {
                        rowCells[i] = "<NULL>";
                    } else if (obj instanceof byte[]) {
                        rowCells[i] = EncryptAlgorithm.Format.Base64.encode((byte[]) obj);
                    } else {
                        rowCells[i] = obj.toString();
                    }
                }

                tl.add(rowCells);
            }
            for (String[] ss : tl) {
                for (String s : ss) {
                    logger.info(s);
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
