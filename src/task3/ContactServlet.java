package task3;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取所有联系人的列表
        List<Contact> contacts = getContacts();

        // 将联系人列表传递到JSP页面进行显示
        request.setAttribute("contacts", contacts);
        request.getRequestDispatcher("/contact.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addContact(request);
        } else if ("edit".equals(action)) {
            editContact(request);
        } else if ("delete".equals(action)) {
            deleteContact(request);
        }

        // 重定向回联系人列表页面
        response.sendRedirect("contact");
    }

    private void addContact(HttpServletRequest request) {
        // 从request获取表单数据并创建一个新的联系人对象
        // 然后将该对象保存到数据库或文件系统中
    }

    private void editContact(HttpServletRequest request) {
        // 从request获取表单数据和ID，然后更新对应的联系人对象
        // 更新完成后，将其保存回数据库或文件系统中
    }

    private void deleteContact(HttpServletRequest request) {
        // 从request获取ID，然后从数据库或文件系统中删除对应的联系人对象
    }

    private List<Contact> getContacts() {
        // 从数据库或文件系统中加载所有的联系人对象
        return new ArrayList<>();
    }
}
