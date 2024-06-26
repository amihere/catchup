import java.io.*;

public class Demo {
  private static BufferedReader reader =
      new BufferedReader(new InputStreamReader(System.in));
  private static Server server;

  private static void init() {
    server = new Server();
    server.register("a@g.com", "admin");
    server.register("u@g.com", "user");

    // All checks are linked. Client can build various chains using the same
    // components.
    Middleware middleware =
        Middleware.link(new ThrottlingMiddleware(2),
                        new UserExistsMiddleware(server), new RoleMM());

    // Server gets a chain from client code.
    server.setMiddleware(middleware);
  }

  public static void main(String[] args) throws IOException {
    init();

    boolean success;
    do {
      System.out.print("Enter email: ");
      String email = reader.readLine();
      System.out.print("Input password: ");
      String password = reader.readLine();
      success = server.logIn(email, password);
    } while (!success);
  }
}
