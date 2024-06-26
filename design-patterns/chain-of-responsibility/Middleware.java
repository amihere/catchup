public abstract class Middleware {
  private Middleware next;

  /**
   * Builds chains of middleware objects.
   */
  public static Middleware link(Middleware first, Middleware... chain) {
    Middleware head = first; // Throttling

    for (Middleware nextInChain :
         chain) {              // UserExistsMiddleware, (nextInCHain) RoleCheck
      head.next = nextInChain; // (h) Throttling -> UserExistsMiddleware
      head = nextInChain;      // (h) UserExistsMiddleware
    }
    return first;
  }

  /**
   * Subclasses will implement this method with concrete checks.
   */
  public abstract boolean check(String email, String password);

  /**
   * Runs check on the next object in chain or ends traversing if we're in
   * last object in chain.
   */
  protected boolean checkNext(String email, String password) {
    if (next == null) {
      return true; // end of chain. Valid!
    }
    return next.check(email, password);
  }
}
