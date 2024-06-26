import java.util.*;
public class Server {
  private Map<String, String> users = new HashMap<>();
  private Middleware middleware;

  /**
   * Client passes a chain of object to server. This improves flexibility and
   * makes testing the server class easier.
   */
  public void setMiddleware(Middleware middleware) {
    this.middleware = middleware;
  }

  /**
   * Server gets email and password from client and sends the authorization
   * request to the chain.
   */
  public boolean logIn(String email, String password) {
    boolean check = middleware.check(email, password);

    if (check) {
      System.out.println("Authorization has been successful!");

      // Do something useful here for authorized users.

      return true;
    }
    return false;
  }

  public void register(String email, String password) {
    users.put(email, password);
  }

  public boolean hasEmail(String email) { return users.containsKey(email); }

  public boolean isValidPassword(String email, String password) {
    return users.get(email).equals(password);
  }
}
