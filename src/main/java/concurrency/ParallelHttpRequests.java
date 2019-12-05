package concurrency;

import org.omg.CORBA.TIMEOUT;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by rupalph on 10/28/19.
 */
public class ParallelHttpRequests {

    final static  int REQUEST_TIMEOUT = 500;
    public static String process(List<Service> serivces) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Response>> callableTasks = new ArrayList<>();
        for(Service s:serivces){
            callableTasks.add(getCallableTask(s));
        }

        List<Future<Response>> futures = executorService.invokeAll(callableTasks);
        List<Response> responses = new ArrayList<>();
        for(Future<Response> future:futures){
            try {
                responses.add(future.get(REQUEST_TIMEOUT, TimeUnit.MILLISECONDS));
            } catch (TimeoutException e) {
                System.out.println("time out occurred");
                responses.add(new Response(" response Timedout.", 500));

            }
        }
        executorService.shutdown();

        String combinedResp = combine(responses);
        return combinedResp;
    }

    private static String combine(List<Response> responses) {
        StringBuilder sb = new StringBuilder();
        responses.stream().forEach(k->sb.append(k+"\n"));
        return sb.toString();
    }

    public static Callable<Response> getCallableTask(Service service){
        Callable<Response> callableTask = () -> {
            Response resp = service.sendRequest();
            return resp;
        };
        return callableTask;
    }

    public static List<Service> createDummyServices(){
        List<Service> list = new ArrayList<>();
        Object[][] testData = { {Request.Type.GET, "Get stock price for AAPL"},
                {Request.Type.GET, "Check local weather"},
                {Request.Type.POST, "Add a task to todo service"}};

        for(int i=0;i<testData.length;i++){
            Request request = new Request((Request.Type)testData[i][0],(String)testData[i][1]);
            Service service = new Service(request);
            list.add(service);
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        List<Service> services = createDummyServices();
        String combinedReponse = process(services);
        System.out.print(combinedReponse);
    }

    static class Service {
        private Request request;
        public Service(Request req) {
            request = req;
        }
        public Response sendRequest() throws Exception {
            System.out.println("Sending.. " + request.getRequestType() + " request body " + request.getRequestBody());
            int sleep = (int) (Math.random() * 1000) + (REQUEST_TIMEOUT * 1000 );
            System.out.println("Waiting for " + sleep);
            Thread.sleep(3000);
            return new Response(request.getRequestBody() + " response success", 200);


        }
    }
    static class Request {
        enum Type { GET, POST, PUT };
        String requestBody;
        Type type;
        public Request(Type t, String jsonString){
            this.type = t;
            requestBody = jsonString;
        }

        public String getRequestBody(){ return requestBody; }
        public Type getRequestType() { return type; }

    }
    static class Response {
        String response;
        int respCode;
        public Response(String resp, int code){
            response = resp;
            respCode = code;
        }
        //public String getResponse() { return response; }
        public String toString() { return "HTTP RESPONSE CODE: "+respCode+", RESPONSE BODY: "+response; }
    }
}

