package rikkei.academy.controller.Video;

import rikkei.academy.model.Video;
import rikkei.academy.service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VideoController {
    @WebServlet(value = "/video")
    public class UserController extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            String action = request.getParameter("action");
            switch (action){
                case "create":
                    showFormCreateVideo(request,response);
                    break;
            }
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            String action = request.getParameter("action");
            switch (action){
                case "create":
                    actionCreateVideo(request,response);
                    break;
            }
        }
        private void showFormCreateVideo(HttpServletRequest request, HttpServletResponse response){
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/upload/upload-video-form.jsp");
            try {
                dispatcher.forward(request,response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        private void actionCreateVideo(HttpServletRequest request, HttpServletResponse response){
            String video_name = request.getParameter("videoName");
            String video_link = request.getParameter("videoLink");
            String video_avatar = request.getParameter("avatar");
            Video video = new Video(video_name,video_link,video_avatar);
            Service.getInstance().getVideoService().save(video);
            try {
                response.sendRedirect("WEB-INF/upload/upload-video-form.jsp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
