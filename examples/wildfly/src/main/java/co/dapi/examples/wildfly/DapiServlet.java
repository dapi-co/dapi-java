package co.dapi.examples.wildfly;

import okhttp3.Response;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.stream.Collectors;

@WebServlet("/HandleSDKRequests")
public class DapiServlet extends HttpServlet {

    @Inject
    DapiService dapiService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Post/HandleSDKRequests called");

        // Read the body of the request
        String reqBody = req.getReader().lines().collect(Collectors.joining());

        // Read the headers of the request, and prepare them to be passed to Dapi
        HashMap<String, String> reqHeaders = new HashMap<>();
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = req.getHeader(headerName);
            reqHeaders.put(headerName, headerValue);
        }
        reqHeaders.put("Host", "dd.dapi.co");

        // Call Dapi with the got request body and headers, and handle any exception that may occur
        try {
            Response dapiResp = dapiService.handleSDKRequest(reqBody, reqHeaders);

            // Try to read the body of the response got from Dapi
            String dapiRespBody = dapiResp.body().string();

            // Set the Content-Type of the response and write the response
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(dapiRespBody);
            writer.close();
        } catch (IOException e) {
            // Proper exception handling should be implemented in real-world handler
            resp.setContentType("text/plain");
            PrintWriter writer = resp.getWriter();
            writer.println("An exception happened");
            writer.println(e.getMessage());
            writer.close();
        }

    }
}
