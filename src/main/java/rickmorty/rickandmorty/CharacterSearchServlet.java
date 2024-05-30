/**
 * Created by Denys Durbalov
 * Date of creation: 5/30/24
 * Project name: RickAndMorty
 * email: den.dyrbalov25@gmail.com or s28680@pjwstk.edu.pl
 */

package rickmorty.rickandmorty;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/searchCharacters")
public class CharacterSearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String status = request.getParameter("status");
        String species = request.getParameter("species");
        String gender = request.getParameter("gender");
        String type = request.getParameter("type");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String hql = "FROM CharacterModel WHERE 1=1";
            if (name != null && !name.isEmpty()) hql += " AND name LIKE :name";
            if (status != null && !status.isEmpty()) hql += " AND status = :status";
            if (species != null && !species.isEmpty()) hql += " AND species = :species";
            if (gender != null && !gender.isEmpty()) hql += " AND gender = :gender";
            if (type != null && !type.isEmpty()) hql += " AND type = :type";

            var query = session.createQuery(hql, Character.class);
            if (name != null && !name.isEmpty()) query.setParameter("name", "%" + name + "%");
            if (status != null && !status.isEmpty()) query.setParameter("status", status);
            if (species != null && !species.isEmpty()) query.setParameter("species", species);
            if (gender != null && !gender.isEmpty()) query.setParameter("gender", gender);
            if (type != null && !type.isEmpty()) query.setParameter("type", type);

            List<Character> characters = query.getResultList();
            transaction.commit();

            System.out.println(characters);

            Gson gson = HibernateProxyTypeAdapter.registerHibernateProxyTypeAdapter(new GsonBuilder()).create();
            String json = gson.toJson(characters);

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

