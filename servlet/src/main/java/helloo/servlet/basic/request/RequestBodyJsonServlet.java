package helloo.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import helloo.servlet.basic.Dto.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        HelloData hellloData = objectMapper.readValue(messageBody, HelloData.class);
        System.out.println("HelloData.getUsername = " + hellloData.getUsername());
        System.out.println("hellloData.getAge() = " + hellloData.getAge());

        response.getWriter().write("ok");

    }
}
