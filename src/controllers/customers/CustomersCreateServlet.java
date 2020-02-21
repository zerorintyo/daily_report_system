package controllers.customers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Customer;
import models.Employee;
import models.validators.CustomerValidator;
import utils.DBUtil;

/**
 * Servlet implementation class CustomersCreateServlet
 */
@WebServlet("/customers/create")
public class CustomersCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomersCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Customer c = new Customer();

            c.setCustomer_name(request.getParameter("customer_name"));
            c.setContent(request.getParameter("content"));

            c.setEmployee((Employee)request.getSession().getAttribute("login_employee"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            c.setCreated_at(currentTime);
            c.setUpdated_at(currentTime);

            List<String> errors = CustomerValidator.validate(c);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("customer", c);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/customers/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(c);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/customers/index");
            }
        }
    }

}
