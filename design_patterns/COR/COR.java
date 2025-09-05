package design_patterns.COR;

/**
 * Chain of Responsibility Pattern Implementation
 * Demonstrates request processing through a chain of handlers
 */
public class COR {
    // Request class with expanded properties
    public static class Request {
        private String user;
        private boolean isAdmin;
        private boolean isAuthenticated;
        private boolean isAuthorized;
        private String resource;
        private String action; // "READ", "WRITE", "DELETE"

        public Request(String user, String resource, String action) {
            this.user = user;
            this.resource = resource;
            this.action = action;
        }

        // Getters and setters
        public String getUser() { return user; }
        public boolean isAdmin() { return isAdmin; }
        public void setAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }
        public boolean isAuthenticated() { return isAuthenticated; }
        public void setAuthenticated(boolean authenticated) { isAuthenticated = authenticated; }
        public boolean isAuthorized() { return isAuthorized; }
        public void setAuthorized(boolean authorized) { isAuthorized = authorized; }
        public String getResource() { return resource; }
        public String getAction() { return action; }
    }

    // Base handler class
    public abstract static class RequestHandler {
        protected RequestHandler next;
        protected String handlerName;

        public RequestHandler(String name) {
            this.handlerName = name;
        }

        public RequestHandler setNext(RequestHandler handler) {
            this.next = handler;
            return handler;
        }

        public void handle(Request request) {
            if (next != null) {
                next.handle(request);
            } else {
                System.out.println("End of chain reached. Request processing completed.");
            }
        }

        protected void log(String message) {
            System.out.println("[" + handlerName + "] " + message);
        }
    }

    // Authentication Handler
    public static class Authenticator extends RequestHandler {
        public Authenticator() {
            super("Authenticator");
        }

        @Override
        public void handle(Request request) {
            log("Authenticating user: " + request.getUser());
            
            if (request.getUser().equals("Admin")) {
                request.setAdmin(true);
                request.setAuthenticated(true);
                log("Authentication successful");
                super.handle(request);
            } else if (request.getUser().equals("User")) {
                request.setAuthenticated(true);
                log("Authentication successful");
                super.handle(request);
            } else {
                log("Authentication failed");
            }
        }
    }

    // Authorization Handler
    public static class Authorizer extends RequestHandler {
        public Authorizer() {
            super("Authorizer");
        }

        @Override
        public void handle(Request request) {
            log("Checking authorization for: " + request.getUser());
            
            if (!request.isAuthenticated()) {
                log("Can't authorize unauthenticated request");
                return;
            }

            if (request.isAdmin() || 
                (request.getAction().equals("READ") && request.isAuthenticated())) {
                request.setAuthorized(true);
                log("Authorization granted");
                super.handle(request);
            } else {
                log("Authorization denied");
            }
        }
    }

    // Logging Handler
    public static class Logger extends RequestHandler {
        public Logger() {
            super("Logger");
        }

        @Override
        public void handle(Request request) {
            log("Logging request - User: " + request.getUser() + 
                ", Resource: " + request.getResource() + 
                ", Action: " + request.getAction());
            super.handle(request);
        }
    }

    // Resource Handler
    public static class ResourceHandler extends RequestHandler {
        public ResourceHandler() {
            super("ResourceHandler");
        }

        @Override
        public void handle(Request request) {
            if (!request.isAuthorized()) {
                log("Cannot access resource: Not authorized");
                return;
            }

            log("Accessing resource: " + request.getResource() + 
                " with action: " + request.getAction());
            super.handle(request);
        }
    }

    public static void main(String[] args) {
        // Create the chain of responsibility
        RequestHandler chain = new Logger();
        chain.setNext(new Authenticator())
             .setNext(new Authorizer())
             .setNext(new ResourceHandler());

        // Test case 1: Admin user
        System.out.println("=== Testing Admin Access ===");
        Request adminRequest = new Request("Admin", "sensitive-data", "WRITE");
        chain.handle(adminRequest);

        // Test case 2: Regular user with read access
        System.out.println("\n=== Testing Regular User Access ===");
        Request userRequest = new Request("User", "public-data", "READ");
        chain.handle(userRequest);

        // Test case 3: Invalid user
        System.out.println("\n=== Testing Invalid User Access ===");
        Request invalidRequest = new Request("Hacker", "sensitive-data", "DELETE");
        chain.handle(invalidRequest);
    }
}
