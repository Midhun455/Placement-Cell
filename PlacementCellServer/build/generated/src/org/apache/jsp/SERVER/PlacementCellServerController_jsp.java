package org.apache.jsp.SERVER;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.time.LocalDate;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.Vector;
import java.util.Iterator;
import db.dbconnection;

public final class PlacementCellServerController_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    dbconnection conn = new dbconnection();
    String key = request.getParameter("key").trim();
    System.out.println(key);
    System.out.println("Key Retrived");

    //    ---------officer_register-------
    if (key.equals("officer_register")) {
        String NAME = request.getParameter("o_name");
        String QUALI = request.getParameter("o_quali");
        String ADDRESS = request.getParameter("o_address");
        String PHONE = request.getParameter("o_phone");
        String EMAIL = request.getParameter("o_email");
        String PASSWORD = request.getParameter("o_pswd");
        String COLLEGE = request.getParameter("college_name");

        String insertqry = "SELECT COUNT(*) AS COUNT FROM `officer` WHERE officer_email='" + EMAIL.trim() + "' OR officer_phone='" + PHONE.trim() + "'";
        System.out.println(insertqry);
        Iterator it = conn.getData(insertqry).iterator();
        if (it.hasNext()) {
            Vector v = (Vector) it.next();
            int max_vid = Integer.parseInt(v.get(0).toString());
            System.out.println(max_vid);
            if (max_vid == 0) {
                String qry = "INSERT INTO `officer`(`officer_name`,`officer_email`,`officer_phone`,`officer_quali`,`college`,`officer_address`)VALUES('" + NAME + "', '" + EMAIL + "', '" + PHONE + "', '" + QUALI + "', '" + COLLEGE + "', '" + ADDRESS + "')";
                String qry1 = "INSERT INTO login(reg_id,email,password,type,status) VALUES((select max(`officerId`)from `officer`),'" + EMAIL + "','" + PASSWORD + "','OFFICER', '1')";
                System.out.println(qry + "\n" + qry1);
                if (conn.putData(qry) > 0 && conn.putData(qry1) > 0) {
                    System.out.println("Registered");
                    out.println("Registered");
                } else {
                    System.out.println("Registertion Failed");
                    out.println("Registertion Failed");
                }
            } else {
                System.out.println("Already Exist");
                out.print("Already Exist");
            }
        } else {
            out.print("failed");
        }
    }

    //    ---------Login-------
    if (key.trim().equals("login")) {

        String USERNAME = request.getParameter("U_name");
        String PASSWORD = request.getParameter("P_swd");

        String loginQry = "SELECT * FROM login WHERE email='" + USERNAME + "' AND password='" + PASSWORD + "' AND status='1'";
        System.out.println(loginQry);
        Iterator i = conn.getData(loginQry).iterator();

        if (i.hasNext()) {

            Vector v = (Vector) i.next();

            out.println(v.get(1) + "#" + v.get(4));
            System.out.println(v.get(1) + "#" + v.get(4));
        } else {
            System.out.println("kjkju");
            out.println("failed");

        }
    }

    //    ---------student_register-------
    if (key.equals("commonstudentreg")) {
        String NAME = request.getParameter("name");
        String PHONE = request.getParameter("phone");
        String EMAIL = request.getParameter("email");
        String GENDER = request.getParameter("gender");
        String DOB = request.getParameter("dob");
        String COLLEGE = request.getParameter("college");
        String DEPT = request.getParameter("dept");
        String ADDRESS = request.getParameter("address");
        String PASSWORD = request.getParameter("password");
        String DEGREE = request.getParameter("degree");
        String PLUSTWO = request.getParameter("plustwo");
        String TENTH = request.getParameter("tenth");
        String BACKLOGS = request.getParameter("tenth");

        String insertqry = "SELECT * FROM `student` WHERE `s_email`='" + EMAIL.trim() + "' OR s_phone='" + PHONE.trim() + "'";
        System.out.println(insertqry);
        Iterator itt = conn.getData(insertqry).iterator();
        if (!itt.hasNext()) {
            String qry = "INSERT INTO `student` (`s_name`,`s_phone`,`s_email`,`s_gender`,`dob`,`s_department`,`s_address`,`college_name`,`degree`,`plustwo`,`tenth`,`type`,`backlogs`)VALUES('" + NAME + "', '" + PHONE + "', '" + EMAIL + "', '" + GENDER + "', '" + DOB + "', '" + DEPT + "', '" + ADDRESS + "', '" + COLLEGE + "', '" + DEGREE + "', '" + PLUSTWO + "', '" + TENTH + "','COMMON','" + BACKLOGS + "')";
            String qry1 = "INSERT INTO `login`(reg_id,email,password,type,status) VALUES((select max(`s_id`)from `student`),'" + EMAIL + "','" + PASSWORD + "','STUDCOMMON', '1')";
            System.out.println(qry + "\n" + qry1);
            if (conn.putData(qry) > 0 && conn.putData(qry1) > 0) {
                System.out.println("Registered");
                out.println("Registered");
            } else {
                System.out.println("Registertion Failed");
                out.println("Registertion Failed");
            }
        } else {
            System.out.println("Already Exist");
            out.print("Already Exist");
        }
    }
//    ---------------------addPlacement--------------------- 
    if (key.equals("addPlacement")) {
        String officer_id = request.getParameter("officer_id");
        String job_title = request.getParameter("job_title");
        String job_location = request.getParameter("job_location");
        String comp_name = request.getParameter("comp_name");
        String comp_email = request.getParameter("comp_email");
        String job_type = request.getParameter("job_type");
        String vacancies = request.getParameter("vacancies");
        String job_desc = request.getParameter("job_desc");
        String placement_date = request.getParameter("placement_date");
        String min_qualification = request.getParameter("min_qualification");
        String college = request.getParameter("college");
        String plustwo = request.getParameter("plustwo");
        String tenth = request.getParameter("tenth");
        String backLogs = request.getParameter("backLogs");
        String url = request.getParameter("comp_url");
        LocalDate date = LocalDate.now();
        String DATE = date.toString();

        String qry = "INSERT INTO `placement` (`officer_id`,`job_title`,`job_location`,`comp_name`,`comp_email`,`job_type`,`vacancies`,`job_desc`,`placement_date`,`min_qualification`,`college`,`plustwo`,`tenth`,`posted_date`,`backlogs`,`url`)VALUES(" + officer_id + ",'" + job_title + "','" + job_location + "','" + comp_name + "','" + comp_email + "','" + job_type + "','" + vacancies + "','" + job_desc + "','" + placement_date + "','" + min_qualification + "','" + college + "','" + plustwo + "','" + tenth + "','" + DATE + "','" + backLogs + "','" + url + "') ";
        System.out.println(qry);
        if (conn.putData(qry) > 0) {

            out.print("successful");
        } else {
            out.print("failed");
        }
    }

//-----------------------View Students Common----------------------------------
    if (key.equals("viewStudentsCommon")) {
        String qry = "SELECT `student`.*,`login`.* FROM `student`,`login` WHERE `student`.`s_id`=`login`.`reg_id` AND `login`.`type`='STUDCOMMON'";
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("sid", v.get(0).toString().trim());
                obj.put("sname", v.get(1).toString().trim());
                obj.put("sphone", v.get(2).toString().trim());
                obj.put("semail", v.get(3).toString().trim());
                obj.put("gender", v.get(4).toString().trim());
                obj.put("dob", v.get(5).toString().trim());
                obj.put("dept", v.get(6).toString().trim());
                obj.put("address", v.get(7).toString().trim());
                obj.put("college", v.get(8).toString().trim());
                obj.put("degree", v.get(9).toString().trim());
                obj.put("plustwo", v.get(10).toString().trim());
                obj.put("tenth", v.get(11).toString().trim());
                obj.put("backlogs", v.get(11).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

//-----------------------getSMSnumbers----------------------------------
    if (key.equals("getNumber")) {
        String qry = "SELECT COUNT(`student`.`s_department`),`student`.`s_phone` FROM `student`,`placement` WHERE `student`.`s_department`=`placement`.`min_qualification` AND `student`.`degree`>=`placement`.`college` AND `student`.`plustwo`>=`placement`.`plustwo` AND `student`.`tenth`>=`placement`.`tenth`  GROUP BY (`student`.`s_department`)";
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        System.out.println("QRY--->" + qry);
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("sphone", v.get(1).toString().trim());
                array.add(obj);
                System.out.println("WHILKE");
            }
            out.println(array);
            System.out.println("array" + array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

//-------------------------View Placements----------------------------------
    if (key.equals("getPlacementsStudent")) {
        String sid = request.getParameter("s_id");
        String qry = "SELECT DISTINCT `placement`.* FROM `placement`,`student` WHERE `student`.`degree`>=`placement`.`college` AND `student`.`plustwo`>=`placement`.`plustwo` AND `student`.`tenth`>=`placement`.`tenth` AND `student`.`s_department`=`placement`.`min_qualification` AND `student`.`backlogs`<=`placement`.`backlogs` AND `student`.`s_id`='" + sid + "'";
        System.out.println("QRY->" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("pl_id", v.get(0).toString().trim());
                obj.put("officerId", v.get(1).toString().trim());
                obj.put("title", v.get(2).toString().trim());
                obj.put("location", v.get(3).toString().trim());
                obj.put("comp_name", v.get(4).toString().trim());
                obj.put("comp_email", v.get(5).toString().trim());
                obj.put("jobtype", v.get(6).toString().trim());
                obj.put("vacancies", v.get(7).toString().trim());
                obj.put("job_desc", v.get(8).toString().trim());
                obj.put("date", v.get(9).toString().trim());
                obj.put("min_quali", v.get(10).toString().trim());
                obj.put("college", v.get(11).toString().trim());
                obj.put("plustwo", v.get(12).toString().trim());
                obj.put("tenth", v.get(13).toString().trim());
                obj.put("posted_date", v.get(14).toString().trim());
                obj.put("url", v.get(16).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

    //-------------------------View Placements Officer----------------------------------
    if (key.equals("getPlacementsOfficer")) {
        String sid = request.getParameter("officer_id");
        String qry = "SELECT * FROM `placement` WHERE `officer_id`='" + sid + "'";
        System.out.println("QRY->" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("pl_id", v.get(0).toString().trim());
                obj.put("officerId", v.get(1).toString().trim());
                obj.put("title", v.get(2).toString().trim());
                obj.put("location", v.get(3).toString().trim());
                obj.put("comp_name", v.get(4).toString().trim());
                obj.put("comp_email", v.get(5).toString().trim());
                obj.put("jobtype", v.get(6).toString().trim());
                obj.put("vacancies", v.get(7).toString().trim());
                obj.put("job_desc", v.get(8).toString().trim());
                obj.put("date", v.get(9).toString().trim());
                obj.put("min_quali", v.get(10).toString().trim());
                obj.put("college", v.get(11).toString().trim());
                obj.put("plustwo", v.get(12).toString().trim());
                obj.put("tenth", v.get(13).toString().trim());
                obj.put("posted_date", v.get(14).toString().trim());
                obj.put("url", v.get(16).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

//    ---------------------addEvent--------------------- 
    if (key.equals("addEvent")) {
        String officer_id = request.getParameter("officer_id");
        String subject = request.getParameter("subject");
        String event_desc = request.getParameter("event_desc");
        String event_date = request.getParameter("event_date");
        String event_time = request.getParameter("event_time");
        LocalDate date = LocalDate.now();
        String DATE = date.toString();

        String qry = "INSERT INTO `event_notification`(`officer_id`,`ev_subject`,`ev_description`,`ev_date`,`added_date`,`time`)VALUES('" + officer_id + "','" + subject + "','" + event_desc + "','" + event_date + "','" + DATE + "','" + event_time + "')";
        System.out.println(qry);
        if (conn.putData(qry) > 0) {
            out.print("successful");
        } else {
            out.print("failed");
        }
    }

//-------------------------View Events----------------------------------
    if (key.equals("viewEventsOfficer")) {
        String officer_id = request.getParameter("officer_id");
        String qry = "SELECT * FROM `event_notification` WHERE `officer_id`='" + officer_id + "'";
        System.out.println("QRY->" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("officerId", v.get(1).toString().trim());
                obj.put("subject", v.get(2).toString().trim());
                obj.put("desc", v.get(3).toString().trim());
                obj.put("event_date", v.get(4).toString().trim());
                obj.put("event_time", v.get(6).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

//-------------------------View Events Students----------------------------------
    if (key.equals("getEventsStudent")) {
        String qry = "SELECT * FROM `event_notification`";
        System.out.println("QRY->" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("officerId", v.get(1).toString().trim());
                obj.put("subject", v.get(2).toString().trim());
                obj.put("desc", v.get(3).toString().trim());
                obj.put("event_date", v.get(4).toString().trim());
                obj.put("event_time", v.get(6).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

//    ---------student_register-------
    if (key.equals("externalStudentReg")) {
        String NAME = request.getParameter("name");
        String PHONE = request.getParameter("phone");
        String EMAIL = request.getParameter("email");
        String GENDER = request.getParameter("gender");
        String DOB = request.getParameter("dob");
        String COLLEGE = request.getParameter("college");
        String DEPT = request.getParameter("dept");
        String ADDRESS = request.getParameter("address");
        String PASSWORD = request.getParameter("password");
        String DEGREE = request.getParameter("degree");
        String PLUSTWO = request.getParameter("plustwo");
        String TENTH = request.getParameter("tenth");
        String BACKLOGS = request.getParameter("backlogs");

        String insertqry = "SELECT * FROM `student` WHERE `s_email`='" + EMAIL.trim() + "' OR s_phone='" + PHONE.trim() + "'";
        System.out.println(insertqry);
        Iterator itt = conn.getData(insertqry).iterator();
        if (!itt.hasNext()) {
            String qry = "INSERT INTO `student` (`s_name`,`s_phone`,`s_email`,`s_gender`,`dob`,`s_department`,`s_address`,`college_name`,`degree`,`plustwo`,`tenth`,`type`,`backlogs`)VALUES('" + NAME + "', '" + PHONE + "', '" + EMAIL + "', '" + GENDER + "', '" + DOB + "', '" + DEPT + "', '" + ADDRESS + "', '" + COLLEGE + "', '" + DEGREE + "', '" + PLUSTWO + "', '" + TENTH + "','OUTSIDE', '" + BACKLOGS + "')";
            String qry1 = "INSERT INTO `login`(reg_id,email,password,type,status) VALUES((select max(`s_id`)from `student`),'" + EMAIL + "','" + PASSWORD + "','STUDEXTERNAL', '1')";
            System.out.println(qry + "\n" + qry1);
            if (conn.putData(qry) > 0 && conn.putData(qry1) > 0) {
                System.out.println("Registered");
                out.println("Registered");
            } else {
                System.out.println("Registertion Failed");
                out.println("Registertion Failed");
            }
        } else {
            System.out.println("Already Exist");
            out.print("Already Exist");
        }
    }

//    ---------------------updateResult--------------------- 
    if (key.equals("updateResult")) {
//        String officer_id = request.getParameter("officer_id");
        String job_id = request.getParameter("job_id");
        String student_id = request.getParameter("student_id");
        String aptitude = request.getParameter("aptitude");
        String numerical = request.getParameter("numerical");
        String technical = request.getParameter("technical");
        String coding = request.getParameter("coding");
        String total = request.getParameter("total");
        LocalDate date = LocalDate.now();
        String DATE = date.toString();

        String qry = "INSERT INTO `exam_result`(`job_id`,`student_id`,`aptitude`,`numerical`,`technical`,`coding`,`total`,`date`)VALUES('" + job_id + "','" + student_id + "','" + aptitude + "','" + numerical + "','" + technical + "','" + coding + "','" + total + "','" + DATE + "')";
        System.out.println(qry);
        if (conn.putData(qry) > 0) {
            out.print("successful");
        } else {
            out.print("failed");
        }
    }

    //    ---------getPhoneNumber-------
    if (key.trim().equals("getPhoneNumber")) {
        String student_id = request.getParameter("u_id");
        String getPhone = "SELECT `s_phone` FROM `student` WHERE `s_id`='" + student_id + "' AND `type`='COMMON'";
        System.out.println(getPhone);
        Iterator i = conn.getData(getPhone).iterator();
        if (i.hasNext()) {
            Vector v = (Vector) i.next();
            out.println(v.get(0) + "#");
            System.out.println(v.get(0) + "#");
        } else {
            System.out.println("kjkju");
            out.println("failed");
        }
    }

//---------------------------getallresultstudent----------------------------------
    if (key.equals("getallresultstudent")) {
        String sid = request.getParameter("getr_id");
        String qry = "select `placement`.`job_title` ,`student`.`s_name`,`student`.`s_email`,`student`.`s_department`,`exam_result`.* from `student`,`placement`,`exam_result` where `placement`.`pl_id`=`exam_result`.`job_id` AND `student`.`s_id`=`exam_result`.`student_id` AND `placement`.`officer_id`='" + sid + "'";
        System.out.println("QRY->" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("pl_id", v.get(4).toString().trim());
                obj.put("officerId", v.get(0).toString().trim());
                obj.put("title", v.get(1).toString().trim());
                obj.put("location", v.get(2).toString().trim());
                obj.put("comp_name", v.get(3).toString().trim());
                obj.put("comp_email", v.get(0).toString().trim());
                obj.put("jobtype", v.get(7).toString().trim());
                obj.put("vacancies", v.get(8).toString().trim());
                obj.put("job_desc", v.get(10).toString().trim());
                obj.put("date", v.get(11).toString().trim());
                obj.put("min_quali", v.get(9).toString().trim());
                obj.put("college", v.get(11).toString().trim());
                obj.put("plustwo", v.get(12).toString().trim());
                obj.put("tenth", "");
                obj.put("posted_date", v.get(12).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

//---------------------------getPushMsgData----------------------------------
    if (key.trim().equals("getPushMsgData")) {
        String s_id = request.getParameter("s_id");
        String getData = "SELECT DISTINCT `placement`.`job_title`,`placement`.`job_desc` FROM `placement`,`student` WHERE `student`.`degree`>=`placement`.`college` AND `student`.`plustwo`>=`placement`.`plustwo` AND `student`.`tenth`>=`placement`.`tenth` AND `student`.`s_department`=`placement`.`min_qualification` AND `student`.`backlogs`<=`placement`.`backlogs` AND `student`.`s_id`='" + s_id + "' ORDER BY `pl_id` DESC LIMIT 1";
        System.out.println(getData);
        Iterator i = conn.getData(getData).iterator();
        if (i.hasNext()) {
            Vector v = (Vector) i.next();
            out.println(v.get(0) + "#" + v.get(1) + "#");
            System.out.println(v.get(0) + "#");
        } else {
            System.out.println("kjkju");
            out.println("failed");

        }
    }

    //    ---------getPhoneNumberOut-------
    if (key.trim().equals("getPhoneNumberOut")) {
        String getPhone = "SELECT `s_phone` FROM `student` WHERE `type`='OUTSIDE'";
        String PHONE = "";
        System.out.println(getPhone);
        Iterator i = conn.getData(getPhone).iterator();
        if (i.hasNext()) {
            while (i.hasNext()) {
                Vector v = (Vector) i.next();
                PHONE = PHONE + v.get(0) + "#";
            }
            out.println(PHONE);
            System.out.println("All PHONE :" + PHONE);
        } else {
            System.out.println("kjkju");
            out.println("failed");
        }
    }


    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
